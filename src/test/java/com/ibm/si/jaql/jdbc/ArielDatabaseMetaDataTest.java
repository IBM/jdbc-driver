package com.ibm.si.jaql.jdbc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.ibm.si.jaql.util.BaseTest;

public class ArielDatabaseMetaDataTest extends BaseTest
{
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
	public void getColumnsTest()
	{
		try
		{
			final DatabaseMetaData dmd = conn.getMetaData();
			assertNotNull(dmd);
						
			final ResultSet columns = dmd.getColumns("ariel", "ariel", "events", null);
			assertNotNull(columns);			
			
			final ResultSet columns2 = dmd.getColumns("null", "ariel", "ariel", null);
			assertNotNull(columns2);
			
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}		
		
	}
	
}
