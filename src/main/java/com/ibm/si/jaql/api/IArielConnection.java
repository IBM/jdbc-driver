package com.ibm.si.jaql.api;

import com.ibm.si.jaql.api.pojo.ArielResult;
import com.ibm.si.jaql.api.pojo.ArielSearch;

/**
 * Model a connection to an Ariel datastore
 * 
 * @author IBM
 *
 */
public interface IArielConnection
{
	/**
	 * Create a search using an AQL statement, returning a unique id
	 * of the form '39c14862-ae68-40eb-84e8-6ac1f3741940'
	 * 
	 * @param query An AQL statement
	 * @return unique identifier for this search
	 */
	ArielSearch createSearch(final String query) throws ArielException;
	
	/**
	 * Delete a search
	 * 
	 * @param searchId unique identifier for the search that should be deleted
	 */
	void deleteSearch(final String searchId) throws ArielException;
	
	/**
	 * Retrieves the list of configured searches
	 * 
	 * @return the list of configured searches
	 */
	String[] listSearches() throws ArielException;
	
	/**
	 * Retrieve the syntax used to create a search
	 * 
	 * @param id unique identifier for the search
	 * @return AQL syntax for the search
	 */
	ArielSearch getSearch(final String id) throws ArielException;
	
	/**
	 * Return the results for a given search
	 * 
	 * @param searchId unique identifier for this search
	 * @param blocking block if we want to wait for search results to become available, otherwise return immediately regardless
	 * @return search results for the query
	 */
	ArielResult getSearchResults(final String searchId, final int start, final int end, boolean blocking) throws ArielException;
	
	/**
	 * Close the connection to ariel
	 */
	void close() throws ArielException;
}
