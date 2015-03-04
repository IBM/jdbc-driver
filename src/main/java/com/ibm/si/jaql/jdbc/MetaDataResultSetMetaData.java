package com.ibm.si.jaql.jdbc;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Ariel Datastore metadata resultset, meta data handler
 * 
 * Datasets returned via an AQL execution, will have an associated metadata set, see MetaDataBuilder and ArielResultSetMetaData
 * However, the metadata result set, also requires have a metadata handler.
 * 
 * @author IBM
 *
 */
public class MetaDataResultSetMetaData implements ResultSetMetaData
{
	static final Logger logger = LogManager.getLogger(MetaDataResultSetMetaData.class.getName());
	private List<MetaDataColumnMetaData> columns;
	
	public MetaDataResultSetMetaData()
	{
	}

	public MetaDataResultSetMetaData( List<MetaDataColumnMetaData> columns )
    {
        this.columns = columns;
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
	public int getColumnCount() throws SQLException {
		int result = 0;

		if (columns != null)
		{
			result= columns.size();
		}
		
		return result;
	}

	@Override
	public boolean isAutoIncrement(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCaseSensitive(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSearchable(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCurrency(int column) throws SQLException {
		// TODO Auto-generated method stub
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
		return 20;
	}

	@Override
	public String getColumnLabel(int column) throws SQLException {
		return columns.get( column - 1 ).getName();
	}

	@Override
	public String getColumnName(int column) throws SQLException {
		return columns.get( column - 1 ).getName();
	}

	@Override
	public String getSchemaName(int column) throws SQLException {
		 return "ariel";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCatalogName(int column) throws SQLException {
		 return "ariel";
	}

	@Override
	public int getColumnType(int column) throws SQLException {
		int result = 0;
		
		if (columns != null)
		{
			result = columns.get( column - 1 ).getType();
		}

		return result;
	}

	@Override
	public String getColumnTypeName(int column) throws SQLException {
		String result = "";
		
		if (columns != null)
		{
			result = columns.get( column - 1 ).getTypeName();
		}

		return result;
	}

	@Override
	public boolean isReadOnly(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWritable(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDefinitelyWritable(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getColumnClassName(int column) throws SQLException {
		String result = "";
		
		if (columns != null)
		{
			result = columns.get( column - 1 ).getTypeName();
		}

		return result;
	}
	
	

}
