package com.ibm.si.jaql.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.api.pojo.ColumnTuple;

/**
 * Some basic methods for parsing AQL into fields, supporting meta data operations
 * 
 * @author IBM
 */
public class AQLParser
{
	static final Logger logger = LogManager.getLogger(AQLParser.class.getName());
	
	private static Pattern aqlRegex = 
			Pattern.compile("^(?:select)\\s+(\\w+)(,\\s*\\w+)*(?:\\s+from.*)",
					Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
	
	public static List<ColumnTuple> parse(final String aql)
	{
		final List<ColumnTuple> result = new ArrayList<ColumnTuple>();
		
		final Matcher match = aqlRegex.matcher(aql);
		if (match.matches())
		{
			final int count = match.groupCount();
			logger.debug(String.format("There are %d matches", count));
			for (int idx = 1; idx <= count; idx++)
			{
				final String fieldName = match.group(idx);
				logger.debug("Extracted field name is " + fieldName);
				ColumnTuple tuple = new ColumnTuple(fieldName, "", "java.lang.String");
				result.add(tuple);
			}
		}
		
		return result;
	}
}
