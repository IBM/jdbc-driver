package com.ibm.si.jaql.rest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.si.jaql.Driver;
import com.ibm.si.jaql.api.ArielException;
import com.ibm.si.jaql.api.pojo.ArielColumn;
import com.ibm.si.jaql.api.pojo.ArielMetaData;

/**
 * Ariel functions metadata handler
 * @author IBM
 *
 */
public class FunctionMetaData
{
	static final Logger logger = LogManager.getLogger(FunctionMetaData.class.getName());
	private static Map<String,String> functionTypeMap;
	
	private FunctionMetaData() {
		loadMetaMap();
	}
	
	/* Thread safe lazy instance creation
     */
    private static class LazyHolder 
    {
    	static 
    	{
    		logger.debug("FunctionMetaData static initializer");
		}
    	
        private static final FunctionMetaData INSTANCE = new FunctionMetaData() ;
        
    }
    
    public static FunctionMetaData getInstance() 
    {
    	logger.debug("FunctionMetaData.getInstance() >>>");
        return LazyHolder.INSTANCE;
    }
    
    	
	//public void loadMetaMap(Map<String, ArielColumn> funcsMetaData)
    public void loadMetaMap()
	{
		functionTypeMap = new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
		loadFunctionMetaData();
		
		//add in aggregate functions from QRadar that aren't surfaced by the functions restapi endpoint, and the scalarfuction annotation set
		functionTypeMap.put("count", "DOUBLE");
		functionTypeMap.put("first", "DOUBLE");
		functionTypeMap.put("sum", "DOUBLE");
		functionTypeMap.put("avg", "DOUBLE");
		functionTypeMap.put("min", "DOUBLE");
		functionTypeMap.put("max", "DOUBLE");
		functionTypeMap.put("uniquecount", "DOUBLE");
	}
	
	
	/**
	 * Ariel functions, require a seperate endpoint to be inspected to gather the metadata
	 * @throws ArielException
	 */
	protected void loadFunctionMetaData()
	{
		//read static mapping file  
		final InputStream fstream = Driver.class.getResourceAsStream("/functions.json");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader  br = new BufferedReader(new InputStreamReader(in));
	
		//parse to output object
		Gson gson = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.create();
		final ArielMetaData columns = gson.fromJson(br, ArielMetaData.class);
			
		//iterate and add to map
		final Iterator<ArielColumn> itr = columns.getColumns().iterator();
		while (itr.hasNext())
		{
			final ArielColumn column = itr.next();
			String name = column.getName();
			String type = column.getArgumentType();
			logger.debug("FunctionMetaData.loadFunctionMetaData()>> add name=" + name + " type=" + type);
			functionTypeMap.put(name, type);
		}
		
	}
	
	public Map<String,String> getTypes()
	{
		return functionTypeMap;
	}
	
	public String getType(final String name)
	{
		String result = null;
		
		logger.debug(String.format("FUNCTION :: %s", name));
		result = functionTypeMap.get(name.toLowerCase());
		logger.debug(String.format("FUNCTION :: %s, result:%s", name, result));

		return result;
	}
}
