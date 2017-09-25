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
    logger.trace("Init ColumnTuple: {} {} {}", name,value,type);
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
  
  public String toString() {
    return name + "(" + type + ")=" +value;
  }
    // Java 8 has a JDBCType Enum that would make this easier
  public Object getAs() throws SQLException { return getAs(type); }
  public Object getAs(String matchtype) throws SQLException
  {
    logger.trace("Get '{}' with '{}' as '{}'", name, value, matchtype);
    if (matchtype.equalsIgnoreCase("NULL")) return null;
    if (matchtype.equalsIgnoreCase("VARCHAR"))
      return value;
    try {
      if (matchtype.equalsIgnoreCase("INTEGER"))
        try {
          return new Integer(value);
        } catch (NumberFormatException nfe) {
          return new Integer((new Double(value)).intValue());
        }
      if (matchtype.equalsIgnoreCase("SMALLINT") || matchtype.equalsIgnoreCase("TINYINT"))
        return new Short(value);
      if (matchtype.equalsIgnoreCase("BIGINT") || matchtype.equalsIgnoreCase("NUMERIC") || matchtype.equalsIgnoreCase("DECIMAL"))
        return new BigDecimal(value);
      if (matchtype.equalsIgnoreCase("DOUBLE") || matchtype.equalsIgnoreCase("REAL"))
        return new Double(value);
      if (matchtype.equalsIgnoreCase("FLOAT"))
        return new Float(value);
      } catch (NumberFormatException nfe) {
        logger.fatal("Error parsing " + name + " " + matchtype + " val " + value + " ", nfe);
        throw new SQLException(nfe);
      }
    if (matchtype.equalsIgnoreCase("CHAR"))
      return new Character(value.charAt(0));
    if (matchtype.equalsIgnoreCase("BOOLEAN"))
      return new Boolean(value);
    return null;
  }
}