package com.ibm.si.jaql.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class MetaDataResultSetTest {
	
	@Test
	public void shouldReturnNullOnNull_Con1()
	{
		try
		{
			final MetaDataResultSet rs = new MetaDataResultSet(new ArrayList<Map<String,String>>());
			assertNotNull(rs);
			assertFalse(rs.next());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void shouldReturnNullOnNull_Con2()
	{
		try
		{
			final MetaDataResultSet rs = new MetaDataResultSet(new HashMap<String,String>());
			assertNotNull(rs);
			assertFalse(rs.next());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void shouldReturnNullOnEmptySet_Con1()
	{
		List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		@SuppressWarnings("resource")
		final MetaDataResultSet rs = new MetaDataResultSet(data);
		try
		{
			assertFalse(rs.next());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void shouldReturnNullOnEmptySet_Con2()
	{
		@SuppressWarnings("resource")
		final MetaDataResultSet rs = new MetaDataResultSet(new HashMap<String,String>());
		try
		{
			assertFalse(rs.next());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void shouldReturnStringOnNonEmptySet_Con1()
	{
		final List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		final Map<String,String> entryMap = new HashMap<String,String>();
		entryMap.put("TABLE_NAME", "flows");
		data.add(entryMap);
		
		@SuppressWarnings("resource")
		final MetaDataResultSet rs = new MetaDataResultSet(data);
		try
		{
			assertTrue(rs.next());
			assertEquals("flows", rs.getString("TABLE_NAME"));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void shouldReturnStringOnNonEmptySet_Con2()
	{
		final Map<String,String> entryMap = new HashMap<String,String>();
		entryMap.put("TABLE_NAME", "flows");
		
		@SuppressWarnings("resource")
		final MetaDataResultSet rs = new MetaDataResultSet(entryMap);
		try
		{
			assertTrue(rs.next());
			assertEquals("flows", rs.getString("TABLE_NAME"));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}	
	
	
	@Test
	public void shouldReturnStringForSpecificIndex()
	{
		final Map<String,String> types = new HashMap<String,String>();
		types.put("TABLE_TYPE", "VIEW");
		
		@SuppressWarnings("resource")
		final MetaDataResultSet rs = new MetaDataResultSet(types);
		try
		{
			assertTrue(rs.next());
			assertEquals("VIEW", rs.getString(1));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
