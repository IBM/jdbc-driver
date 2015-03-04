package com.ibm.si.jaql.jdbc4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ibm.si.jaql.Driver;
import com.ibm.si.jaql.util.BaseTest;

public class Jdbc4ConnectionAQLFunctionsTest extends BaseTest{

	@Test 
	public void test_NoFunctions_ExplictField() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select sourceIP from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data = rs.getString(1);
			assertNotNull(data);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(1)); //asserting on VARCHAR(String) return type
			assertNotNull(rsMeta.getColumnTypeName(1));
		}
	}
	
	@Test 
	public void test_AllEvents() throws Exception
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
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(1)); //asserting on VARCHAR(String) return type
			assertNotNull(rsMeta.getColumnTypeName(1));
		}
	}
	
	@Test 
	public void testSTR_NoAlias() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select STR(sourceIP) from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data = rs.getString(1);
			assertNotNull(data);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(1)); //asserting on VARCHAR(String) return type
			assertNotNull(rsMeta.getColumnTypeName(1));
		}
	}	
	
	
	
	@Test 
	public void testSTR_Alias() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select STR(sourceIP) AS myalias from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data = rs.getString(1);
			assertNotNull(data);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(1)); //asserting on VARCHAR(String) return type
			assertNotNull(rsMeta.getColumnTypeName(1));
		}	
	}
	
	
	@Test 
	public void test_2_STR_Alias_NoAlias() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select STR(sourceIP) AS myalias, STR(sourceIP) from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data = rs.getString(1);
			assertNotNull(data);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(1)); //asserting on VARCHAR(String) return type of aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(1));
			
			assertNotNull(rsMeta.getColumnLabel(2));
			assertNotNull(rsMeta.getColumnType(2));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(2)); //asserting on VARCHAR(String) return type of non-aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(1));
		}	
	}
	
	
	@Test 
	public void test_2_STR_NoAlias_Alias() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select STR(sourceIP), STR(sourceIP) AS myalias from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data = rs.getString(1);
			assertNotNull(data);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(1)); //asserting on VARCHAR(String) return type of aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(1));
			
			assertNotNull(rsMeta.getColumnLabel(2));
			assertNotNull(rsMeta.getColumnType(2));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(2)); //asserting on VARCHAR(String) return type of non-aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(1));
		}	
	}
	
	
	/**
	 * TODO: revisit, we are currently mapping output data types for Arithmetic Operators to VARCHAR
	 * 
	 * @throws Exception
	 */
	@Test 
	public void test_OnePlusOne() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select 1+1 from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data = rs.getString(1);
			assertNotNull(data);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(1)); //asserting on VARCHAR(String) return type of aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(1));

		}	
	}
	
	
	
	@Test 
	public void test_STR_STRLEN_SomeAliases() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select STR(sourceIP), STRLEN(sourceIP), STR(sourceIP) as alias1, STRLEN(sourceIP) as alias2 from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String str1 = rs.getString(1);
			assertNotNull(str1);
			
			final Integer str2 = rs.getInt(2);
			assertNotNull(str2);
			
			final String str3 = rs.getString(3);
			assertNotNull(str3);
			
			final Integer str4 = rs.getInt(4);
			assertNotNull(str4);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(1)); //asserting on VARCHAR(String) return type of aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(1));
			
			assertNotNull(rsMeta.getColumnLabel(2));
			assertNotNull(rsMeta.getColumnType(2));
			assertEquals(java.sql.Types.INTEGER,rsMeta.getColumnType(2)); //asserting on VARCHAR(String) return type of aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(2));
			
			assertNotNull(rsMeta.getColumnLabel(3));
			assertNotNull(rsMeta.getColumnType(3));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(3)); //asserting on VARCHAR(String) return type of aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(3));

			assertNotNull(rsMeta.getColumnLabel(4));
			assertNotNull(rsMeta.getColumnType(4));
			assertEquals(java.sql.Types.INTEGER,rsMeta.getColumnType(4)); //asserting on VARCHAR(String) return type of aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(4));
		}	
	}
	
	
	@Test 
	public void test_STR_STRLEN_Arithmetic_SomeAliases() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select STR(sourceIP), STRLEN(sourceIP), STR(sourceIP) as alias1, STRLEN(sourceIP) as alias2, (STRLEN(sourceIP) + 1) as alias3 from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String str1 = rs.getString(1);
			assertNotNull(str1);
			
			final Integer str2 = rs.getInt(2);
			assertNotNull(str2);
			
			final String str3 = rs.getString(3);
			assertNotNull(str3);
			
			final Integer str4 = rs.getInt(4);
			assertNotNull(str4);
			
			final String str5 = rs.getString(5);
			assertNotNull(str5);
			

			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(1)); //asserting on VARCHAR(String) return type of aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(1));
			
			assertNotNull(rsMeta.getColumnLabel(2));
			assertNotNull(rsMeta.getColumnType(2));
			assertEquals(java.sql.Types.INTEGER,rsMeta.getColumnType(2)); //asserting on VARCHAR(String) return type of aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(2));
			
			assertNotNull(rsMeta.getColumnLabel(3));
			assertNotNull(rsMeta.getColumnType(3));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(3)); //asserting on VARCHAR(String) return type of aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(3));

			assertNotNull(rsMeta.getColumnLabel(4));
			assertNotNull(rsMeta.getColumnType(4));
			assertEquals(java.sql.Types.INTEGER,rsMeta.getColumnType(4)); //asserting on VARCHAR(String) return type of aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(4));
			
			assertNotNull(rsMeta.getColumnLabel(5));
			assertNotNull(rsMeta.getColumnType(5));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(5)); //asserting on VARCHAR(String) return type of aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(5));


		}	
	}
	

	@Test 
	public void test_STRLEN() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select STRLEN(sourceIP) from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data = rs.getString(1);
			assertNotNull(data);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(java.sql.Types.INTEGER,rsMeta.getColumnType(1)); //asserting on VARCHAR(String) return type of aliased function on field
			assertNotNull(rsMeta.getColumnTypeName(1));

		}	
	}
	
	
	@Test 
	public void test_SUBSTRING() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select SUBSTRING(userName, 0, 4) from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data = rs.getString(1);
			assertNotNull(data);

			assertTrue(data.length() == 4);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(1)); 
			assertNotNull(rsMeta.getColumnTypeName(1));

		}	
	}
	
	 
	@Test 
	public void test_CONCAT() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select sourceIP, destinationIP, CONCAT(STR(destinationIP), STR(sourceIP)) from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data1 = rs.getString(1);
			assertNotNull(data1);
			
			final String data2 = rs.getString(2);
			assertNotNull(data2);
			
			final String data3 = rs.getString(3);
			assertNotNull(data3);
			
			assertTrue(data3.length() == (data1.length() + data2.length()));
			
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
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(3)); 
			assertNotNull(rsMeta.getColumnTypeName(3));

		}	
	}
	
	@Test 
	public void test_DATEFORMAT() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		ResultSet rs = myCon.executeQuery("select DATEFORMAT(startTime, 'YYYY-MM-DD HH:mm:ss') as StartTime from events", params);
		assertNotNull(rs) ;
		
		while (rs.next()) {
			final String data1 = rs.getString(1);
			assertNotNull(data1);
		
			assertTrue(data1.length() == 19);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(java.sql.Types.VARCHAR,rsMeta.getColumnType(1)); 
			assertNotNull(rsMeta.getColumnTypeName(1));

		}	
	}
	
}

