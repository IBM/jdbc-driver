package com.ibm.si.jaql.rest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ibm.si.jaql.api.ArielException;
import com.ibm.si.jaql.api.ArielFactoryTest;
import com.ibm.si.jaql.api.IArielConnection;
import com.ibm.si.jaql.api.pojo.ArielResult;
import com.ibm.si.jaql.api.pojo.ArielSearch;
import com.ibm.si.jaql.util.BaseTest;


public class ArielDatabaseTest extends BaseTest {
	
	static final Logger logger = LogManager.getLogger(ArielFactoryTest.class.getName());

	@Test
	public void testListDatabases()
	{
		logger.debug(String.format("testListDatabases>>>"));
		try {
			ArielDatabase db = new ArielDatabase(_properties.getProperty(IP), _properties.getProperty(USER), _properties.getProperty(PASSWORD));
			String[] dbs;
			dbs = db.listDatabases();
			assertNotNull(dbs);
			for (String dbStr : dbs)
			{
				logger.debug(dbStr);
			}			
		} catch (ArielException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testListSearches()
	{
		logger.debug(String.format("testListSearches>>>"));
		try {
			ArielDatabase db = new ArielDatabase(_properties.getProperty(IP), _properties.getProperty(USER), _properties.getProperty(PASSWORD));
			IArielConnection conn;
			conn = db.createConnection();
			String[] dbs = conn.listSearches();
			assertNotNull(dbs);
			for (String dbStr : dbs)
			{
				logger.debug(dbStr);
			}			
		} catch (ArielException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetSearch()
	{
		logger.debug(String.format("testGetSearch>>>"));
		try
		{
			ArielDatabase db = new ArielDatabase(_properties.getProperty(IP), _properties.getProperty(USER), _properties.getProperty(PASSWORD));
			
			IArielConnection conn = db.createConnection();
			String[] dbs = conn.listSearches();
			assertNotNull(dbs);
			for (String dbStr : dbs)
			{
				ArielSearch aql = conn.getSearch(dbStr);
				logger.debug(String.format("GetSearch :: %s", aql.getSearchId()));
			}
		}
		catch( ArielException ex)
		{
			fail(ex.getMessage());
		}
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	 
	@Test
	public void testGetSearchResultsForNonExistingSearch() throws ArielException
	{
			logger.debug(String.format("testGetSearchResultsForNonExistingSearch>>> for search-id='does-not-exist-search-id' "));
			ArielDatabase db = new ArielDatabase(_properties.getProperty(IP), _properties.getProperty(USER), _properties.getProperty(PASSWORD));
			assertNotNull(db);
			IArielConnection conn = db.createConnection();
			assertNotNull(conn);
			
			thrown.expect(ArielException.class);
			thrown.expectMessage("Failed to retrieve results for searchId does-not-exist-search-id with return code 404, uniquecode 1002");
		    
			@SuppressWarnings("unused")
			final ArielResult aql = conn.getSearchResults("does-not-exist-search-id", 1, 10, true);
			fail("we shouldnt have got to this line, I expect an ArielException at this point !");
	}
	
	
	/*Commenting out for now,.
	 * system with huge amounts of searches, this test takes a hugely long time
	 * Occasionally and inevtiable, the test will also fail if searches are delete during the contxt o frunning teh entire suite of serach tests
	 *  *Maven test configuration to run the tests forked in parallel can help
	@Test
	public void testGetAllSearchResults()
	{
		try
		{
			logger.debug(String.format("testGetSearchResults>>>"));
			ArielDatabase db = new ArielDatabase(_properties.getProperty(IP), _properties.getProperty(USER), _properties.getProperty(PASSWORD));
			assertNotNull(db);
			IArielConnection conn = db.createConnection();
			assertNotNull(conn);
			final String[] dbs = conn.listSearches();
			assertNotNull(dbs);
			logger.debug(dbs.length);
			
			for (final String search : dbs)
			{	
				logger.debug(String.format("search=%s",search));
				final ArielResult aql = conn.getSearchResults(search, 1, 10, true);
				assertNotNull(aql);
				List<Map<String,ColumnTuple>> results = aql.getResults();
				Iterator<Map<String,ColumnTuple>> itr = results.iterator();
				while (itr.hasNext())
				{
					final Map<String,ColumnTuple> map = itr.next();
					final Iterator<String> sItr = map.keySet().iterator();
					while (sItr.hasNext())
					{
						final String key = sItr.next();
						logger.debug(String.format("Map (%s :: %s)", key, map.get(key).getType()));
					}
				}
			}
		}
		catch( ArielException ex)
		{
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
	*/
	
	@Test
	public void testCreateSearch()
	{
		try
		{
			logger.debug(String.format("testCreateSearch>>>"));
			ArielDatabase db = new ArielDatabase(_properties.getProperty(IP), _properties.getProperty(USER), _properties.getProperty(PASSWORD));
			IArielConnection conn = db.createConnection();
			ArielSearch id = conn.createSearch("select * from events");
			logger.debug(String.format("Created query id :: %s", id.getSearchId()));
			conn.deleteSearch(id.getSearchId());
		}
		catch( ArielException ex)
		{
			fail(ex.getMessage());
		}
	}
	
}
