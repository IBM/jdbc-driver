package com.ibm.si.jaql.util;

import java.lang.StringBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	
  private static List<String> tokenize(String query) {
		List<String> tokens = new ArrayList<String>();
		int start = 0, end = 0;
		boolean inquote = false;
		boolean escape = false;
		int infunction = 0;
		boolean wasFunction = false;
		int index = 1; // for un-named columns
		while(end < query.length()) {
			if ((query.charAt(end) == ' ' || query.charAt(end) == ',') && !inquote && infunction == 0) {
				String col = query.substring(start,end+1);
        tokens.add(col);
				end++;
				escape = false;
				wasFunction = false;
				start = end;
				continue;
			} else if (query.charAt(end) == '(' && !inquote) {
				infunction++;
				wasFunction = true;
				escape = false;
				end++;
				continue;
			} else if (query.charAt(end) == ')' && !inquote) {
				infunction--;
				escape = false;
				end++;
				continue;
			} else if (query.charAt(end) == '\'' && !escape) {
				inquote = !inquote;
				escape = false;
				end++;
			} else if (query.charAt(end) == '\\') {
				escape = !escape;
			} else
				end++;
		}
		if (start != end) {
			String col = query.substring(start,query.length());
      tokens.add(col);
		}
    return tokens;
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
    List<String> tokens = tokenize(stmt);
    StringBuilder newQuery = new StringBuilder();
    boolean addedLimit = false;
    for (int indx = 0; indx < tokens.size(); indx++) {
      if (tokens.get(indx).trim().equalsIgnoreCase("limit")) {
        newQuery.append("LIMIT 1 ");
        indx++;
        addedLimit = true;
      } else if (tokens.get(indx).trim().equalsIgnoreCase("start")) {
        if (!addedLimit) {
          newQuery.append("LIMIT 1 ");
          newQuery.append(tokens.get(indx));
          addedLimit = true;
        } else
          newQuery.append(tokens.get(indx));
      } else
        newQuery.append(tokens.get(indx));
    }
    return newQuery.toString();
  }
  
	public static void main(String[] args)
	{
		System.out.println("New Query \"" + sparkQueryUnwrapper(args[0]) + "\"");
	}
}