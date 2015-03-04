package com.ibm.si.jaql.aql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class StatementParserTest {

	@Test
	public void shouldParseWildcardSelect()
	{
		final String aql = "select * from flows";
		StatementParser.parseAQLStatementForFieldList(aql);
	}
	
	@Test
	public void shouldParseBasicSelect()
	{
		final String aql = "select sourceIP, destinationIP from flows";
		StatementParser.parseAQLStatementForFieldList(aql);
	}

	@Test
	public void shouldParseBasicAliasedSelect()
	{
		final String aql = "select qidname(qid) as EventName, UNIQUECOUNT(sourceip) as Unique_IPs, count(*) as Total_Count from events group by EventName order by Total_Count desc last 1 days";
		List<ParsedColumn> result = StatementParser.parseAQLStatementForFieldList(aql);
		assertNotNull(result);
		assertEquals(3, result.size());
		assertEquals("EventName", result.get(0).alias);
		assertEquals("Unique_IPs", result.get(1).alias);
		assertEquals("Total_Count", result.get(2).alias);
	}

	@Test
	public void shouldParseBasicAliasedSelectRegardlessOfCapitalisation()
	{
		final String aql = "select QIDNAME(qid) as EventName, UNIQUECOUNT(sourceip) as Unique_IPs, count(*) as Total_Count from events group by EventName order by Total_Count desc last 1 days";
		List<ParsedColumn> result = StatementParser.parseAQLStatementForFieldList(aql);
		assertNotNull(result);
		assertEquals(3, result.size());
		assertEquals("EventName", result.get(0).alias);
		assertEquals("Unique_IPs", result.get(1).alias);
		assertEquals("Total_Count", result.get(2).alias);
	}
	
	@Test
	public void shouldParseMixOfAliasedSelect()
	{
		final String aql = "select qidname(qid) as EventName, UNIQUECOUNT(sourceip) as Unique_IPs, count(*) as Total_Count, sourceIP from events group by EventName order by Total_Count desc last 1 days";
		List<ParsedColumn> result = StatementParser.parseAQLStatementForFieldList(aql);
		assertNotNull(result);
		assertEquals(4, result.size());
		assertEquals("EventName", result.get(0).alias);
		assertEquals("Unique_IPs", result.get(1).alias);
		assertEquals("Total_Count", result.get(2).alias);
		assertEquals("sourceIP", result.get(3).name);
	}
	

	@Test
	public void shouldParseWildcardDisplaySelect_ButNoMetaData_Events()
	{
		final String aql = "select * from events";
		List<ParsedColumn> result = StatementParser.parseAQLStatementForFieldList(aql);
		assertNotNull(result);
		assertEquals(0, result.size());
	}
	
	@Test
	public void shouldParseWildcardDisplaySelect_ButNoMetaData_Flows()
	{
		final String aql = "select * from flows";
		List<ParsedColumn> result = StatementParser.parseAQLStatementForFieldList(aql);
		assertNotNull(result);
		assertEquals(0, result.size());
	}
	
	@Test
	public void shouldParseWildcardDisplaySelect_ButNoMetaData2_Simarc()
	{
		final String aql = "select * from flows";
		List<ParsedColumn> result = StatementParser.parseAQLStatementForFieldList(aql);
		assertNotNull(result);
		assertEquals(0, result.size());
	}
	
	@Test
	public void shouldParseFlowsTableName()
	{
		final String aql = "select * from flows";
		String tableName = StatementParser.parseAQLStatementForTable(aql);
		assertNotNull(tableName);
		assertEquals("flows", tableName);
	}
	
	
	/*
	@Test
	public void shouldParseFlowsTableName_WithSchema()
	{
		final String aql = "select * from ariel.flows";
		String tableName = StatementParser.parseAQLStatementForTable(aql);
		assertNotNull(tableName);
		assertEquals("flows", tableName);
	}
	
	@Test
	public void shouldParseEventsTableName_WithSchema_WithSchemaPrefixedFields()
	{
		final String aql = "select events.sourceip from ariel.events";
		String tableName = StatementParser.parseAQLStatementForTable(aql);
		assertNotNull(tableName);
		assertEquals("events", tableName);
		
		String query = StatementParser.removeUnsupportedSchemas(aql);
		assertNotNull(query);
		assertEquals("select sourceip from events", query);
	}
	
	
	@Test
	public void shouldParseCrystalReportsStmt()
	{
		final String aql = "select 'events.sourceip' from 'ariel.events' 'events'";	
		String query = StatementParser.removeUnsupportedSchemas(aql);
		assertNotNull(query);
		assertEquals("select sourceip from events", query);
	}
	*/
}
