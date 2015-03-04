package com.ibm.si.jaql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.ibm.si.jaql.util.BaseTest;


public class DatabaseMetaDataTest extends BaseTest
{
	static final Logger logger = LogManager.getLogger(DatabaseMetaDataTest.class.getName());
	private Connection conn = null;
	
	@Before
	public void setup()
	{
		try
		{
			conn = DriverManager.getConnection(_properties.getProperty("url"), _properties.getProperty("user"), _properties.getProperty("password"));
			assertNotNull(conn);			
			assertNotNull(conn.getMetaData());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
		finally
		{
			if (conn != null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}		
	}
	
	@Test
	public void shouldLoadMetaData()
	{
		try
		{
			final DatabaseMetaData dmd = conn.getMetaData();
			assertNotNull(dmd);
			
			// Product info
			final String productName = dmd.getDatabaseProductName();
			final String productVersion = dmd.getDatabaseProductVersion();
			final int productMajorVersion = dmd.getDatabaseMajorVersion();
			final int productMinorVersion = dmd.getDatabaseMinorVersion();
			
			assertEquals("Ariel", productName);
			assertEquals("1.0", productVersion);
			assertEquals(1, productMajorVersion);
			assertEquals(0, productMinorVersion);
			
			// Schema information
			final ResultSet rs = dmd.getSchemas();
			assertNotNull(rs);
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldLoadAllTables()
	{
		try
		{
			final DatabaseMetaData dmd = conn.getMetaData();
			assertNotNull(dmd);
						
			// Schema information
			final ResultSet rs = dmd.getSchemas();
			assertNotNull(rs);
			
			if (rs.next())
			{
				final String schema = rs.getString("TABLE_SCHEM");
				logger.debug(String.format("Schema=%s ", schema));
				assertNotNull(schema);
				assertEquals("ariel", schema);
			}
			
			final ResultSet tables = dmd.getTables("ariel", "ariel", "*", null);
			assertNotNull(tables);
			int count = 0;
			
			while (tables.next())
			{
				count++;
				final String name = tables.getString("TABLE_NAME");
				final ResultSet columns = dmd.getColumns(null, "ariel", name, "*");
				while (columns.next())
				{
					final String colName = columns.getString("COLUMN_NAME");
					assertNotNull(colName);
				}
			}
			
			assertEquals(3, count);
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}		
	}

	@Test
	public void shouldTestSchemasTables()
	{
        Connection con = null;
        
		try
		{
			con = DriverManager.getConnection(_properties.getProperty("url"), _properties.getProperty("user"), _properties.getProperty("password"));
			final DatabaseMetaData dd = con.getMetaData();
			
			ResultSet rs = dd.getCatalogs();
			while (rs.next())
			{
				String cat = rs.getString("TABLE_CAT");
				assertEquals("ariel", cat);
			}
			
			ResultSet schemaRs = dd.getSchemas();
			while (schemaRs.next())
			{
				String schema = schemaRs.getString("TABLE_SCHEM");
				String cat = schemaRs.getString("TABLE_CATALOG");
				assertEquals("ariel", cat);
				assertEquals("ariel", schema);
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
	public void shouldHaveMetaDataColumnInfo_getCatalogs()
	{
        Connection con = null;
        
		try
		{
			con = DriverManager.getConnection(_properties.getProperty("url"), _properties.getProperty("user"), _properties.getProperty("password"));
			final DatabaseMetaData dd = con.getMetaData();
			
			ResultSet rs = dd.getCatalogs();
			while (rs.next())
			{
				String cat = rs.getString("TABLE_CAT");
				assertEquals("ariel", cat);
			}
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			
			int numCols = rsMeta.getColumnCount();
			assertNotNull(numCols);
			assertEquals(1, numCols);
			
			int colType = rsMeta.getColumnType(1);
			String colTypeName = rsMeta.getColumnTypeName(1);
			
			assertEquals(colTypeName, "String");
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
	public void shouldHaveMetaDataColumnInfo_getSchemas()
	{
        Connection con = null;
        
		try
		{
			con = DriverManager.getConnection(_properties.getProperty("url"), _properties.getProperty("user"), _properties.getProperty("password"));
			final DatabaseMetaData dd = con.getMetaData();
			
			ResultSet rs = dd.getSchemas();
			while (rs.next())
			{
				String sch = rs.getString("TABLE_SCHEM");
				assertEquals("ariel", sch);
				String cat = rs.getString("TABLE_CATALOG");
				assertEquals("ariel", cat);
			}
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			
			int numCols = rsMeta.getColumnCount();
			assertNotNull(numCols);
			assertEquals(2, numCols);
			
			int colType = rsMeta.getColumnType(1);
			assertEquals(rsMeta.getColumnTypeName(1), "String");
			assertEquals(rsMeta.getColumnTypeName(2), "String");
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
