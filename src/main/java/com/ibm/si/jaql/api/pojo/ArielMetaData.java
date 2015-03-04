package com.ibm.si.jaql.api.pojo;

import java.util.List;

/**
 * Represents the meta data, for Ariel data.  Think data types and constraints for the data properties of actual
 * Ariel data.
 * This metadata is based on querying the ariel datastore iteslef, through endpoints, as opposed to parsing the ariel
 * query string lexicon parsing 
 * 
 * @author IBM
 *
 */
public class ArielMetaData
{
	private List<ArielColumn> columns;
	
	public void setColumns(final List<ArielColumn> columns)
	{
		this.columns = columns;
	}
	
	public List<ArielColumn> getColumns()
	{
		return this.columns;
	}
}
