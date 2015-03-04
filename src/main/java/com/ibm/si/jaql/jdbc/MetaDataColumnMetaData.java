package com.ibm.si.jaql.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Meta Data pojo, for Ariel Database Meta Data
 * @author IBM
 *
 */
public class MetaDataColumnMetaData 
{
	static final Logger logger = LogManager.getLogger(MetaDataColumnMetaData.class.getName());

	public String name;
	private String typeName;
	public int type;
	
	public MetaDataColumnMetaData( String name, String typeName, int type )
    {
        this.name = name;
        this.typeName = typeName;
        this.type = type;
    }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString()
	{
		logger.debug(String.format("Name=%s, typeName=%s, Type=%d", name, typeName, type));
		return String.format("Name=%s, typeName=%s, Type=%d", name, typeName, type);
	}
}
