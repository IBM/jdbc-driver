package com.ibm.si.jaql.jdbc;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * MetaData handler, for a given ariel search query's result set
 * 
 * @author IBM
 *
 */
public class ArielResultSetMetaData implements ResultSetMetaData
{
	private Map<String,QueryFieldMetaData> resultSet = null;

	
	public ArielResultSetMetaData()
	{
		resultSet = new LinkedHashMap<String,QueryFieldMetaData>();
	}
	
	public void addColumnDef(final QueryFieldMetaData data)
	{
		String name = data.name;
		if (data.isFunction)
		{
			name = data.alias;
		}
		resultSet.put(name, data);
	}
	
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getColumnCount() throws SQLException
	{
		int result = 0;

		if (resultSet != null)
		{
			result= resultSet.keySet().size();
		}
		
		return result;
	}

	@Override
	public boolean isAutoIncrement(int column) throws SQLException
	{
		// Ariel does not contain any autoincrement fields
		return false;
	}

	@Override
	public boolean isCaseSensitive(int column) throws SQLException
	{
		return false;
	}

	@Override
	public boolean isSearchable(int column) throws SQLException
	{
		// Until I discover otherwise
		return true;
	}

	@Override
	public boolean isCurrency(int column) throws SQLException
	{
		// Maybe for custom properties, but certainly not for static Ariel fields
		return false;
	}

	@Override
	public int isNullable(int column) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isSigned(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getColumnDisplaySize(int column) throws SQLException {
		// TODO Auto-generated method stub
		
		int result = 0;
		final String key = this.resultSet.keySet().toArray()[(column-1)].toString();
		final QueryFieldMetaData fieldMetaData = resultSet.get(key);
		
		result = fieldMetaData.columnSize;
		
		return result;
		
		//return 0;
	}

	@Override
	public String getColumnLabel(final int column) throws SQLException
	{
		return this.resultSet.keySet().toArray()[(column-1)].toString();
	}

	@Override
	public String getColumnName(int column) throws SQLException
	{
		return this.resultSet.keySet().toArray()[(column-1)].toString();
	}

	@Override
	public String getSchemaName(int column) throws SQLException {
		final String key = this.resultSet.keySet().toArray()[(column-1)].toString();
		final QueryFieldMetaData fieldMetaData = resultSet.get(key);	
		String schema = fieldMetaData.schema;
		return schema ;
	}

	@Override
	public int getPrecision(int column) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScale(int column) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTableName(int column) throws SQLException {
		final String key = this.resultSet.keySet().toArray()[(column-1)].toString();
		final QueryFieldMetaData fieldMetaData = resultSet.get(key);	
		String tableName = fieldMetaData.tableName;
		return tableName ;
	}

	@Override
	public String getCatalogName(int column) throws SQLException {
		final String key = this.resultSet.keySet().toArray()[(column-1)].toString();
		final QueryFieldMetaData fieldMetaData = resultSet.get(key);	
		String catalog = fieldMetaData.catalog;
		return catalog ;
	}

	@Override
	public int getColumnType(int column) throws SQLException
	{
		int result = 0;
		final String key = this.resultSet.keySet().toArray()[(column-1)].toString();
		final QueryFieldMetaData fieldMetaData = resultSet.get(key);
		
		result = fieldMetaData.type;
		
		return result;
	}

	@Override
	public String getColumnTypeName(int column) throws SQLException
	{
		
		String result = "";
		final String key = this.resultSet.keySet().toArray()[(column-1)].toString();
		final QueryFieldMetaData fieldMetaData = resultSet.get(key);
		
		result = fieldMetaData.typeName;
		
		return result;
		
		//return this.resultSet.keySet().toArray()[(column-1)].toString();
	}

	@Override
	public boolean isReadOnly(int column) throws SQLException
	{
		return true;
	}

	@Override
	public boolean isWritable(int column) throws SQLException
	{
		return false;
	}

	@Override
	public boolean isDefinitelyWritable(int column) throws SQLException
	{
		return false;
	}

	@Override
	public String getColumnClassName(int column) throws SQLException
	{
		return "java.lang.String";
	}
}
