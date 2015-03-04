package com.ibm.si.jaql.api;

import java.util.Map;

import com.ibm.si.jaql.api.pojo.ArielColumn;

/**
 * Provides an interface for communicating with an Ariel datastore, and a layer
 * for our JDBC driver to use for all operations.
 * 
 * @author IBM
 *
 */
public interface IArielDatabase
{
	String[] listDatabases() throws ArielException;
	IArielConnection createConnection() throws ArielException;
	Map<String,ArielColumn> getMetaData(final String tableName) throws ArielException;
	Map<String,String> getFunctionMetaData() throws ArielException;
}
