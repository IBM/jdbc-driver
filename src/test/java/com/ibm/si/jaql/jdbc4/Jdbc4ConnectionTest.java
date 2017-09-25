package com.ibm.si.jaql.jdbc4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ibm.si.jaql.Driver;
import com.ibm.si.jaql.util.BaseTest;

public class Jdbc4ConnectionTest extends BaseTest{

	@Test
	public void testValidConnectionCreation() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
	}

	@Rule public ExpectedException thrown= ExpectedException.none();
	@Test 
	public void testInValidConnectionCreation() throws Exception
	{
		thrown.expect( SQLException.class );
	    @SuppressWarnings({ "unused", "resource" })
		Jdbc4Connection myCon = new Jdbc4Connection("wrong_url", new Properties());
	}
	
	@Test 
	public void testValidStatement() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		Statement stmt = myCon.createStatement();
		assertNotNull(myCon) ;
		assertNotNull(stmt) ;
				
	}
	
	@Test 
	public void testValidStmtQueryNoParams() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		ResultSet rs = stmt.executeQuery("select * from events");
		assertNotNull(rs) ;
				
	}
	
	
	@Test 
	public void testValidConnQueryParams() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select * from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data = rs.getString(1);
			assertNotNull(data);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			
        }
				
	}
	
	
	
	
	
	@Test 
	public void testValidConnQueryParams_SingleDisplayField() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select eventcount from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data = rs.getString(1);
			assertNotNull(data);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(4,rsMeta.getColumnType(1)); //asserting on INTGEGER return type
			assertNotNull(rsMeta.getColumnTypeName(1));

		}
				
	}
	
	@Test 
	public void testValidConnQueryParamsOrdered() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		//ArielResultSet rs = (ArielResultSet)myCon.executeQuery("select * from events", params);
		ResultSet rs = myCon.executeQuery("select * from events order by qid", params);
		assertNotNull(rs) ;
		
		//assert on the increasing qid values
		int oldqid=0;
		int newqid=0;
		while (rs.next()) {
			final String data = rs.getString(1);
			assertNotNull(data);
			
			//get qid
			newqid = rs.getInt("qid");
			assertTrue(newqid >= oldqid);
			oldqid=  newqid;
			
        }
				
	}
	
	@Test
	public void shouldPrepareBasicStatement() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		final PreparedStatement stmt = myCon.prepareStatement("select * from flows");
		assertNotNull(stmt);
	}
	
	
	
	@Test 
	public void testValidConnQueryParams_Flows() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select * from flows", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data = rs.getString(1);
			assertNotNull(data);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			
        }
	}
	
	
	@Test
	public void testDefaultFields_Events() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select sourceip, username, logsourceid, endtime from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data1 = rs.getString(1);
			assertNotNull(data1);
			
			final String data2 = rs.getString(2);
			assertNotNull(data2);
			
			final String data3 = rs.getString(3);
			assertNotNull(data3);
			
			final String data4 = rs.getString(4);
			assertNotNull(data4);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(1)); 
			assertNotNull(rsMeta.getColumnTypeName(1));
			
			assertNotNull(rsMeta.getColumnLabel(2));
			assertNotNull(rsMeta.getColumnType(2));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(2)); 
			assertNotNull(rsMeta.getColumnTypeName(2));
			
			assertNotNull(rsMeta.getColumnLabel(3));
			assertNotNull(rsMeta.getColumnType(3));
			assertEquals(java.sql.Types.INTEGER,rsMeta.getColumnType(3)); 
			assertNotNull(rsMeta.getColumnTypeName(3));
			
			assertNotNull(rsMeta.getColumnLabel(4));
			assertNotNull(rsMeta.getColumnType(4));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(4)); 
			assertNotNull(rsMeta.getColumnTypeName(4));
			
        }
	}
	
	
	@Test
	public void testDefaultAndCustomFields_Events() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select sourceip, username, logsourceid, endtime, virusName from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data1 = rs.getString(1);
			assertNotNull(data1);
			
			final String data2 = rs.getString(2);
			assertNotNull(data2);
			
			final String data3 = rs.getString(3);
			assertNotNull(data3);
			
			final String data4 = rs.getString(4);
			assertNotNull(data4);

			final String data5= rs.getString(5);
			assertNotNull(data5);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(1)); 
			assertNotNull(rsMeta.getColumnTypeName(1));
			
			assertNotNull(rsMeta.getColumnLabel(2));
			assertNotNull(rsMeta.getColumnType(2));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(2)); 
			assertNotNull(rsMeta.getColumnTypeName(2));
			
			assertNotNull(rsMeta.getColumnLabel(3));
			assertNotNull(rsMeta.getColumnType(3));
			assertEquals(java.sql.Types.INTEGER,rsMeta.getColumnType(3)); 
			assertNotNull(rsMeta.getColumnTypeName(3));
			
			assertNotNull(rsMeta.getColumnLabel(4));
			assertNotNull(rsMeta.getColumnType(4));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(4)); 
			assertNotNull(rsMeta.getColumnTypeName(4));
			
			assertNotNull(rsMeta.getColumnLabel(5));
			assertNotNull(rsMeta.getColumnType(5));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(5)); 
			assertNotNull(rsMeta.getColumnTypeName(5));
        }
	}
	
}

