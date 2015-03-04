package com.ibm.si.jaql.api;

/**
 * Generic ArielEXception wrapper exception
 * 
 * @author IBM
 *
 */
public class ArielException extends Exception
{
	private static final long serialVersionUID = 1L;

	public ArielException(final String message)
	{
		super(message);
	}
	
	public ArielException(final String message, final Throwable t)
	{
		super(message, t);
	}
	
	public ArielException(final Throwable t)
	{
		super(t);
	}
}
