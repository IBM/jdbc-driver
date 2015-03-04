package com.ibm.si.jaql.api.pojo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.rest.ArielConnection;

/**
 * Represents the basic meta data that can be acquried for column /selectable arield field,
 * returned by the Ariel endpoints
 * 
 * Note: used to feed into QueryFieldMetaData, to build a more jdbc compliant object, used in MetaDataBuilder
 * to form a dtaa object set to be used in the jdbc interface implementation ArielResultSetMetaData (extends
 * resultSetMetaData)
 * 
 * @author IBM
 *
 */
public class ArielColumn
{
	static final Logger logger = LogManager.getLogger(ArielConnection.class.getName());
	
	private boolean calculated;
	private String label;
	private String name;
	private String argumentType;
	private boolean custom;
	private boolean indexable;
	
	public boolean isCalculated() {
		return calculated;
	}
	public void setCalculated(boolean calculated) {
		this.calculated = calculated;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArgumentType() {
		//TODO: hopefully remove, as we clean up Ariel endpoints 
		//temp fix for SQL clients, to remap Ariel Objects back to Strings
		String res = "";
		if (argumentType.equalsIgnoreCase("JAVA_OBJECT"))
		{
			res = "VARCHAR";
		}
		else
		{
			res = argumentType;
		}
		
		return res;
	}
	public void setArgumentType(String argumentType) {
		//temp fix for SQL clients, to remap Ariel object types to Strings
		if (argumentType.equalsIgnoreCase("JAVA_OBJECT"))
		{
			argumentType= "VARCHAR";
		}	
		this.argumentType = argumentType;
		
	}
	public boolean isCustom() {
		return custom;
	}
	public void setCustom(boolean custom) {
		this.custom = custom;
	}
	public boolean isIndexable() {
		return indexable;
	}
	public void setIndexable(boolean indexable) {
		this.indexable = indexable;
	}
	
	@Override
	public String toString()
	{
		return String.format("name=%s, type=%s", name, argumentType);
	}
}
