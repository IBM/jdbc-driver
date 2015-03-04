package com.ibm.si.jaql.api.pojo;

/**
 * Individual Ariel data property defined in terms of its key-value and datatype 
 * Based on parsing the Ariel Query string lexicons; as opposed to data from the result sets in ariel, or querying explicit
 * metadata endpoints 
 * @author IBM
 *
 */
public class ColumnTuple
{
	private String name;
	private String value;
	private String type;
	
	public ColumnTuple(final String name,
					   final String value,
					   final String typeString)
	{
		this.name = name;
		this.value = value;
		
		//TODO: hopefully remove, as we clean up Ariel endpoints 
		//temp fix for SQL clients, to remap Ariel Objects back to Strings
		if (typeString != null && typeString.equalsIgnoreCase("JAVA_OBJECT"))
		{
			this.type = "VARCHAR";
		}
		else
		{
			this.type = typeString;
		}
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getValue()
	{
		return this.value;
	}
	
	public String getType()
	{
		return this.type;
	}
}