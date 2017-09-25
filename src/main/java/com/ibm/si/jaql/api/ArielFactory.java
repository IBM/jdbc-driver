package com.ibm.si.jaql.api;

import java.util.Properties;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ibm.si.jaql.rest.ArielDatabase;

/**
 * Factory for returning the Ariel Database
 *  
 * @author IBM
 *
 */
public class ArielFactory
{
	private static Map<String,IArielDatabase> dbCache = null;
	
	static
	{
		dbCache = new ConcurrentHashMap<String,IArielDatabase>();
	}
	
	public static IArielDatabase getArielDatabase(
		final String ip,
		final String user,
		final String password,
    final Properties props) throws ArielException
	{
		return getArielDatabase(ip,user,password,443,props);
	}
	public static IArielDatabase getArielDatabase(
			final String ip,
			final String user,
			final String password,
			final int port,
      final Properties props) throws ArielException
	{
		IArielDatabase result = dbCache.get(ip);
		if (result == null)
		{
			result = new ArielDatabase(ip, user, password,port,props);
			dbCache.put(ip, result);
		}
		
		return result;
	}
  public static IArielDatabase getArielDatabase(
    final String ip,
    final String auth_token,
    final Properties props) throws ArielException
  {
    return getArielDatabase(ip,auth_token,443,props);
  }
  public static IArielDatabase getArielDatabase(
    final String ip,
    final String auth_token,
    int port,
    final Properties props) throws ArielException
  {
    IArielDatabase result = dbCache.get(ip);
    if (result == null)
    {
      result = new ArielDatabase(ip, auth_token, port,props);
      dbCache.put(ip, result);
    }
    return result;
  }
}
