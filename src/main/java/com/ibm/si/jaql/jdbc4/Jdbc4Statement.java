package com.ibm.si.jaql.jdbc4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.jdbc.JdbcStatement;

/**
 * Ariel JDBC statement handler
 * @author IBM
 *
 */
public class Jdbc4Statement	 extends JdbcStatement {
	
	static final Logger logger = LogManager.getLogger(Jdbc4Statement.class.getName());
	
	private Jdbc4Connection connection;
	private ResultSet resultSet;
	
	public Jdbc4Statement( Jdbc4Connection connection )
    {
		super(connection);
        this.connection = connection;
    }
    
	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		//execute( sql );
		return connection.executeQuery( sql  , Collections.<String, Object>emptyMap());
		//return resultSet;
	}
	
	@Override
    public boolean execute( String sql) throws SQLException
    {
		try
		{
			resultSet = connection.executeQuery( sql  , Collections.<String, Object>emptyMap());
			
			return true;
		}
        catch ( SQLWarning e )
        {
            if ( sqlWarning == null )
            {
                sqlWarning = e;
            }
            else
            {
                sqlWarning.setNextWarning( e );
            }
            throw e;
        }
        catch ( SQLException e )
        {
            throw e;
        }
        catch ( Throwable e )
        {
            throw new SQLException( e );
        }
    }
	
	@Override
	public ResultSet getResultSet() throws SQLException {
		return resultSet;
	}
	
    @Override
    public void close() throws SQLException
    {
        if ( resultSet != null )
        {
            resultSet.close();
        }
        connection = null;
        resultSet = null;
        sqlWarning = null;
    }
    
    @Override
    public boolean getMoreResults() throws SQLException
    {
        resultSet = null;
        return false;
    }
    
    @Override
    public int getResultSetType() throws SQLException
    {
        return ResultSet.TYPE_FORWARD_ONLY;
    }
    
    @Override
    public Connection getConnection() throws SQLException
    {
        return connection;
    }
    
    @Override
    public boolean getMoreResults( int i ) throws SQLException
    {
        return getMoreResults();
    }
    
    
    @Override
    public int getResultSetHoldability() throws SQLException
    {
        return ResultSet.CLOSE_CURSORS_AT_COMMIT;
    }

    @Override
    public boolean isClosed() throws SQLException
    {
        return connection == null;
    }
    
    @Override
    public int getUpdateCount() throws SQLException
    {
        return -1;
    }
    
}
