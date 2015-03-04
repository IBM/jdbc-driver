package com.ibm.si.jaql;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.ibm.si.jaql.util.BaseTest;

public class BasicAQLQueryCreationTest extends BaseTest
{
	@Test
	public void shouldCreateBasicStatement()
	{
		Connection conn = null;
		
		try
		{
			conn = DriverManager.getConnection(_properties.getProperty("url"), _properties.getProperty("user"), _properties.getProperty("password"));
			assertNotNull(conn);
			Statement stmt = conn.createStatement();
			assertNotNull(stmt);
			
			ResultSet rs = stmt.executeQuery("select * from events");
			assertNotNull(rs);
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
}
