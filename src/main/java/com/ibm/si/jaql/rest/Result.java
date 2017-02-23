package com.ibm.si.jaql.rest;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.Header;
import org.apache.http.HttpMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.rest.RESTClient.ErrorResult;

public class Result
{
	private int status;
	private String body;
  private HttpMessage msg = null;
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
  
	public Result(final int status, final String body, HttpMessage msg)
	{
		this.status = status;
		this.body = body;
    this.msg = msg;
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
  
  public Map<String,Object> getParsedBody() {
    // Consider pojo
		Gson gson = null;
		gson = new GsonBuilder()
		.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
		.create();
		return gson.fromJson(this.body, Map.class);
  }
  
  public String getHeader(String name) {
    Header head = msg.getFirstHeader(name);
    if (head != null) return head.getValue();
    return null;
  }
}
