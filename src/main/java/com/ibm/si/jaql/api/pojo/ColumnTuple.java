package com.ibm.si.jaql.api.pojo;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	static final Logger logger = LogManager.getLogger();
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
  
    // Java 8 has a JDBCType Enum that would make this easier
  public Object getAs() throws SQLException
  {
    if (type.equalsIgnoreCase("NULL")) return null;
    if (type.equalsIgnoreCase("VARCHAR"))
      return value;
    try {
      if (type.equalsIgnoreCase("INTEGER"))
        try {
          return new Integer(value);
        } catch (NumberFormatException nfe) {
          return new Integer((new Double(value)).intValue());
        }
      if (type.equalsIgnoreCase("SMALLINT") || type.equalsIgnoreCase("TINYINT"))
        return new Short(value);
      if (type.equalsIgnoreCase("BIGINT") || type.equalsIgnoreCase("NUMERIC") || type.equalsIgnoreCase("DECIMAL"))
        return new BigDecimal(value);
      if (type.equalsIgnoreCase("DOUBLE") || type.equalsIgnoreCase("REAL"))
        return new Double(value);
      if (type.equalsIgnoreCase("FLOAT"))
        return new Float(value);
      } catch (NumberFormatException nfe) {
        logger.fatal("Error parsing " + name + " " + type + " val " + value + " ", nfe);
        throw new SQLException(nfe);
      }
    if (type.equalsIgnoreCase("CHAR"))
      return new Character(value.charAt(0));
    if (type.equalsIgnoreCase("BOOLEAN"))
      return new Boolean(value);
    return null;
  }
}