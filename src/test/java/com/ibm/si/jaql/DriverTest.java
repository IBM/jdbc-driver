package com.ibm.si.jaql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.ibm.si.jaql.util.BaseTest;

public class DriverTest  extends BaseTest {
	
	static final Logger logger = LogManager.getLogger(DriverTest.class.getName());
	
	
	/**
     * Test to ensure that the major version reported by the driver matches the build process
     */
	@Test
	public void testMajorVersion()
	{
		// Load the driver (note clients should never do it this way!)
		Driver drv = new Driver();
        assertNotNull(drv);
        assertEquals(drv.getMajorVersion(),0);
	}
	
	/**
     * Test to ensure that the minor version reported by the driver matches the build process
     */
	@Test
	public void testMinorVersion()
	{
		// Load the driver (note clients should never do it this way!)
		Driver drv = new Driver();
        assertNotNull(drv);
        assertEquals(drv.getMinorVersion(),1);
	}
	
   /**
    * Test to ensure that the version reported by the driver matches the JAR
    * file's name.
    */
	@Test
	public void testDriverVersion()  throws Exception
	{
		String file = Driver.class.getResource( '/' + Driver.class.getName().replace( '.', '/' ) + ".class" ).toString();

		// only check if jTDS has been loaded from a jar
		if( file.startsWith( "jar" ) )
		{
		// parse path, e.g. jar:file:/target/jaql-1.0.jar!/com/ibm/si/jaql/Driver.class
		file = file.substring( 0, file.indexOf( ".jar!" ) );
		file = file.substring( file.lastIndexOf( '/' ) + 1 );
		
		assertEquals( Driver.getVersion(), file.substring( file.lastIndexOf( '-' ) + 1 ) );
		}
	
	}
	
	/**
    * Tests to ensure that the the driver url is parsed correctly
    */
	@Test
	public void testAcceptsURL() throws Exception
    {
		// Load the driver (note clients should never do it this way!)
        Driver drv = new Driver();
        assertNotNull(drv);
        
        // Badly formatted url's
        assertFalse(drv.acceptsURL("qradar:test"));
        assertFalse(drv.acceptsURL("db"));
        assertFalse(drv.acceptsURL("jdbc:qradar://localhost:5432a/test"));
        assertFalse(drv.acceptsURL("jdbc:xxx"));
        assertFalse(drv.acceptsURL("jdbc:xxx"));

        // always correct urls
        assertTrue(drv.acceptsURL("jdbc:qradar:test"));
        assertTrue(drv.acceptsURL("jdbc:qradar://1.1.1.1/"));
        assertTrue(drv.acceptsURL("jdbc:qradar://localhost/test"));
        assertTrue(drv.acceptsURL("jdbc:qradar://127.0.0.1/anydbname"));
        assertTrue(drv.acceptsURL("jdbc:qradar://localhost:9999/test"));
        assertTrue(drv.acceptsURL("jdbc:qradar://127.0.0.1:5433/hidden"));
        assertTrue(drv.acceptsURL("jdbc:qradar://[::1]:5740/db"));
        
        // always correct urls and overloaded props
        verifyUrl(drv, "jdbc:qradar://1.1.1.1/", "1.1.1.1" );
        verifyUrl(drv, "jdbc:qradar://localhost/", "localhost" );
    }
	
	
    private void verifyUrl(Driver drv, 
    						String url, 
    						String hosts) throws Exception 
    {
        assertTrue(url, drv.acceptsURL(url));
        
        Method parseMethod = drv.getClass().getDeclaredMethod("parseURL", new Class[]{String.class, Properties.class});
        parseMethod.setAccessible(true);
        Properties p = (Properties) parseMethod.invoke(drv, new Object[]{url, null});
        assertEquals(url, hosts, p.getProperty("prop.server"));
    }
	
	/**
    * Tests to ensure that the the driver isnt jdbc compliant
    */
	@Test
	public void testJdbcCompliant() throws Exception
    {
		// Load the driver (note clients should never do it this way!)
        Driver drv = new Driver();
        assertNotNull(drv);
        assertFalse(drv.jdbcCompliant());
    }
	
	/**
    * Tests to use the registered driver to connect to some ariel db
    */
	@Test
	public void testConnect() throws Exception
	{
        // Test with the url, username & password
        Connection con = DriverManager.getConnection(_properties.getProperty("url"), _properties.getProperty("user"), _properties.getProperty("password"));
        assertNotNull(con);
        con.close();
    }

