package com.ibm.si.jaql.jdbc4;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.Driver;
import com.ibm.si.jaql.util.BaseTest;

public class MetaDataTest extends BaseTest {
  static final Logger logger = LogManager.getLogger();
  
  @Test
  public void test_Metadata_alias() throws Exception {
    logger.info("Loading connection to {}", _properties);
    if (_properties.containsKey(Driver.PORT))
      _properties.put(Driver.PORT, Integer.valueOf((String)_properties.get(Driver.PORT)));
    Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
    assertNotNull(myCon) ;
    Jdbc4Statement stmt = myCon.createStatement();
    assertNotNull(stmt) ;
    Map<String, Object> params = new HashMap<String, Object> ();
    params.put("start", 0);
    params.put("end", 10);
    params.put("block", true);
    ResultSet rs = myCon.executeQuery("select starttime as foo from events", params);
    assertNotNull(rs) ;
    
    while (rs.next()) {
      ResultSetMetaData rsMeta = rs.getMetaData();
      for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
        logger.info("label {}",rsMeta.getColumnLabel(i));
        logger.info("type {}",rsMeta.getColumnType(i));
        logger.info("typeName {}",rsMeta.getColumnTypeName(i));
        logger.info("value {}",rs.getDouble(i));
      }
      assertNotNull(rsMeta);
      assert(rsMeta.getColumnLabel(1).equals("foo"));
      assert(rs.getDouble(1) > 0.0);
    }
  }
  
  @Test
  public void testQuery() throws Exception {
    logger.info("Loading connection to {}", _properties);
    if (_properties.containsKey(Driver.PORT))
      _properties.put(Driver.PORT, Integer.valueOf((String)_properties.get(Driver.PORT)));
    Jdbc4Connection myCon = new Jdbc4Connection(_properties.getProperty(Driver.URL), _properties);
    assertNotNull(myCon) ;
    Jdbc4Statement stmt = myCon.createStatement();
    assertNotNull(stmt) ;
    Map<String, Object> params = new HashMap<String, Object> ();
    params.put("start", 0);
    params.put("end", 10);
    params.put("block", true);
    ResultSet rs = myCon.executeQuery("SELECT username,starttime/3600000 as slicenum, category,min(starttime) as withinday,SUM(eventcount) as eventcount FROM events GROUP BY username,slicenum,category LIMIT 1", params);
    
    assertNotNull(rs) ;
    
    while (rs.next()) {
      ResultSetMetaData rsMeta = rs.getMetaData();
      int count = rsMeta.getColumnCount();
      for (int i = 1; i <= count; i++) {
        if (i > 1) System.out.print(" | ");
        System.out.print(rsMeta.getColumnLabel(i));
      }
      System.out.println();
      for (int i = 1; i <= count; i++) {
        if (i > 1) System.out.print(" | ");
        System.out.print(rs.getString(i));
      }
      System.out.println();
    }
  }
}