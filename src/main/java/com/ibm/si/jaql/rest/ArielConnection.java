package com.ibm.si.jaql.rest;


import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.si.jaql.api.ArielException;
import com.ibm.si.jaql.api.IArielConnection;
import com.ibm.si.jaql.api.pojo.ArielColumn;
import com.ibm.si.jaql.api.pojo.ArielMetaData;
import com.ibm.si.jaql.api.pojo.ArielResult;
import com.ibm.si.jaql.api.pojo.ArielSearch;
import com.ibm.si.jaql.rest.Result;

/**
 * Ariel datastore connection object
 * Use endpoints to get tables, post and run searches, get results and metadata
 * 
 * @author IBM
 *
 */
public class ArielConnection implements IArielConnection
{
	static final Logger logger = LogManager.getLogger(ArielConnection.class.getName());
	
	private Gson gson = null;
	private RESTClient rawClient = null;
	private Map<String,ArielColumn> metaData = null;
	private Map<String,Map<String,ArielColumn>> metaDataByDb = null;
	private long lastMetaDataPull = 0;
	public ArielConnection(final RESTClient rawClient) throws ArielException
	{
		gson = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.registerTypeAdapter(ArielResult.class, new ArielResultGSONAdapter(this))
			.create();
		this.rawClient = rawClient;
		metaData = new HashMap<String,ArielColumn>();
    if (metaDataByDb == null)
  		metaDataByDb = new HashMap<String,Map<String,ArielColumn>>();
		
		loadColumnMetaData();
	}

		public ArielSearch createSearch(final String query) throws ArielException
	{
		ArielSearch result = null;
		final Map<String,String> queryMap = new HashMap<String,String>();
		queryMap.put("query_expression", query);
		Result rawResult;
		
		try
		{
			rawResult = rawClient.doPost("/api/ariel/searches", queryMap);
			if (null != rawResult)
			{
				result = gson.fromJson(rawResult.getBody(), ArielSearch.class);
			}
			else
			{
				throw new IllegalStateException("RESTClient request did not return anything");
			}
		}
		catch (final IOException e)
		{
			throw new ArielException(e);
		}
		
		return result;
	}

		public void deleteSearch(final String searchId) throws ArielException
	{
		Result rawResult;
		
		try
		{
			rawResult = rawClient.doDelete(String.format("/api/ariel/searches/%s", searchId));
			logger.debug("DW>delete search return status=" +rawResult.getStatus());
			if (! (rawResult.getStatus() == HttpStatus.SC_ACCEPTED	|| rawResult.getStatus() == HttpStatus.SC_NO_CONTENT ))
			{
				throw new ArielException(String.format("Delete failed for search id %s, with status code %d", searchId, rawResult.getStatus()));
			}
		}
		catch (IOException e)
		{
			throw new ArielException(e);
		}
	}

		public String[] listSearches() throws ArielException
	{
		String[] result = null;
		Result rawResult;
		
		try 
		{
			rawResult = rawClient.doGet("/api/ariel/searches");
			if (null != rawResult 
					&& rawResult.getStatus() == HttpStatus.SC_OK)
			{
				result = gson.fromJson(rawResult.getBody(), String[].class);
			}
			else
			{
				throw new ArielException(String.format("RESTClient request did not return anything, with status %d", rawResult.getStatus()));
			}
		}
		catch (IOException e)
		{
			throw new ArielException(e);
		}
		
		return result;
	}

		public ArielSearch getSearch(final String id) throws ArielException
	{
		ArielSearch result = null;
		Result rawResult;
		
		try
		{
			rawResult = rawClient.doGet(String.format("/api/ariel/searches/%s", id));
			if (null != rawResult
					&& rawResult.getStatus() == HttpStatus.SC_OK)
			{
				result = gson.fromJson(rawResult.getBody(), ArielSearch.class);
			}
			else
			{
				throw new ArielException(String.format("RESTClient request did not return anything, with status %d", rawResult.getStatus()));
			}
		}
		catch (IOException e)
		{
			throw new ArielException(e);
		}
		
		return result;
	}
  
