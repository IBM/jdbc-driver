package com.ibm.si.jaql.rest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.ibm.si.jaql.api.ArielException;
import com.ibm.si.jaql.api.pojo.ArielColumn;
import com.ibm.si.jaql.api.pojo.ArielResult;
import com.ibm.si.jaql.api.pojo.ColumnTuple;

/**
 * Ariel API endpoints returned data, json parser to form sensible sensible metadata and result set objects 
 * 
 * @author IBM
 *
 */
public class ArielResultGSONAdapter implements JsonDeserializer<ArielResult>
{
	static final Logger logger = LogManager.getLogger(ArielResultGSONAdapter.class.getName());
	
	private ArielConnection conn = null;
	
	public ArielResultGSONAdapter(final ArielConnection conn)
	{
		this.conn = conn;
	}
	
		public ArielResult deserialize(final JsonElement elem,
								   final Type type,
								   final JsonDeserializationContext ctx) throws JsonParseException
	{
		ArielResult result = null;
		final List<LinkedHashMap<String,ColumnTuple>> results = new ArrayList<LinkedHashMap<String,ColumnTuple>>();
		final JsonObject obj = elem.getAsJsonObject();
		Set<Entry<String,JsonElement>> entries = obj.entrySet();
		final Iterator<Entry<String,JsonElement>> itr = entries.iterator();
		
		while (itr.hasNext())
		{
			final Entry<String,JsonElement> entry = itr.next();
			final String structName = entry.getKey();
		
			if (entry.getValue().isJsonArray())
			{
				final JsonArray rawResults = entry.getValue().getAsJsonArray();
				final Iterator<JsonElement> elemItr = rawResults.iterator();
				
				while (elemItr.hasNext())
				{
					final JsonElement element = elemItr.next();
					final LinkedHashMap<String,ColumnTuple> mappedResults = mapObject(element, ctx);
					results.add(mappedResults);
				}
				
				result = new ArielResult(structName, results);
			}
			else
			{
				final LinkedHashMap<String,ColumnTuple> mappedResults = mapObject(elem, ctx);
				results.add(mappedResults);
				result = new ArielResult("error", results);
			}
		}
		
		return result;
	}
	
	private LinkedHashMap<String,ColumnTuple> mapObject(final JsonElement elem, final JsonDeserializationContext ctx) throws JsonParseException
	{
		LinkedHashMap<String,ColumnTuple> result = new LinkedHashMap<String, ColumnTuple>();
		Map<String,String> rawResultSet = null;
		
		if (elem.isJsonObject())
		{
			rawResultSet = ctx.deserialize(elem,
					new TypeToken<Map<String,String>>() {}.getType());
			
			final Iterator<String> itr = rawResultSet.keySet().iterator();
			while (itr.hasNext())
			{
				final String key = itr.next();
				final String value = rawResultSet.get(key);
				ArielColumn col = null;
				
				try
				{
					String type = null;
					col = conn.getColumnMetaData(key);
					if (col != null)
					{
						type = col.getArgumentType();
						logger.debug(String.format("ArielGson Adaprt: mapObject: *** Getting column meta data for %s, which was %s", key, type));
					}
					else
					{
						// For an alias, there will be no function metadata
						type = FunctionMetaData.getInstance().getType(key);
						logger.debug(String.format("ArielGson Adaprt: mapObject: *** Getting function column meta data for %s, which was %s", key, type));
					}
					
					
				}
				catch (ArielException e)
				{
					throw new JsonParseException(e);
				}
				
				final ColumnTuple tuple = new ColumnTuple(key, value, (col != null ? col.getArgumentType() : "UNKNOWN"));
				result.put(key, tuple);
			}
		}
		
		return result;
	}
}
