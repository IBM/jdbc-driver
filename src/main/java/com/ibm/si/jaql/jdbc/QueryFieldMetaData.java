package com.ibm.si.jaql.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Ariel query, result set, Meta data base pojo.
 * The general use case for QueryFieldMetaData is to take ArielColumn data, and build an enriched more compliant 
 * jdbc compliant MetaData object.
 * MetaDataBuilder can use this object to  
 * 
 * @author IBM
 *
 */
public class QueryFieldMetaData
{
	static final Logger logger = LogManager.getLogger(QueryFieldMetaData.class.getName());
	
	public String catalog; 			//TABLE_CAT
	public String schema;			//TABLE_SCHEM 
	public String tableName;		//TABLE_NAME
	public String name; 			//COLUMN_NAME
	public String alias;			
	public boolean isFunction;
	public int type;				//DATA_TYPE
	public String typeName;			//TYPE_NAME
	public int columnSize;			//COLUMN_SIZE
	public int decimalDigits;		//DECIMAL_DIGITS
		
	@Override
	public String toString()
	{
		logger.debug(String.format("FieldName=%s, Alias=%s, Function=%b, Type=%d, tableName=%s, schema=%s, catalog=%s, type=%d, typeName=%s, columnSize=%d, decimalDigits=%d ",
										name, alias, isFunction, type, tableName, schema, catalog, type, typeName, columnSize, decimalDigits ));
		return String.format("FieldName=%s, Alias=%s, Function=%b, Type=%d, tableName=%s, schema=%s, catalog=%s", name, alias, isFunction, type, tableName, schema, catalog);
	}
}