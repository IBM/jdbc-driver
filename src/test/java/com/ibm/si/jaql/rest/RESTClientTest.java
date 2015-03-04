package com.ibm.si.jaql.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.ibm.si.jaql.api.ArielException;
import com.ibm.si.jaql.rest.RESTClient.Result;
import com.ibm.si.jaql.util.BaseTest;

public class RESTClientTest extends BaseTest
{
	static final Logger logger = LogManager.getLogger(RESTClientTest.class.getName());
	
	@Test
	public void testConnection()
	{
		try
		{
			final RESTClient c = new RESTClient(_properties.getProperty(IP), _properties.getProperty(USER), _properties.getProperty(PASSWORD));
			Result result = null;
			
			result = c.doGet("/api/referencedata/mapOfSets");
			assertNotNull(result);
			assertNotNull(result.getBody());
			assertEquals(result.getStatus(), 200);
			logger.debug(String.format("Result was %s", result));
		}
		catch (IOException e)
		{
			fail(e.getMessage());
		}
		catch (ArielException ae)
		{
			fail(ae.getMessage());
		}
		
		
	}
}
