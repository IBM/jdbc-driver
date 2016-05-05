package com.ibm.si.jaql.rest;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.si.jaql.api.ArielException;
import com.ibm.si.jaql.api.IArielConnection;
import com.ibm.si.jaql.api.IArielDatabase;
import com.ibm.si.jaql.api.pojo.ArielColumn;
import com.ibm.si.jaql.api.pojo.ArielMetaData;
import com.ibm.si.jaql.rest.RESTClient.Result;

/**
 * Ariel Database wrapper
 * Allows the creation of connection objects to be passed onto statements/preparedstatements etc, to enable ariel query strings to be executed
 * Offers methods to get database metadata
 * 
 * @author IBM
 *
 */
public class ArielDatabase implements IArielDatabase
{
	static final Logger logger = LogManager.getLogger(ArielConnection.class.getName());
	
	private RESTClient apiClient = null;
	private Gson gson = null;
	private String ip = null;
	private String userName = null;
	private String password = null;
	private Map<String,ArielColumn> metaData = null;
	private Map<String,Map<String,ArielColumn>> metaDataByDb = null;	
	private int port=443;
	
	/**
	 * Create the database, getting from ariel endpoints the column metadata for all tables (events/flows/simarc), and ariel functions
	 * @param ip
	 * @param user
	 * @param password
	 */
	public ArielDatabase(String ip, String user, String password,int port) throws ArielException
	{
		this.ip = ip;
		this.userName = user;
		this.password = password;
		this.port = port;
		apiClient = new RESTClient(this.ip, this.userName, this.password,this.port);
		gson = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.create();
		
		metaData = new HashMap<String,ArielColumn>();
		metaDataByDb = new HashMap<String,Map<String,ArielColumn>>();
		
		loadColumnMetaData();

	}
	
	/**
	 * get the databases, effectively rbdms tables 
	 */
	@Override
	public String[] listDatabases() throws ArielException
	{
		String[] result = null;
		
		try
		{
			final Result res = apiClient.doGet("/api/ariel/databases");
			if (null != res)
			{
				logger.debug("listDatabases:" + res.getBody());
				if (res.getStatus() == HttpStatus.SC_OK)
				{
					result = gson.fromJson(res.getBody(), String[].class);
				}
			}
			else
			{
				throw new IllegalStateException("RESTClient request did not return anything");
			}
		}
		catch (SocketException e)
		{
			throw new ArielException(e);
		}
		catch (IOException e)
		{
			throw new ArielException(e);
		}
		
		return result;
	}

	/**
	 * Create the actual connection to ariel, via rest api endpoints over a http client 
	 */
	@Override
	public IArielConnection createConnection() throws ArielException
	{
		ArielConnection result = null;
		final RESTClient client = new RESTClient(ip, userName, password,port);
		result = new ArielConnection(client);
		return result;
	}
	
	public ArielColumn getColumnMetaData(final String key) throws ArielException
	{
		ArielColumn result = null;
		
		result = metaData.get(key);
		
		return result;
	}
	
	/**
	 * Use API endpoints to build metdata, for the driver
	 * @throws ArielException
	 */
	protected void loadColumnMetaData() throws ArielException
	{
		final String[] dbs = listDatabases();
		for (final String db : dbs)
		{
			Map<String, ArielColumn> dbMetaData = new HashMap<String, ArielColumn>();
			
			try
			{
				final Result res = apiClient.doGet(String.format("/api/ariel/databases/%s", db));
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
					}
				}
			}
			catch (final IOException e)
			{
				throw new ArielException(e);
			}
			
			metaDataByDb.put(db, dbMetaData);
		}
	}
	

	@Override
	public Map<String, ArielColumn> getMetaData(String tableName) throws ArielException
	{
		return metaDataByDb.get(tableName);
	}

	@Override
	public Map<String, String> getFunctionMetaData()
	{
		return FunctionMetaData.getInstance().getTypes();
	}	
}
