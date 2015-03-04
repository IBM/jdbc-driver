package com.ibm.si.jaql.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConnectionUtility
{	
	public static void closeQuietly(final Object conn)
	{
		if (conn != null)
		{
			final Class<? extends Object> clazz = conn.getClass();
			
			try
			{
				final Method method = clazz.getDeclaredMethod("close");
				if (method != null)
				{
					method.invoke(conn);
				}
			}
			catch (NoSuchMethodException e)
			{
				// Ignored
			}
			catch (SecurityException e)
			{
				// Ignored				
			}
			catch (IllegalAccessException e)
			{
				// Ignored				
			}
			catch (IllegalArgumentException e)
			{
				// Ignored				
			}
			catch (InvocationTargetException e)
			{
				// Ignored				
			}
		}
	}	
}
