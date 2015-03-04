package com.ibm.si.jaql.api.pojo;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ibm.si.jaql.aql.ParsedColumn;

/**
 * Represents a generic Ariel data results.
 * 
 * Typically represents a data structure which datasource/database field, and a variable length list, of variable key-value properties
 * {
 *   "database":
 *   [{"key1":"value1", "key2":"value2" ... "keyN":"valueN"},..
 *    }]
 * }
 * 
 * @author IBM
 *
 */
public class ArielResult
{
	private String name;
	private List<LinkedHashMap<String,ColumnTuple>> results;
	
	public ArielResult(final String name, final List<LinkedHashMap<String,ColumnTuple>> results)
	{
		this.name = name;
		this.results = results;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public List<LinkedHashMap<String,ColumnTuple>> getResults()
	{
		return this.results;
	}
	
	/**
	 * Helper method to return from the ariel result set, a list of column fields
	 * to be used to rebuild metadata on the result.
	 * Use shoudl be exclusive to the wildcard display querys (select * from <table>), as parsing the 
	 * aql query string liexicons will not yield metadata for  
	 * 
	 * @return
	 */
	public List<ParsedColumn> getFieldList()
	{	
		final List<ParsedColumn> results = new LinkedList<ParsedColumn>();
		if ( (this.results != null) && (this.results.size() > 0) )
		{
			Map<String,ColumnTuple> firstRow = this.results.get(0);
			if (firstRow != null)
			{
				for ( String key : firstRow.keySet() ) {
					ParsedColumn column = new ParsedColumn(key, false, "");
					results.add(column);
				}
			}
		}
		
		return results;
	}
	
}
