package com.ibm.si.jaql.api.pojo;

public class ParsedColumn
{
	public final String name;
	public final boolean func;
	public final boolean isArthimeticExpression;
	public final String alias;
	
	public ParsedColumn(String name, boolean func, String alias)
	{
		this(name, func, alias, false);
	}
	
	public ParsedColumn(String name, boolean func, String alias, boolean isArthimeticExpression)
	{
		this.name = name;
		this.func = func;
		this.alias = alias;
		this.isArthimeticExpression = isArthimeticExpression;
	}
  public String toString() {
    return "{name=" + name + ", func=" + func + ", alias=" + alias + ", expr=" + isArthimeticExpression +"}";
  }
}
