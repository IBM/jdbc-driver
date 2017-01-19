package com.ibm.si.jaql.jdbc4;

import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import com.ibm.si.jaql.Driver;
import com.ibm.si.jaql.api.ArielException;
import com.ibm.si.jaql.api.ArielFactory;
import com.ibm.si.jaql.api.IArielConnection;
import com.ibm.si.jaql.api.IArielDatabase;
import com.ibm.si.jaql.api.pojo.ArielResult;
import com.ibm.si.jaql.api.pojo.ArielSearch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Query execution implementation against the Ariel datastore
 * @author IBM
 *
 */
public class QueryExecutor implements IQueryExecutor{
  private static Logger logger = LogManager.getLogger();
	private IArielDatabase arielDB;
	
	/**
	 * Establish endpoint connections, based on user defined jdbc driver connection details 
	 * @param url
	 * @param info
	 * @throws SQLException
	 */
	public QueryExecutor(String url, Properties info) throws SQLException
	{
		try
		{
      if (info.get(Driver.PORT) == null)
        info.put(Driver.PORT, 443);
      if (info.getProperty(Driver.AUTH_TOKEN) == null)
  			arielDB = ArielFactory.getArielDatabase(info.getProperty(Driver.SERVER),
  											info.getProperty(Driver.USER),
  											info.getProperty(Driver.PASSWORD),
  											(Integer)info.get(Driver.PORT));
      else
        arielDB = ArielFactory.getArielDatabase(info.getProperty(Driver.SERVER),
          info.getProperty(Driver.AUTH_TOKEN), (Integer)info.get(Driver.PORT));
		}
		catch (NullPointerException e)
		{
			throw new SQLException("Invalid ariel database connections detail",e);
		}
		catch (Exception e)
		{
			throw new SQLException("Could not obtain ariel connection",e);
		}
	}

	
	/**
	 * Main query execution method for connections/statements/preparedstatements etc
	 * Phases:
	 * 1. Create a connection to the Ariel endpoints
	 * 2. Create a search on ariel data
	 * 3. Get ariel search data results
	 * 4. delete the ariel search
	 *  
	 */
	public ArielResult executeQuery(String query, Map<String, Object> parameters)
			throws SQLException {

		//create connection
		IArielConnection arielCon;
		try {
			arielCon = arielDB.createConnection();
		} catch (ArielException e) {
			throw new SQLException( "Error getting Ariel database connection.", e );
		}
		//create search
		ArielSearch search;
		try {
			//search = arielCon.createSearch(StatementParser.removeUnsupportedSchemas(query));
			search = arielCon.createSearch(query);
		} catch (ArielException e) {
			 throw new SQLException( "Error creating Ariel search.", e );
		}
		
		//execute search, plugging in default param values if none passed through
		ArielResult results;
    
		try {
			results = arielCon.getSearchResults(search.getSearchId(), 
												(Integer)parameters.get("start") ==null ? -1 : (Integer)parameters.get("start"),
												(Integer)parameters.get("end") ==null ? -1 : (Integer)parameters.get("end"),
												(Boolean)parameters.get("blocking") ==null ? true : (Boolean)parameters.get("blocking"));
		} catch (ArielException e) {
			throw new SQLException( "Error executing Ariel search.", e );
		}
		
		//delete search
		try {
			arielCon.deleteSearch(search.getSearchId());
		} catch (ArielException e) {
      logger.warn("Error removing Ariel search.");
		}
		
		return results;
	}
	
	public IArielDatabase getDatabase()
	{
		return this.arielDB;
	}
}
