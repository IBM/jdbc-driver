package com.ibm.si.jaql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.jdbc4.Jdbc4Connection;

/**
 * Base jdbc driver class for all things Ariel data related
 * @author IBM
 *
 */
public class Driver implements java.sql.Driver
{
	static final Logger logger = LogManager.getLogger(Driver.class.getName());
	
	private final static String BUILD_PROPERTIES_FILE_EXT = "/build.properties";
	private final static String DEF_CONN_PROPERTIES_FILE_EXT = "/default.connect.properties";
	private final static String PROP_MAJOR = "major";
	private final static String PROP_MINOR = "minor";
	private final static Properties _buildProperties = new Properties();
	private final static Properties _defaultConnectProperties = new Properties();
    private final static String DRIVER_PREFIX = "jdbc:qradar:";
    
    public static final String SERVER       = "prop.server";
    public static final String URL       	= "prop.url";
    public static final String USER       	= "prop.user";
    public static final String PASSWORD 	= "prop.password";

	static
	{
		//System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		try 
		{
			final InputStream is = Driver.class.getResourceAsStream(BUILD_PROPERTIES_FILE_EXT);
			if (is != null)
			{
				_buildProperties.load(is);
			}
		} 
		catch (IOException e) 
		{
			logger.info("Perfectly fine to not find a properties file for each super test class");
		}
		
		try 
		{
			final InputStream is = Driver.class.getResourceAsStream(DEF_CONN_PROPERTIES_FILE_EXT);
			if (is != null)
			{
				_defaultConnectProperties.load(is);
			}
		} 
		catch (IOException e) 
		{
			 logger.error(e.getMessage(), e);
		}
		
		try
		{
			java.sql.DriverManager.registerDriver(new Driver());
		}
		catch (final SQLException e)
		{
			logger.error(e.getMessage(), e);
		}
	}
	
	@Override
	public Connection connect(String url, Properties info) throws SQLException
	{
        Properties props = new Properties(_defaultConnectProperties);
        
        if (info != null)
        {
        	for(String key : info.stringPropertyNames()) {
          	  String value = info.getProperty(key);
          	  props.setProperty( key, value);
          	}
        }
        
        if ((props = parseURL(url, props)) == null)
        {
            return null;
        }
        
		return new Jdbc4Connection(url, props);
	}

	@Override
	public boolean acceptsURL(final String url) throws SQLException
	{
		if (url == null) {
			return false;
		}
		
		return (parseURL(url, null) != null);

	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
			throws SQLException
	{
		final DriverPropertyInfo[] result = new DriverPropertyInfo[0];
		return result;
	}
	
    /**
     * Returns the driver version.
     * @return the driver version
     */
    public static final String getVersion() {
        return _buildProperties.getProperty( PROP_MAJOR ) + "." + _buildProperties.getProperty( PROP_MINOR );
    }

	@Override
	public int getMajorVersion() {
		int intMajor = Integer.parseInt(_buildProperties.getProperty( PROP_MAJOR ));
		return intMajor;
    }

	@Override
	public int getMinorVersion() {
		int intMajor = Integer.parseInt(_buildProperties.getProperty( PROP_MINOR ));
		return intMajor;
	}

	@Override
	public boolean jdbcCompliant() {
		return false;
	}

	@Override
	public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new AbstractMethodError();
	}
	
	/**
	 * Parse url string provided to match format:
	 * 	"jdbc:qradar[://<host>?prop1:value1]" 
	 * the url may contain other option querystring params, which are loaded as properties, overwriting
	 * and default properties
	 * 
	 * @param url
	 * @param info
	 * @return
	 */
	private Properties parseURL(String url, Properties info){
		if (url == null
				|| !url.toLowerCase().startsWith(DRIVER_PREFIX)) {
			return null;
		}
		
		return processURL(url, info);
	}
	
	private Properties processURL(String url, Properties info){
		//set the properties up based on passed through props, overwrite pops with url props
		Properties props = (info != null) ? new Properties(info) : new Properties();

		//split url into portions
		String protocolServerPort = "";
		//String urlArgs = "";
		int qsParamsIdx = url.indexOf('?');
        if (qsParamsIdx != -1)
        {
            protocolServerPort = url.substring(0, qsParamsIdx);
            //urlArgs = url.substring(qsParamsIdx + 1);
        }
        else
        {
        	protocolServerPort = url;
        }
        logger.debug("protocolServerPort ==>"+ protocolServerPort);
        
        //deal with host and port params
        String urlServer = url.substring(DRIVER_PREFIX.length());
        logger.debug("urlServer ==>"+ urlServer);
			
		if (urlServer.startsWith("//")) 
		{
			urlServer = urlServer.substring(2);
            int slashIndex = urlServer.indexOf('/');
            //if (slashIndex == -1) 
            //{
            //    return null;
            //}
            
            if (slashIndex != -1)
            {
	            String hostPort= urlServer.substring(0, slashIndex);
	            
	            int colonIdx = hostPort.lastIndexOf(':');
	            String server = "";
	            if (colonIdx != -1)
	            {
	                server = hostPort.substring(0, colonIdx);
	                String port = hostPort.substring(colonIdx + 1);
	                try {
	                    Integer.parseInt(port);
	                } catch (NumberFormatException ex) {
	                    return null;
	                }
	            }
	            else
	            {
	            	server = hostPort;
	            }
	            logger.debug("server ==>"+ server);
	            props.setProperty(SERVER, server);
            }
		}
		
		// try to setup our must have connection properties
		if (info != null
				&& protocolServerPort  != null)
		{
			props.setProperty(URL, protocolServerPort);
			
			String user = info.getProperty("user");
			if (user != null  && !user.isEmpty() )
				props.setProperty(USER, user);
			
			String password = info.getProperty("password");
			if (password != null  && !password.isEmpty() )
				props.setProperty(PASSWORD, password );
		}
		
		return props;
	}
	
}
