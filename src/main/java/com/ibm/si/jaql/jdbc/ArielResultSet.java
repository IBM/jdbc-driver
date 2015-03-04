package com.ibm.si.jaql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.api.pojo.ColumnTuple;
import com.ibm.si.jaql.jdbc4.Jdbc4Connection;


/**
 * Ariel datastore jdbc compliant result set handler
 * @author IBM
 *
 */
public class ArielResultSet extends AbstractResultSet{
	
	static final Logger logger = LogManager.getLogger(ArielResultSet.class.getName());
	
	private List<Map<String,ColumnTuple>> results;
	private Map<String,ColumnTuple> currentRow;
	private ListIterator<Map<String,ColumnTuple>> resultsListIter;
    private int row = -1;

    public ArielResultSet( final Jdbc4Connection conn, final List<Map<String,ColumnTuple>> results, final String query)
    {
    	super(conn, results, query);
    	this.results = results;
        resultsListIter = results.listIterator();
        resultsListIter.hasNext();
    }
    
    
    protected Map<String,ColumnTuple> currentRow()
    {
        return currentRow;
    }
    
    @Override
    public boolean next() throws SQLException
    {
        if ( hasNext() )
        {
        	currentRow = resultsListIter.next();
            row++;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private boolean hasNext()
    {
        return resultsListIter.hasNext();
    }
    
    
    @Override
    public boolean isBeforeFirst() throws SQLException
    {
        return row == -1;
    }

    @Override
    public boolean isAfterLast() throws SQLException
    {
        return !hasNext();
    }

    @Override
    public boolean isFirst() throws SQLException
    {
        return row == 0;
    }

    @Override
    public boolean isLast() throws SQLException
    {
        return !hasNext();
    }

    @Override
    public void beforeFirst() throws SQLException
    {
        throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
    }
    
    @Override
    public void afterLast() throws SQLException
    {
        throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
    }

    @Override
    public boolean first() throws SQLException
    {
        throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
    }

    @Override
    public boolean last() throws SQLException
    {
        throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
    }

    @Override
    public int getRow() throws SQLException
    {
        return row;
    }

    @Override
    public boolean absolute( int i ) throws SQLException
    {
        throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
    }

    @Override
    public boolean relative( int i ) throws SQLException
    {
        throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
    }

    @Override
    public boolean previous() throws SQLException
    {
        throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
    }

    @Override
    public void setFetchDirection( int i ) throws SQLException
    {
        if ( i != ResultSet.FETCH_FORWARD )
        {
            throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
        }
    }

    @Override
    public int getFetchDirection() throws SQLException
    {
        return ResultSet.FETCH_FORWARD;
    }

    @Override
    public void setFetchSize( int i ) throws SQLException
    {
    	throw new SQLException( "setFetchSize is not supported" );
    }

    @Override
    public int getFetchSize() throws SQLException
    {
        throw new SQLException( "getFetchSize is not supported" );
    }

    @Override
    public int getType() throws SQLException
    {
        return ResultSet.TYPE_FORWARD_ONLY;
    }

    @Override
    public boolean isClosed() throws SQLException
    {
        return super.isClosed();
    }

    @Override
    public String toString()
    {
    	logger.debug(currentRow.values());
        return super.toString() + " current row " + row + ": " + Arrays.toString( (currentRow.values()).toArray() );
    }

}
