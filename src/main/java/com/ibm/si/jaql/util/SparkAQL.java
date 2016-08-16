package com.ibm.si.jaql.util;

import java.lang.StringBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.aql.AQLParser;
import com.ibm.si.jaql.aql.AQLParser.StatementContext;
import com.ibm.si.jaql.aql.AQLLexer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class SparkAQL {
	private static Pattern getSchemaPattern = Pattern.compile("^SELECT\\s+\\*\\s+FROM\\s+\\((SELECT\\s+(.*)\\s+FROM\\s+(\\S+)(.*))\\)\\s+WHERE\\s+1=0$", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
	private static Pattern selectStarPattern = Pattern.compile("^SELECT\\s+(.*?)\\s+FROM\\s+\\((SELECT\\s+(.*)\\s+(FROM\\s+(\\S+)(.*)))\\)\\s*$", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
	private static Pattern removeWherePattern = Pattern.compile("(.*)\\s*WHERE\\s+1\\s*=\\s*0\\s*$");

	static final Logger logger = LogManager.getLogger();

	// Does the query contain a WHERE 1=0 Spark schema extractor
	private static boolean isDetectingSchema(final String query)
	{
		return query.endsWith("WHERE 1=0");
	}
	
	/**
	 * Spark will make some rather annoying queries in order to determine that a table exists
	 * and what the Schema is. Instead of chanign Spark, we'll identify and unwrap these queries
	 * and make them compatible with AQL. In particular, Spark attempts to return zero records
	 * thinking that the ResultSetMetaData will contain the types. AQL will return zero records,
	 * thus we can't return any schema information. 
	 */
	public static String sparkQueryUnwrapper(final String query)
	{
		if (isDetectingSchema(query))
			return getSchemaQuery(query);
		Matcher match = selectStarPattern.matcher(query);
		if (match.matches()) {
			return "SELECT " + newColumns(match.group(1), match.group(3)) + " " + match.group(4);
		}
		return query;
	}
	
	public static String getColumnName(String column) {
		Pattern isAlias = Pattern.compile("^(.*)\\s+as\\s+([a-z_@#]+[a-z0-1_@#]*)$", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
		Matcher match = isAlias.matcher(column);
		if (match.matches())
			return match.group(2).toLowerCase();
		return column.toLowerCase();
	}
	
	public static String newColumns(final String proj, final String selected) {
		if (proj.equals("*"))
			return selected;
		Map<String,String> columnsSelected = new HashMap<String,String>();
		int start = 0,end = 0;
		boolean inquote = false;
		boolean escape = false;
		int infunction = 0;
		boolean wasFunction = false;
		int index = 1; // for un-named columns
		while(end < selected.length()) {
			if (selected.charAt(end) == ',' && !inquote && infunction == 0) {
				String col = selected.substring(start,end);
				String alias = getColumnName(col);
				if (col.compareToIgnoreCase(alias) == 0 && wasFunction)
					columnsSelected.put(Integer.toString(index++).trim(), col.trim());
				else
					columnsSelected.put(alias.trim(), col.trim());
				end++;
				escape = false;
				wasFunction = false;
				start = end;
				continue;
			} else if (selected.charAt(end) == '(' && !inquote) {
				infunction++;
				wasFunction = true;
				escape = false;
				end++;
				continue;
			} else if (selected.charAt(end) == ')' && !inquote) {
				infunction--;
				escape = false;
				end++;
				continue;
			} else if (selected.charAt(end) == '\'' && !escape) {
				inquote = !inquote;
				escape = false;
				end++;
			} else if (selected.charAt(end) == '\\') {
				escape = !escape;
			} else
				end++;
		}
		if (start != end) {
			String col = selected.substring(start,end);
			String alias = getColumnName(col);
			if (col.compareToIgnoreCase(alias) == 0 && wasFunction)
				columnsSelected.put(Integer.toString(index++).trim(), col);
			else
				columnsSelected.put(alias.trim(), col.trim());
		}

		logger.debug("Columns Selected: {}", columnsSelected);
		StringBuilder sb = new StringBuilder();
		for (String s : proj.split(",")) {
			s = s.replaceAll("\"", "").trim();
			if (columnsSelected.containsKey(s.toLowerCase()))
			{
				if (sb.length() > 0)
					sb.append(",");
				sb.append(columnsSelected.get(s.toLowerCase()));
			} else if (columnsSelected.containsKey("*")) {
				if (sb.length() > 0)
					sb.append(",");
				sb.append(s);
			} else {
			  logger.warn("Column '{}' in projection not found in original query", s);
			}
		}
		return sb.toString();
	}
	
		private static void quoteString(StringBuilder builder, String s)
		{
		builder.append("'");
		builder.append(s);
		builder.append("'");
    }
	private static void joinParseTree(ParseTree tree, StringBuilder builder, char sep)
	{
        // TODO There seems to be some error in the parser.
        // queryTime : START startTime=StringLiteral (STOP stopTime=StringLiteral)?
        // But the LiteralStringContext are coming through as CommonTokens
        logger.trace("@" + tree.getPayload().getClass() + "\t" + tree.getPayload() + "\t" + tree.getText());
		for (int i = 0; i < tree.getChildCount(); i++) {
            logger.trace(tree.getChild(i).getPayload().getClass() + "\t" + tree.getChild(i).getPayload() + "\t" + tree.getChild(i).getText());
			if (tree.getChild(i).getPayload() instanceof org.antlr.v4.runtime.CommonToken) {
				if (builder.length() > 0)
					builder.append(sep);
				logger.debug(tree.getChild(i).getPayload() + "\t" + tree.getChild(i).getPayload().getClass() + "\t" + tree.getChild(i).getText());
                if (tree.getPayload() instanceof com.ibm.si.jaql.aql.AQLParser.QueryTimeContext && !(tree.getChild(i).getText().equals("STOP") || tree.getChild(i).getText().equals("START")))
                    quoteString(builder, tree.getChild(i).getText());
                else
    				builder.append(tree.getChild(i).getText());
			} else if (tree.getChild(i).getPayload() instanceof com.ibm.si.jaql.aql.AQLParser.LiteralStringContext) {
				if (builder.length() > 0)
					builder.append(sep);
				logger.debug(tree.getChild(i).getPayload() + "\t" + tree.getChild(i).getPayload().getClass() + "\t" + tree.getChild(i).getText());
				// TODO Proper quote here
                quoteString(builder, tree.getChild(i).getText());
			} else {
				logger.debug(tree.getChild(i).getPayload() + "\t" + tree.getChild(i).getPayload().getClass() + "\t" + tree.getChild(i).getText());
				joinParseTree(tree.getChild(i), builder, sep);
			}
		}
	}
	
	private static String getSchemaCoreQuery(final String query)
	{
		Matcher match = getSchemaPattern.matcher(query);
		if (match.matches()) return match.group(1);
		match = selectStarPattern.matcher(query);
		if (match.matches()) return match.group(1);
		match = removeWherePattern.matcher(query);
		if (match.matches()) return match.group(1);
		logger.fatal("Failed to parse and convert an SQL to AQL query. Should not get here.");
		return query;
	}
	/**
	 * Reformats the query to insert a LIMIT 1 prior to a Time constraint
	 */
	private static String getSchemaQuery(final String query)
	{
		String stmt = getSchemaCoreQuery(query);
		final AQLParser aqlParser = 
				new AQLParser(new CommonTokenStream(new AQLLexer(new ANTLRInputStream(stmt))));
		final StatementContext statement = aqlParser.statement();
		if (AQLParser.SELECT != statement.start.getType())
		{
			//what do we want to do if it the AQL query is non select ? thrown an exception ?
			return query;
		}
		ParseTree queryTree = statement.getChild(0);
		StringBuilder newQuery = new StringBuilder();
		boolean hasLimit = false;
		for (int i = 0; i < queryTree.getChildCount(); )
		{
			if (queryTree.getChild(i).getPayload() instanceof org.antlr.v4.runtime.CommonToken) {
				if (((org.antlr.v4.runtime.CommonToken)queryTree.getChild(i).getPayload()).getType() == AQLParser.LIMIT) {
					newQuery.append(" LIMIT 1");
					hasLimit = true;
					i += 2;
					continue;
				} else {
					if (newQuery.length() > 0)
						newQuery.append(' ');
					logger.debug(queryTree.getChild(i).getPayload() + "\t" + queryTree.getChild(i).getPayload().getClass() + "\t" + queryTree.getChild(i).getText());
					newQuery.append(queryTree.getChild(i).getText());
					i++;
					continue;
				}
			} else if (queryTree.getChild(i).getPayload() instanceof com.ibm.si.jaql.aql.AQLParser.QueryTimeContext) {
				if (!hasLimit) {
					newQuery.append(" LIMIT 1");
					hasLimit = true;
				}
				joinParseTree(queryTree.getChild(i), newQuery, ' ');
				i++;
			} else {
				joinParseTree(queryTree.getChild(i), newQuery, ' ');
				i++;
			}
		}
		return newQuery.toString();
	}
	public static void main(String[] args)
	{
		System.out.println("New Query '" + sparkQueryUnwrapper(args[0]) + "'");
	}
}