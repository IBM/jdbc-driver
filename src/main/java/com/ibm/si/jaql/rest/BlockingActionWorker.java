package com.ibm.si.jaql.rest;

import java.io.IOException;
import java.util.Properties;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.rest.Result;

/**
 * Blocking thread that will wait until the ariel search query completes
 * @author IBM
 *
 */
public class BlockingActionWorker implements Runnable {
  static final Logger logger = LogManager.getLogger(BlockingActionWorker.class.getName());

  private RESTClient client = null;
  private String request = null;
  private Result result = null;
  private Properties props = null;
  
  public BlockingActionWorker(final RESTClient client, final String req) {
    this(client, req, null);
  }
  public BlockingActionWorker(final RESTClient client, final String req, final Properties p) {
    this.client = client;
    this.request = req;
    this.props = p;
  }
  public void run() {
    long startTime = System.currentTimeMillis();
    try {
      while (true) {
        logger.debug("Requesting search status...");
        result = client.doGet(request, props);
        int status = result.getStatus();
        int uniqueErrorcode = result.getCode();
        if (status != 200) {
          logger.warn(String.format("Status was %d", result.getStatus()));
          logger.warn(String.format("Status Code was %d", result.getCode()));
          break;
        } else {
          Map<String,Object> body = result.getParsedBody();
          if (body.containsKey("status") && body.get("status").equals("COMPLETED")) {
            logger.debug("Search complete. Retrieving results");
            break;
          } else if ((System.currentTimeMillis() - startTime) > 10*60*1000) {
            logger.debug("Search timed out. Aborting");
            break;
          }
          Thread.sleep(100);
        }
      }
    } catch (IOException e) {
      //for now, dump the checked exception, as we cannot propagate it 
      e.printStackTrace();
    } catch (InterruptedException e) {
      //for now, dump the checked exception, as we cannot propagate it 
      e.printStackTrace();
    }
  }
  
  public Result getResult() {
    return result;
  }
}
