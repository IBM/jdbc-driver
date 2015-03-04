package com.ibm.si.jaql.jdbc4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ibm.si.jaql.Driver;
import com.ibm.si.jaql.util.BaseTest;

public class Jdbc4StatementTest extends BaseTest{

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
		
		boolean execRes = stmt.execute("select eventcount from events");
		assertTrue(execRes) ;
		
		ResultSet rs = stmt.getResultSet();
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
	public void testValidConnQueryParams_WildCardDisplayField() throws Exception
	{
		Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
		assertNotNull(myCon) ;
		Jdbc4Statement stmt = myCon.createStatement();
		assertNotNull(stmt) ;
		
		Map<String, Object> params = new HashMap<String, Object> ();
		params.put("start", 0);
		params.put("end", 10);
		params.put("block", true);
		
		boolean execRes = stmt.execute("select * from events");
		assertTrue(execRes) ;
		
		ResultSet rs = null;
            rs = stmt.getResultSet();
            while (rs.next()) {
    			final String data = rs.getString(1);
    			assertNotNull(data);
    			
    			ResultSetMetaData rsMeta = rs.getMetaData();
    			assertNotNull(rsMeta);
    			int colCount = rsMeta.getColumnCount();
    			assertNotNull(colCount);
    			
    			
    			assertNotNull(rsMeta);
    			assertNotNull(rsMeta.getColumnCount());
    			assertNotNull(rsMeta.getColumnLabel(1));
    			assertNotNull(rsMeta.getColumnType(1));
    			assertNotNull(rsMeta.getColumnTypeName(1));
    			
    			assertEquals("sourceip",rsMeta.getColumnLabel(1)); 
    			assertEquals(Types.VARCHAR,rsMeta.getColumnType(1));
    			
    			assertEquals("destinationip",rsMeta.getColumnLabel(2));
    			assertEquals(Types.VARCHAR,rsMeta.getColumnType(2)); 
    			
    			assertEquals("eventcount",rsMeta.getColumnLabel(3));
    			assertEquals(Types.INTEGER,rsMeta.getColumnType(3)); 
    			
    			assertEquals("sourceport",rsMeta.getColumnLabel(4));
    			assertEquals(Types.VARCHAR,rsMeta.getColumnType(4));
    			
    			assertEquals("protocolid",rsMeta.getColumnLabel(5));
    			assertEquals(Types.SMALLINT,rsMeta.getColumnType(5));
    			
    		}			
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
		
		boolean execRes = stmt.execute("select * from flows");
		assertTrue(execRes) ;
		ResultSet rs = null;
        rs = stmt.getResultSet();
		
		while (rs.next()) {
			final String data = rs.getString(1);
			assertNotNull(data);
			
			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			
        }
				
	}
	
	
	
}

