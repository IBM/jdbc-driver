package com.ibm.si.jaql.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;

import org.apache.commons.httpclient.HttpStatus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.rest.RESTClient.ErrorResult;

public class Result
{
	private int status;
	private String body;
	private static Logger logger = LogManager.getLogger();
	public Result(final int status)
	{
		this.status = status;
	}
	
	public Result(final int status, final String body)
	{
		this.status = status;
		this.body = body;
	}
	
	public int getStatus()
	{
		return this.status;
	}
	
	public String getBody()
	{
		try {
			logger.trace(String.format(this.body));
		} catch (java.util.UnknownFormatConversionException e) {
			logger.debug("Unable log body due to exception " + e.getMessage());
		}
		return this.body;
	}
	
	public int getCode()
	{
		Gson gson = null;
		gson = new GsonBuilder()
		.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
		.create();
		
		int returnCode = 0;
		
		ErrorResult result = null;
		if (this.status != HttpStatus.SC_OK)
		{
			result = gson.fromJson(this.body, ErrorResult.class);
			if (result != null)
			{
				returnCode = result.getCode();
			}
		}
		
		return returnCode;
	}
}
