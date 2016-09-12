package com.ibm.si.jaql.aql;

import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.aql.AQLParser.ArithmeticOperatorContext;
import com.ibm.si.jaql.aql.AQLParser.DatabaseContext;
import com.ibm.si.jaql.aql.AQLParser.DisplayColumnAllContext;
import com.ibm.si.jaql.aql.AQLParser.DisplayColumnContext;
import com.ibm.si.jaql.aql.AQLParser.DisplayColumnExpressionContext;
import com.ibm.si.jaql.aql.AQLParser.DisplayListContext;
import com.ibm.si.jaql.aql.AQLParser.ExpressionContext;
import com.ibm.si.jaql.aql.AQLParser.QueryContext;
import com.ibm.si.jaql.aql.AQLParser.ScalarFunctionContext;
import com.ibm.si.jaql.aql.AQLParser.StatementContext;

/**
 * AQL (user defined) query strings parser, with utility functions
 *  
 * @author IBM
 *
 */
public class StatementParser
{
	static final Logger logger = LogManager.getLogger(StatementParser.class.getName());
	
	/**
	 * From a AQL query string, determine if the query is a select statement, and process
	 *  
	 * @param stmt
	 * @return
	 */
	public static String parseAQLStatementForTable(String stmt)
	{
		String result = null;
		final AQLParser aqlParser = 
				new AQLParser(new CommonTokenStream(new AQLLexer(new ANTLRInputStream(stmt))));
		//aqlParser.setErrorHandler(new ErrorStrategy());
		final StatementContext statement = aqlParser.statement();
		if (AQLParser.SELECT != statement.start.getType())
		{
			//what do we want to do if it the AQL query is non select ? thrown an exception ?
		}
		else
		{
			final QueryContext queryCtx = statement.query();
			final DatabaseContext dbCtx = queryCtx.database;
			result = dbCtx.name.getText();
		}
		
		return result;
	}
	
	/**
	 * Process an AQL string (with specific columns to be returned), 
	 * determine if the query is a select statement, and process
	 * @param stmt
	 * @return a list of parsed columns, with name, aliases, flags to indicate if its a function or an arithemtic expression
	 */
	public static List<ParsedColumn> parseAQLStatementForFieldList(String stmt)
	{
		final List<ParsedColumn> result = new LinkedList<ParsedColumn>();
		
		final AQLParser aqlParser = new AQLParser(new CommonTokenStream(new AQLLexer(new ANTLRInputStream(stmt))));
		//aqlParser.setErrorHandler(new ErrorStrategy());
		final StatementContext statement = aqlParser.statement();
		if (AQLParser.SELECT != statement.start.getType())
		{
			//what do we want to do if it the AQL query is non select ? thrown an exception ?
		}
		else
		{
			final QueryContext queryCtx = statement.query();
			final DisplayListContext displayCtx = queryCtx.displayList();
			int index = 0;
			for (DisplayColumnContext fieldContext : displayCtx.columns)
			{
				if (fieldContext instanceof DisplayColumnAllContext)
				{
					logger.debug(String.format("Field :: %s",fieldContext.getText()));
				}
				else if (fieldContext instanceof DisplayColumnExpressionContext)
				{
					boolean isFunction = getIsParsedFunction(fieldContext);
					boolean isArthExp = getIsArithemeticExpression(fieldContext);
					String parsedName = getParsedName(fieldContext);
					String parsedAlias = getParsedAlias(fieldContext, index, isFunction);
					index++;
					
					result.add(new ParsedColumn(parsedName, isFunction, parsedAlias, isArthExp));
				}
			}
		}
		
		return result;
	}
	
	
	
	/**
	 * From the AQL, for a column object, DisplayColumnContext <code>columnCtx</code>, determine if this column is derived from an arithmetic calculation
	 * e.g. 
	 * select 1+1 from events
	 * select (STRLEN(sourceIp) + 1) as my_alias from events
	 * @param columnCtx
	 * @return true if artithemtic expression, else false
	 */
	private static boolean getIsArithemeticExpression(DisplayColumnContext columnCtx)
	{
		boolean isArthExp = false;
		
		DisplayColumnExpressionContext displayCtx = (DisplayColumnExpressionContext) columnCtx;
		ExpressionContext exprCtx = displayCtx.expression();
		if (exprCtx != null)
		{
			ArithmeticOperatorContext arCtx = exprCtx.arithmeticOperator();
			if (arCtx != null)
			{
				isArthExp = true;
			}
		}
		
		logger.debug(String.format("isArthExp :: %s", isArthExp));
		return isArthExp;
	}
	