  public ArielResult getSearchResults(String searchId) throws ArielException {
    int batchSize = 5;
    int start = 0;
    int end = batchSize - 1;
    ArielResult result = getSearchResults(searchId, start, end, true);
    start = end + 1;
    end = start + batchSize - 1;
    while (result.getTotal() > start) {
      ArielResult r2 = getSearchResults(searchId, start, end, true);
      result.merge(r2);
      start = end + 1;
      end = start + batchSize - 1;
    }
    return result;
  }
  
  public ArielResult getSearchResults(String searchId, int start, int end, boolean blocking) throws ArielException {
    if (start == -1 || end == -1)
      return getSearchResults(searchId);
    ArielResult result = null;
    Result rawResult = null;
    Pattern pattern = Pattern.compile("^items (\\d+)-(\\d+)/(\\d+)$");
    try {
      Properties p = new Properties();
      p.setProperty("Range", String.format("items=%d-%d", start, end));
      final BlockingActionWorker worker = new BlockingActionWorker(rawClient, String.format("/api/ariel/searches/%s/results", searchId), p);
      final Thread t = new Thread(worker);
      t.start();
      t.join();
      
      rawResult = worker.getResult();
      if (null != rawResult && rawResult.getStatus() == HttpStatus.SC_OK) {
        logger.trace("Raw Json Body: {}", rawResult.getBody());
        Matcher m = pattern.matcher(rawResult.getHeader("Content-Range"));
        logger.debug("Returned range: {} {}", rawResult.getHeader("Content-Range"), m.matches() ? m.group(3) : -1);
        result = gson.fromJson(rawResult.getBody(), ArielResult.class);
        if (m.matches())
          result.setTotal(Integer.parseInt(m.group(3)));
      } else {
        if (null == rawResult) {
          logger.fatal("The result set returned was null");
          throw new ArielException(String.format("Failed to retrieve results for searchId %s with returned null", searchId));
        } else {
          logger.fatal(String.format("Server returned code %d: %s", rawResult.getStatus(), rawResult.getBody()));
        }
        throw new ArielException(String.format("Failed to retrieve results for searchId %s with return code %d, uniquecode %d", searchId, rawResult.getStatus(), rawResult.getCode() ));
      }
    } catch (InterruptedException e) {
      throw new ArielException(e);
    }
    return result;
  }

		public void close()
	{
	}
	
	public Map<String,ArielColumn> getTableMetaData(final String db) throws ArielException
	{
		return this.metaDataByDb.get(db);
	}
	
	public ArielColumn getColumnMetaData(final String key) throws ArielException
	{
		ArielColumn result = null;
		
		result = metaData.get(key.toLowerCase());
		
		return result;
	}
	
	protected void loadColumnMetaData() throws ArielException
	{
    if (System.currentTimeMillis() - lastMetaDataPull < 1000*60*60*24) {
      logger.debug("Using cached table metadata");
      return;
    }
		final String[] dbs = {"flows", "events", "simarc"};
		for (final String db : dbs)
		{
			Map<String, ArielColumn> dbMetaData = new HashMap<String, ArielColumn>();
			
			try
			{
        // TODO There are two issues here: 1. QRadar returns the wrong types and 2. The results may be wrong due to aliasing
        logger.debug("Getting table metadata for {}", db);
				final Result res = rawClient.doGet(String.format("/api/ariel/databases/%s", db));
				if (res != null)
				{
					final String body = res.getBody();
					final ArielMetaData columns = gson.fromJson(body, ArielMetaData.class);
					final Iterator<ArielColumn> itr = columns.getColumns().iterator();
					
					while (itr.hasNext())
					{
						final ArielColumn column = itr.next();
						final String name = column.getName();
						metaData.put(name, column);
						dbMetaData.put(name, column);
						logger.trace(String.format("loadColumnMetaData: Loaded column %s for %s with argType %s", column.getName(), db, column.getArgumentType()));
					}
				} else
          logger.warn("Table metadata result was null");
				
				this.metaDataByDb.put(db, dbMetaData);
        lastMetaDataPull = System.currentTimeMillis();
			}
			catch (final IOException e)
			{
        logger.warn("IOException getting metadata:{}",e);
				throw new ArielException(e);
			}
		}
	}
}
