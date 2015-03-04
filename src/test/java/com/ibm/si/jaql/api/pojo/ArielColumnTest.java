package com.ibm.si.jaql.api.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Normally pojo unit testing is unnecessary, but these test serve to remind us that some bespoke data type mapping occurs
 * in the driver to aid SQL client support
 * @author IBM
 *
 */
public class ArielColumnTest {

	@Test
	public void shouldCreateValidVARCHARMetaDataArielColumn ()
	{
		ArielColumn ar = new ArielColumn();
		ar.setName("test");
		ar.setLabel("test");
		ar.setArgumentType("VARCHAR");
		
		assertNotNull(ar);
		assertEquals("test", ar.getLabel());
		assertEquals("test", ar.getName());
		assertEquals("VARCHAR", ar.getArgumentType());
	}
	
	/*
	 * This test is a reminder that we are mapping 7.2.4 ariel result sets, with meta data JAVA_OBJECT data tyoes to
	 * VARCHAR within the driver.
	 * When we fix data type mapping with Ariel endpoints or back natively into Ariel, this test should fail and serve
	 * as a reminder to fix the mappings within the driver
	 * context: we need to map JAVA_OBJECTS to VARCHAR, so that native sql client tooling/GUIs know how to display
	 * the ariel data  
	 */
	@Test
	public void shouldCreateValidMetaDataArielColumn_MappingJAVAOBJECTtoVARCHAR ()
	{
		ArielColumn ar = new ArielColumn();
		ar.setName("test");
		ar.setLabel("test");
		ar.setArgumentType("JAVA_OBJECT");
		
		assertNotNull(ar);
		assertEquals("test", ar.getLabel());
		assertEquals("test", ar.getName());
		assertEquals("VARCHAR", ar.getArgumentType());
	}
}
