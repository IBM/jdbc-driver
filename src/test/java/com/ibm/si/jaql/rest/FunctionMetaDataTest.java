package com.ibm.si.jaql.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.ibm.si.jaql.api.pojo.ArielColumn;

public class FunctionMetaDataTest {

	static final Logger logger = LogManager.getLogger(FunctionMetaDataTest.class.getName());

	@Test
	public void testFunctionMetaDataTestSimpleAssert()
	{
		logger.info(String.format("testFunctionMetaDataTestSimpleAssert>>>"));
		assertTrue(1==1);
	}
	
	
	@Test
	public void testLoadEmptyMetaDataSet()
	{
		logger.info(String.format("testLoadEmptyMetaDataSet>>>"));
		
		Map<String, ArielColumn> funcsMetaData = new HashMap<String, ArielColumn>();
		//FunctionMetaData.getInstance().loadMetaMap(funcsMetaData);
		FunctionMetaData.getInstance().loadMetaMap();
		
		assertNotNull(FunctionMetaData.getInstance().getTypes());
	}
	
	
	@Test
	public void testLoadEmptyMetaDataSet_HardCodedTypes()
	{
		logger.info(String.format("testLoadEmptyMetaDataSet>>>"));
		
		Map<String, ArielColumn> funcsMetaData = new HashMap<String, ArielColumn>();
		//FunctionMetaData.getInstance().loadMetaMap(funcsMetaData);
		FunctionMetaData.getInstance().loadMetaMap();
		
		assertNotNull(FunctionMetaData.getInstance().getTypes());
		
		//also assert some statically added types are in place, e.g. count
		assertTrue(FunctionMetaData.getInstance().getTypes().size() > 0);
		
		//assert on count method, with different casing
		assertNotNull(FunctionMetaData.getInstance().getType("count"));
		assertEquals(FunctionMetaData.getInstance().getType("count"), "DOUBLE" );
		
		assertNotNull(FunctionMetaData.getInstance().getType("cOuNt"));
		assertEquals(FunctionMetaData.getInstance().getType("COUNT"), "DOUBLE" );
		
	}
	
	
	@Test
	public void testLoadEmptyMetaDataSet_Concat_Type()
	{
		logger.info(String.format("testLoadEmptyMetaDataSet>>>"));
		
		Map<String, ArielColumn> funcsMetaData = new HashMap<String, ArielColumn>();
		//FunctionMetaData.getInstance().loadMetaMap(funcsMetaData);
		FunctionMetaData.getInstance().loadMetaMap();
		
		assertNotNull(FunctionMetaData.getInstance().getTypes());
		
		//also assert some statically added types are in place, e.g. count
		assertTrue(FunctionMetaData.getInstance().getTypes().size() > 0);
		
		//assert on count method, with different casing
		assertNotNull(FunctionMetaData.getInstance().getType("concat"));
		assertEquals(FunctionMetaData.getInstance().getType("concat"), "VARCHAR" );
		
	}
	
}
