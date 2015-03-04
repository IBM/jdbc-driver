package com.ibm.si.jaql.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import com.ibm.si.jaql.api.pojo.ColumnTuple;

public class AQLParserTest
{

	//@Test
	public void shouldParseSingleField()
	{
		List<ColumnTuple> result = AQLParser.parse("select sourceip from table");
		assertNotNull(result);
		assertEquals(1, result.size());
	}
	
	//@Test
	public void shouldParseMultipleFields()
	{
		List<ColumnTuple> result = AQLParser.parse("select sourceip, destinationip from table");
		assertNotNull(result);
		assertEquals(2, result.size());
	}
}
