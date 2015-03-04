package com.ibm.si.jaql.api.pojo;

import java.io.Serializable;

/**
 * Ariel Search transaction utility, storing progress and status of the actual search in Ariel
 * 
 * {"progress":0,"save_results":false,"record_count":0,"search_id":"83758e77-97ba-4ccb-b24d-ebfb7a5a058b","query_execution_time":0,"processed_record_count":0,"status":"WAIT"}
 *
 * @author IBM
 *
 */

public class ArielSearch implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int progress;
	private boolean saveResults;
	private int recordCount;
	private String searchId;
	private long queryExecutionTime;
	private double processedRecordCount;
	private String status;
	
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public boolean isSaveResults() {
		return saveResults;
	}
	public void setSaveResults(boolean saveResults) {
		this.saveResults = saveResults;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public String getSearchId() {
		return searchId;
	}
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}
	public long getQueryExecutionTime() {
		return queryExecutionTime;
	}
	public void setQueryExecutionTime(long queryExecutionTime) {
		this.queryExecutionTime = queryExecutionTime;
	}
	public double getProcessedRecordCount() {
		return processedRecordCount;
	}
	public void setProcessedRecordCount(double processedRecordCount) {
		this.processedRecordCount = processedRecordCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
