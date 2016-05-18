package com.ibm.si.jaql.util;

// import java.util.List;
// import java.util.LinkedList;
import java.lang.StringBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.aql.AQLParser;
import com.ibm.si.jaql.aql.AQLParser.StatementContext;
import com.ibm.si.jaql.aql.AQLLexer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class SparkAQL {
	private static Pattern getSchemaPattern = Pattern.compile("^SELECT\\s+\\*\\s+FROM\\s+\\((SELECT\\s+(.*)\\s+FROM\\s+(\\S+)\\s+WHERE\\s+(.*))\\)\\s+WHERE\\s+1=0$", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
	private static Pattern selectStarPattern = Pattern.compile("^SELECT\\s+(.*?)\\s+FROM\\s+\\((SELECT\\s+(.*)\\s+FROM\\s+(\\S+)\\s+WHERE\\s+(.*))\\)\\s*$", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
	private static Pattern removeWherePattern = Pattern.compile("(.*)\\s*WHERE\\s+1\\s*=\\s*0\\s*$");

	static final Logger logger = LogManager.getLogger(SparkAQL.class.getName());

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
			// TODO match.group(1) might be a subset of the fields in the original select. Perform filtering
			return match.group(2);
		}
		return query;
	}
	
	private static void joinParseTree(ParseTree tree, StringBuilder builder, char sep)
	{
		for (int i = 0; i < tree.getChildCount(); i++) {
			if (tree.getChild(i).getPayload() instanceof org.antlr.v4.runtime.CommonToken) {
				if (builder.length() > 0)
					builder.append(sep);
				logger.debug(tree.getChild(i).getPayload() + "\t" + tree.getChild(i).getPayload().getClass() + "\t" + tree.getChild(i).getText());
				builder.append(tree.getChild(i).getText());
			} else if (tree.getChild(i).getPayload() instanceof com.ibm.si.jaql.aql.AQLParser.LiteralStringContext) {
				if (builder.length() > 0)
					builder.append(sep);
				logger.debug(tree.getChild(i).getPayload() + "\t" + tree.getChild(i).getPayload().getClass() + "\t" + tree.getChild(i).getText());
				// TODO Proper quote here
				builder.append("'");
				builder.append(tree.getChild(i).getText());
				builder.append("'");
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
		int count = statement.getChildCount();
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