	@Test
	public void shouldCreateConnectionAndReturnSelect()
	{
        Connection con = null;
        
		try
		{
			con = DriverManager.getConnection(_properties.getProperty("url"), _properties.getProperty("user"), _properties.getProperty("password"));
			assertNotNull(con);
			Statement stmt = con.createStatement();
			assertNotNull(stmt);
			ResultSet rs = stmt.executeQuery("select sourceIP, destinationIP from events");
			assertNotNull(rs);
			while(rs.next())
			{
				final String sourceip = rs.getString(1);
				final String destinationip = rs.getString(2);
				assertNotNull(sourceip);
				assertNotNull(destinationip);
				//logger.debug(String.format("Source ip %s, destination ip %s", sourceip, destinationip));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
		finally
		{
			try {
				if (con != null)
				{
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Test
	public void shouldCreateConnectionAndPrepareSelect()
	{
        Connection con = null;
        
		try
		{
			con = DriverManager.getConnection(_properties.getProperty("url"), _properties.getProperty("user"), _properties.getProperty("password"));
			assertNotNull(con);
			PreparedStatement stmt = con.prepareStatement("select sourceIP, destinationIP, startTime from events");
			assertNotNull(stmt);
			ResultSet rs = stmt.executeQuery();
			assertNotNull(rs);
			while(rs.next())
			{
				final String sourceip = rs.getString(1);
				final String destinationip = rs.getString(2);
				final long starttime = rs.getLong(3);
				assertNotNull(sourceip);
				assertNotNull(destinationip);
				assertTrue(starttime > 0L);
				logger.debug(String.format("Source ip %s, destination ip %s, start time %d", sourceip, destinationip, starttime));
			}
			
			ResultSetMetaData rsmd = rs.getMetaData();
			assertNotNull(rsmd);
			ResultSetMetaData stmtRsmd = stmt.getMetaData();
			assertNotNull(stmtRsmd);			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
		finally
		{
			try {
				if (con != null)
				{
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void shouldCreateConnectionAndReturnMetadata()
	{
        Connection con = null;
        
		try
		{
			con = DriverManager.getConnection(_properties.getProperty("url"), _properties.getProperty("user"), _properties.getProperty("password"));
			assertNotNull(con);
			Statement stmt = con.createStatement();
			assertNotNull(stmt);
			final ResultSet rs = stmt.executeQuery("select sourceip, destinationip from events");
			final ResultSetMetaData rsmd = rs.getMetaData();
			assertNotNull(rsmd);
			int columnCount = rsmd.getColumnCount();
			assertEquals(2, columnCount);
			
			final String colOneLabel = rsmd.getColumnLabel(1);
			final String colTwoLabel = rsmd.getColumnLabel(2);
			final String colOneName = rsmd.getColumnName(1);
			final String colTwoName = rsmd.getColumnName(2);
			
			assertNotNull(colOneLabel);
			assertNotNull(colTwoLabel);
			assertNotNull(colOneName);
			assertNotNull(colTwoName);
			
			assertEquals("sourceip", colOneLabel);
			assertEquals("destinationip", colTwoLabel);
			assertEquals("sourceip", colOneName);
			assertEquals("destinationip", colTwoName);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
		finally
		{
			try {
				if (con != null)
				{
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void shouldCreateConnectionAndReturnMetadataForAliasedQuery()
	{
        Connection con = null;
        
		try
		{
			con = DriverManager.getConnection(_properties.getProperty("url"), _properties.getProperty("user"), _properties.getProperty("password"));
			assertNotNull(con);
			Statement stmt = con.createStatement();
			assertNotNull(stmt);
			final ResultSet rs = stmt.executeQuery("select qidname(qid) as EventName, UNIQUECOUNT(sourceip) as Unique_IPs, count(*) as Total_Count from events group by EventName order by Total_Count desc last 1 days");
			final ResultSetMetaData rsmd = rs.getMetaData();
			assertNotNull(rsmd);
			int columnCount = rsmd.getColumnCount();
			assertEquals(3, columnCount);
			
			final String colOneLabel = rsmd.getColumnLabel(1);
			final String colTwoLabel = rsmd.getColumnLabel(2);
			final String colThreeLabel = rsmd.getColumnLabel(3);
			final String colOneName = rsmd.getColumnName(1);
			final String colTwoName = rsmd.getColumnName(2);
			final String colThreeName = rsmd.getColumnName(3);
			
			assertNotNull(colOneLabel);
			assertNotNull(colTwoLabel);
			assertNotNull(colThreeLabel);
			assertNotNull(colOneName);
			assertNotNull(colTwoName);
			assertNotNull(colThreeName);
			
			logger.debug(String.format("Column :: %s", colOneLabel));
			logger.debug(String.format("Column :: %s", colTwoLabel));
			logger.debug(String.format("Column :: %s", colThreeLabel));
			
			//assertEquals("EventName", colOneLabel);
			//assertEquals("Unique_IPs", colTwoLabel);
			//assertEquals("Total_Count", colThreeLabel);
			
			logger.debug(String.format("Column Type: %d", rsmd.getColumnType(1)));
			logger.debug(String.format("Column Type: %d", rsmd.getColumnType(2)));
			logger.debug(String.format("Column Type: %d", rsmd.getColumnType(3)));
			
			//assertEquals("destinationip", colTwoName);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
		finally
		{
			try {
				if (con != null)
				{
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Test
	public void shouldCreateConnectionAndReturnMetadataForAliasedQueryByName()
	{
        Connection con = null;
        
		try
		{
			con = DriverManager.getConnection(_properties.getProperty("url"), _properties.getProperty("user"), _properties.getProperty("password"));
			assertNotNull(con);
			Statement stmt = con.createStatement();
			assertNotNull(stmt);
			final ResultSet rs = stmt.executeQuery("select qidname(qid) as EventName, UNIQUECOUNT(sourceip) as Unique_IPs, count(*) as Total_Count from events group by EventName order by Total_Count desc last 1 days");
			final ResultSetMetaData rsmd = rs.getMetaData();
			assertNotNull(rsmd);
			int columnCount = rsmd.getColumnCount();
			assertEquals(3, columnCount);
			
			final String colOneLabel = rsmd.getColumnLabel(1);
			final String colTwoLabel = rsmd.getColumnLabel(2);
			final String colThreeLabel = rsmd.getColumnLabel(3);
			final String colOneName = rsmd.getColumnName(1);
			final String colTwoName = rsmd.getColumnName(2);
			final String colThreeName = rsmd.getColumnName(3);
			
			assertNotNull(colOneLabel);
			assertNotNull(colTwoLabel);
			assertNotNull(colThreeLabel);
			assertNotNull(colOneName);
			assertNotNull(colTwoName);
			assertNotNull(colThreeName);
			
			logger.debug(String.format("Column :: %s", colOneName));
			logger.debug(String.format("Column :: %s", colTwoName));
			logger.debug(String.format("Column :: %s", colThreeName));
			
			assertEquals("EventName", colOneLabel);
			assertEquals("Unique_IPs", colTwoLabel);
			assertEquals("Total_Count", colThreeLabel);
			
			logger.debug(String.format("Column Type: %d", rsmd.getColumnType(1)));
			logger.debug(String.format("Column Type: %d", rsmd.getColumnType(2)));
			logger.debug(String.format("Column Type: %d", rsmd.getColumnType(3)));
			
			if (rs.next())
			{
				final String eventName = rs.getString("EventName");
				final String uniqueIPs = rs.getString("Unique_IPs");
				final String totalCount = rs.getString("Total_Count");

				final String eventNameByIndex = rs.getString(1);
				final String uniqueIPsByIndex = rs.getString(2);
				final String totalCountByIndex = rs.getString(3);

				assertNotNull(eventNameByIndex);
				assertNotNull(uniqueIPsByIndex);
				assertNotNull(totalCountByIndex);
				
				assertNotNull(eventName);
				assertNotNull(uniqueIPs);
				assertNotNull(totalCount);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
		finally
		{
			try {
				if (con != null)
				{
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	
	@Test
	public void shouldOrderColumnsPerQuery()
	{
        Connection con = null;
        
		try
		{
			con = DriverManager.getConnection(_properties.getProperty("url"), _properties.getProperty("user"), _properties.getProperty("password"));
			assertNotNull(con);
			Statement stmt = con.createStatement();
			assertNotNull(stmt);
			final ResultSet rs = stmt.executeQuery("select qidname(qid) as EventName, UNIQUECOUNT(sourceip) as Unique_IPs, count(*) as Total_Count from events group by EventName order by Total_Count desc last 1 days");
			final ResultSetMetaData rsmd = rs.getMetaData();
			assertNotNull(rsmd);
			int columnCount = rsmd.getColumnCount();
			assertEquals(3, columnCount);
			
			final String colOneLabelFromString = rsmd.getColumnName(1);
			final String colTwoLabelFromString = rsmd.getColumnName(2);
			final String colThreeLabelFromString = rsmd.getColumnName(3);
			
			assertEquals("EventName", colOneLabelFromString);
			assertEquals("Unique_IPs", colTwoLabelFromString);
			assertEquals("Total_Count", colThreeLabelFromString);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
		finally
		{
			try {
				if (con != null)
				{
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}		
}

