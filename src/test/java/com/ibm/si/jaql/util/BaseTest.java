package com.ibm.si.jaql.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Extendable Base Test, for unit testing (junit)
 * Loads a properties file, of the same shortname as the super class test class, if that file is relative to the super test class
 * 
 * e.g. com.acme.TestMe.class will try to load com/acme/TestMe.properties
 *
 */
public class BaseTest {
	
	static final Logger logger = LogManager.getLogger(BaseTest.class.getName());
	private final static String PROPERTIES_FILE_EXT = ".properties";
	protected final static String IP = "ip";
	protected final static String USER = "user";
	protected final static String PASSWORD = "password";
	protected final static String AUTH_TOKEN = "auth_token";
	protected Properties _properties = new Properties();
	
	public BaseTest() {
		String fileName = this.getClass().getSimpleName()  + PROPERTIES_FILE_EXT;
		
		try 
		{
			_properties.load(this.getClass().getResourceAsStream(fileName));
		} 
		catch (IOException e) 
		{
			logger.debug("Perfectly fine to not find a properties file for each super test class");
		}
	}

}
