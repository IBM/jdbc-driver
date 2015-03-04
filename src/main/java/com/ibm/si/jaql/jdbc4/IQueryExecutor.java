package com.ibm.si.jaql.jdbc4;

import java.util.Map;

import com.ibm.si.jaql.api.IArielDatabase;
import com.ibm.si.jaql.api.pojo.ArielResult;

/**
 * Interface defining Query execution support against the Arield JDBC4 connections 
 * @author IBM
 *
 */
public interface IQueryExecutor
{	
	ArielResult executeQuery( String query, Map<String, Object> parameters ) throws Exception;
	IArielDatabase getDatabase();
}