	/**
	 * From the AQL, for a column object, DisplayColumnContext <code>columnCtx</code>, determine if this column is derived from a standard Ariel scalar functions
	 * e.g. 
	 * select STR(sourceIp) from events
	 * select (STRLEN(sourceIp) as my_alias from events
	 * @param columnCtx
	 * @return returns true if Ariel function column, else false
	 */
	private static boolean getIsParsedFunction(DisplayColumnContext columnCtx)
	{
		boolean isFunc = false;
		
		DisplayColumnExpressionContext displayCtx = (DisplayColumnExpressionContext) columnCtx;
		ExpressionContext exprCtx = displayCtx.expression();
		if (exprCtx != null)
		{
			ScalarFunctionContext funcCtx = exprCtx.scalarFunction();
			if (funcCtx != null)
			{
				isFunc = true;
			}
		}
		
		logger.debug(String.format("isFunc :: %s", isFunc));
		return isFunc;
	}
	
	/**
	 * From the AQL, for a column object, DisplayColumnContext <code>columnCtx</code>, determine if this column has an alias, if none is found
	 * set the alias value to the logical sequential index of this column against all the columns in the AQL statement.  
	 * e.g. 
	 * select STR(sourceIp) from events  -- yields "0"
	 * select (STRLEN(sourceIp) as my_alias from events -- yields "my_alias"
	 * @param columnCtx
	 * @return either the alias name if found, or an index value 0..n, based on column position amongst other columns
	 */
	private static String getParsedAlias(DisplayColumnContext columnCtx, int index, boolean wasFunction)
	{
		String parsedAlias = "";
		
		DisplayColumnExpressionContext displayCtx = (DisplayColumnExpressionContext) columnCtx;
		if (displayCtx.alias != null)
		{
			parsedAlias = displayCtx.alias.getText();
		}
		else if(parsedAlias.equals("") && wasFunction)
		{
			parsedAlias = Integer.toString(index);
		}
		logger.debug(String.format("Alias :: %s", parsedAlias));
		return parsedAlias;
	}
	
	/**
	 * From the AQL, for a column object, DisplayColumnContext <code>columnCtx</code>, determine if this column has an name, 
	 * 
	 * @param columnCtx
	 * @return some name string
	 * 
	 * e.g. 
	 * select STR(sourceIp) from events  -- yields "STR"
	 * select (1+1) from events -- yields "1+1"
	 * select sourceIp from events -- yields "sourceIp"
	 */
	private static String getParsedName(DisplayColumnContext columnCtx)
	{
		String parsedName = null;
		
		DisplayColumnExpressionContext displayCtx = (DisplayColumnExpressionContext) columnCtx;
		ExpressionContext exprCtx = displayCtx.expression();
		if (exprCtx != null)
		{
			//arithemtic expression, take the full expression as the column name
			ArithmeticOperatorContext arCtx = exprCtx.arithmeticOperator();
			if (arCtx != null)
			{
				parsedName = columnCtx.getText();
				logger.debug(String.format("ParsedName based on arithemtic expression :: %s", parsedName));
			}
			
			//ariel function, take the parsed function name (STR, STRLEN etc) as the column name
			ScalarFunctionContext funcCtx = exprCtx.scalarFunction();
			if (funcCtx != null)
			{
				if (funcCtx.name != null)
				{
					parsedName = funcCtx.name.getText();
					logger.debug(String.format("ParsedName based on Scalar Function name:: %s", parsedName));
				}
				else
				{
					parsedName = funcCtx.agg.getText();
					logger.debug(String.format("ParsedName based on Agg Function :: %s", parsedName));
				}
			}
			
			//else revert to explicit fieldname  
			if (parsedName == null)
			{
				parsedName = columnCtx.getText();
			}

		}
		
		return parsedName;
	}
}
