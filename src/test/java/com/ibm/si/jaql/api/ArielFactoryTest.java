package com.ibm.si.jaql.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ibm.si.jaql.util.BaseTest;

public class ArielFactoryTest extends BaseTest
{
	static final Logger logger = LogManager.getLogger(ArielFactoryTest.class.getName());
	
	@Test
	public void shouldGetDatabaseOnValidIP()
	{
		
		try
		{
			final IArielDatabase db = ArielFactory.getArielDatabase(_properties.getProperty("ip"), 
					_properties.getProperty("user"), 
					_properties.getProperty("password"));
			
			assertNotNull(db);
			
			final String[] dbs = db.listDatabases();
			assertNotNull(dbs);
			assertTrue( dbs.length >= 3);
			for (final String dbName : dbs)
			{
				logger.debug(String.format("Database : %s", dbName));
			}
			
			final IArielConnection conn = db.createConnection();
			assertNotNull(conn);
		}
		catch (ArielException e)
		{
			fail(e.getMessage());
		}
	}
	
	
	@Rule public ExpectedException thrown= ExpectedException.none();
	@Test 
	public void shouldNotGetDatabaseInValidIP() throws Exception
	{
		thrown.expect( ArielException.class );
	    @SuppressWarnings({ "unused" })
	    final IArielDatabase db = ArielFactory.getArielDatabase("255.255.255.1", 
				_properties.getProperty("user"), 
				_properties.getProperty("password"));
	}
	
	
	@Test
	public void shouldListSearches()
	{
		try
		{
			final IArielDatabase db = ArielFactory.getArielDatabase(_properties.getProperty("ip"), 
					_properties.getProperty("user"), 
					_properties.getProperty("password"));
			assertNotNull(db);
			
			final String[] dbs = db.listDatabases();
			assertNotNull(dbs);			
			final IArielConnection conn = db.createConnection();
			assertNotNull(conn);
			final String[] searches = conn.listSearches();
			assertNotNull(searches);
			assert(searches.length > 0);
			for (final String search : searches)
			{
				logger.debug(String.format("Search :: %s", search));
			}
		}
		catch (ArielException e)
		{
			fail(e.getMessage());
		}
	}	
}
