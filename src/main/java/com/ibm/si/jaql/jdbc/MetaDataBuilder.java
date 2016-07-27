package com.ibm.si.jaql.jdbc;

import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.api.ArielException;
import com.ibm.si.jaql.api.IArielDatabase;
import com.ibm.si.jaql.api.pojo.ArielColumn;
import com.ibm.si.jaql.api.pojo.ColumnTuple;
import com.ibm.si.jaql.aql.ParsedColumn;
import com.ibm.si.jaql.aql.StatementParser;
import com.ibm.si.jaql.rest.FunctionMetaData;

/**
 * Utility, builder class, taking the resultsets from executions of AQL, parses the aql query string, or 
 * resultsets to build the metadata set, corresponding to that data.
 * Makes use of raw ArielColumn (endpoint basic metadata) and QueryFieldMetaData (endricehed endpoint metadata)
 * to garner ArielResultSetMetaData, to be passed to ArielResultSetMetaData 
 * 
 * @author IBM
 *
 */
public class MetaDataBuilder
{
	static final Logger logger = LogManager.getLogger(MetaDataBuilder.class.getName());
	
	private MetaDataBuilder() {}
	
	/**
	 * Generate Ariel result set metadata
	 * Parse the <code>aql</code> string, and map display columns against their metadata types
	 * Should a wildcard search be used, we need to process the resultset itseld to yield the metadata 
	 * @param db
	 * @param aql
	 * @param results
	 * @return
	 */
	public static ArielResultSetMetaData generateResultSetMetaData(final IArielDatabase db, 
																	final String aql, 
																	List<Map<String,ColumnTuple>> results)
																		throws ArielException
	{
		ArielResultSetMetaData result = generateResultSetMetaData(db, aql);
		List<QueryFieldMetaData> fieldInfo = generateMetaData(db, aql);
		
		final String parsedTableName = StatementParser.parseAQLStatementForTable(aql);

		//wildcard search has been passed through
		if (fieldInfo.size() ==0 || (results != null && results.size() > 0 && results.get(0).size() != fieldInfo.size()))
		{
			if (results != null && results.size() > 0)
			{
				Map<String,ColumnTuple> datarow = results.get(0);
				fieldInfo = generateMetaData(db, datarow, parsedTableName);
			}
		}

		for (final QueryFieldMetaData field : fieldInfo)
		{
			result.addColumnDef(field);
			logger.debug("QueryFieldMetaData> %s", field);
		}
		
		
		
		return result;
	}
	
	/**
	 * Build the metadata based on parsing the <code>aql</code> string
	 * @param db
	 * @param aql
	 * @return
	 */
	protected static ArielResultSetMetaData generateResultSetMetaData(final IArielDatabase db, final String aql)
																			throws ArielException
	{
		final ArielResultSetMetaData result = new ArielResultSetMetaData();
		final List<QueryFieldMetaData> fieldInfo = generateMetaData(db, aql);
		
		for (final QueryFieldMetaData field : fieldInfo)
		{
			result.addColumnDef(field);
			logger.debug("QueryFieldMetaData> %s", field);
		}
		
		return result;
	}
	
	
	/**
	 * Utility method to build the actual metadata object list from the raw Ariel query string <code>aql</code>
	 * @param db
	 * @param aql
	 * @return
	 */
	protected static List<QueryFieldMetaData> generateMetaData(final IArielDatabase db, 
																final String aql)
																	 throws ArielException
		
	{
		final List<QueryFieldMetaData> result = new LinkedList<QueryFieldMetaData>();
		
		final List<ParsedColumn> parsed = StatementParser.parseAQLStatementForFieldList(aql);
		final String parsedTableName = StatementParser.parseAQLStatementForTable(aql);
		
		for (final ParsedColumn column : parsed)
		{
			final QueryFieldMetaData md = new QueryFieldMetaData();
			md.name = column.name;
			md.alias = column.alias;
			md.isFunction = column.func;
			
			if (md.isFunction)
			{
				md.type = ArielDatabaseMetaData.toJDBCType(FunctionMetaData.getInstance().getType(md.name));
				md.typeName = FunctionMetaData.getInstance().getType(md.name);
			}
			else
			{
				final ArielColumn colMetaData = db.getMetaData(parsedTableName).get(md.name.toLowerCase());
				
				//if no meta data exists for that field, or function, default the mapped expression (diaplay column context) to a VARCHAR
				if (colMetaData != null)
				{
					md.type = ArielDatabaseMetaData.toJDBCType(colMetaData.getArgumentType());
					md.typeName = colMetaData.getArgumentType();
				}
				else
				{
					md.type = ArielDatabaseMetaData.toJDBCType("VARCHAR");
					md.typeName = "VARCHAR";
				}
			}
			
			//static meta data
			md.catalog = "ariel";
			md.schema = "ariel";
			md.tableName = parsedTableName;
			
			if (md.type == Types.VARCHAR)
			{
				md.columnSize = 2000;
			}
			
			result.add(md);
		}
		
		return result;
	}
	
	/**
	 * Utility method to build the actual metadata object list from the resultset, which gives us a datarow
	 * which contains the resultset data, and some basic data on the Ariel column.
	 * TODO: consider an enum or mapper to map metdatacolumns specific per type
	 * @param db
	 * @param datarow
	 * @param parsedTableName
	 * @return
	 */
	protected static List<QueryFieldMetaData> generateMetaData(final IArielDatabase db, 
																final Map<String,ColumnTuple> datarow, 
																final String parsedTableName)
																	 throws ArielException
	{
		final List<QueryFieldMetaData> result = new LinkedList<QueryFieldMetaData>();
		
		for (Map.Entry<String, ColumnTuple> entry : datarow.entrySet()) {
	    	final QueryFieldMetaData md = new QueryFieldMetaData();
			md.name = entry.getValue().getName();
			md.alias = "";
			md.isFunction = false;
			
			final ArielColumn colMetaData = db.getMetaData(parsedTableName).get(md.name.toLowerCase());
			//if no meta data exists for that field, or function, default the mapped expression (diaplay column context) to a VARCHAR
			if (colMetaData != null)
			{
				md.type = ArielDatabaseMetaData.toJDBCType(colMetaData.getArgumentType());
				md.typeName = colMetaData.getArgumentType();
			}
			else
			{
				md.type = ArielDatabaseMetaData.toJDBCType("VARCHAR");
				md.typeName = "VARCHAR";
			}
			
			//static meta data
			md.catalog = "ariel";
			md.schema = "ariel";
			md.tableName = parsedTableName;
			
			if (md.type == Types.VARCHAR)
			{
				md.columnSize = 2000;
			}
			else
			{
				md.columnSize = 20;
			}
			
			result.add(md);
	        
	    }

		return result;
	}
	
	
	
	
	


	

}


