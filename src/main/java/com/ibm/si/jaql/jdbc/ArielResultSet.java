package com.ibm.si.jaql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.api.pojo.ArielResult;
import com.ibm.si.jaql.api.pojo.ColumnTuple;
import com.ibm.si.jaql.jdbc4.Jdbc4Connection;


/**
 * Ariel datastore jdbc compliant result set handler
 * @author IBM
 *
 */
public class ArielResultSet extends AbstractResultSet{
	
	static final Logger logger = LogManager.getLogger(ArielResultSet.class.getName());
	
	private Map<String,ColumnTuple> currentRow;
	private ListIterator<Map<String,ColumnTuple>> resultsListIter;
    private int row = -1;

    public ArielResultSet( final Jdbc4Connection conn, final List<Map<String,ColumnTuple>> results, final ArielResult arielResult)
    {
    	super(conn, results, arielResult);
        resultsListIter = results.listIterator();
        resultsListIter.hasNext();
    }
    
    
    protected Map<String,ColumnTuple> currentRow()
    {
        return currentRow;
    }
    
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
    
    
        public boolean isBeforeFirst() throws SQLException
    {
        return row == -1;
    }

        public boolean isAfterLast() throws SQLException
    {
        return !hasNext();
    }

        public boolean isFirst() throws SQLException
    {
        return row == 0;
    }

        public boolean isLast() throws SQLException
    {
        return !hasNext();
    }

        public void beforeFirst() throws SQLException
    {
        throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
    }
    
        public void afterLast() throws SQLException
    {
        throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
    }

        public boolean first() throws SQLException
    {
        throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
    }

        public boolean last() throws SQLException
    {
        throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
    }

        public int getRow() throws SQLException
    {
        return row;
    }

        public boolean absolute( int i ) throws SQLException
    {
        throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
    }

        public boolean relative( int i ) throws SQLException
    {
        throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
    }

        public boolean previous() throws SQLException
    {
        throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
    }

        public void setFetchDirection( int i ) throws SQLException
    {
        if ( i != ResultSet.FETCH_FORWARD )
        {
            throw new SQLException( "Result set type is TYPE_FORWARD_ONLY" );
        }
    }

        public int getFetchDirection() throws SQLException
    {
        return ResultSet.FETCH_FORWARD;
    }

        public void setFetchSize( int i ) throws SQLException
    {
    	throw new SQLException( "setFetchSize is not supported" );
    }

        public int getFetchSize() throws SQLException
    {
        throw new SQLException( "getFetchSize is not supported" );
    }

        public int getType() throws SQLException
    {
        return ResultSet.TYPE_FORWARD_ONLY;
    }

        public boolean isClosed() throws SQLException
    {
        return super.isClosed();    
    }

        public String toString()
    {
    	logger.debug(currentRow.values());
        return super.toString() + " current row " + row + ": " + Arrays.toString( (currentRow.values()).toArray() );
    }

}
