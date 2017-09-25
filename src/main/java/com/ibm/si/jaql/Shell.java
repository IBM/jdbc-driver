package com.ibm.si.jaql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Properties;
import java.lang.StringBuilder;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Formatter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import org.jboss.aesh.console.Console;
import org.jboss.aesh.console.ConsoleOutput;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.CSVWriter;

public class Shell {
  Connection c;
  String sep = "|";
  boolean prompt = true;
  private static Logger logger = LogManager.getLogger();
  int numQueries = 0;
  String outputPrefix = "";
  boolean writeFile = false;
  public Shell (Properties props, String separator) throws Exception {
    logger.info("Current props {}", props);
    sep = separator;
    c = DriverManager.getConnection("jdbc:qradar://" + props.getProperty("url"), props);
    if (props.getProperty("outputfile") != null) {
      outputPrefix = props.getProperty("outputfile");
      writeFile = true;
    }
    logger.info("Initialized connection", c);
  }
  
  public void query(String sql, Console console) {
    try {
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      ResultSetMetaData rsMeta = rs.getMetaData();
      numQueries++;
      
      if (writeFile) {
        String filename = outputPrefix + "_" + numQueries + ".csv";
        CSVWriter writer = new CSVWriter(new FileWriter(filename));
        int numRows = writer.writeAll(rs,true) -1;
        writer.close();
        console.pushToStdErr("Wrote " + numRows + " rows to " +filename +"\n");
        return;
      }
      
      List<String[]> table = new LinkedList<String[]>();
      int num_columns = rsMeta.getColumnCount();
      int[] column_widths = new int[num_columns];
      String[] header = new String[num_columns];
      

      for (int i = 1; i <= num_columns; i++) {
        header[i-1] = rsMeta.getColumnLabel(i);
        column_widths[i-1] = header[i-1].length();
      }
      while (rs.next()) {
        String[] column = new String[num_columns];
        for (int i = 1; i <= num_columns; i++) {
          column[i-1] = rs.getString(i);
          column_widths[i-1] = Math.max(column_widths[i-1], column[i-1] == null ? 4 : column[i-1].length());
        }
        table.add(column);
      }
      
      StringBuilder buff = new StringBuilder();
      Formatter formatter = new Formatter();
      String format = sep;
      String horizontal = "+";
      for (int i = 0; i < num_columns; i++) {
        format = format + " %" + column_widths[i] + "s " + sep;
        for (int j = 0; j < column_widths[i]+2; j++)
          horizontal = horizontal + "-";
        horizontal = horizontal + "+";
      }
      format = format + "\n";
      formatter.format("%s\n", horizontal);
      formatter.format(format, header).toString();
      formatter.format("%s\n", horizontal);
      int i = 0;
      for (String[] row : table) {
        formatter.format(format, row).toString();
      }
      formatter.format("%s\n", horizontal);
      console.pushToStdOut(formatter.toString());
      console.pushToStdErr("Returned " + table.size() + " rows\n");
    } catch (Exception e) {
      System.err.println("Error: " + e);
    }
  }
  
  public void run() throws IOException {
    Console console = new Console();
    ConsoleOutput line;
    while ((line = console.read("aql> ")) != null) {
      if (line.getBuffer().equalsIgnoreCase("quit") || line.getBuffer().equalsIgnoreCase("exit")) {
        System.exit(0);
      } else
        query(line.getBuffer(), console);
    }
  }
  
  public static void main(String[] args) {
    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();
    Options options = new Options();
    options.addOption("u", "username", true, "QRadar Username");
    options.addOption("p", "password", false, "Prompt for password");
    options.addOption("a", "auth_token", true, "Auth token");
    options.addOption("s", "server", true, "QRadar server");
    options.addOption("o", "outputfile", true, "Prefix to dump queries as csv. Files appear as [prefix]_[query_number].csv");
    options.addOption("ssl", true, "SSL Verification. Options = FULL_VERIFY, NO_TRUST, SELF_SIGNED");
    options.addOption("h", "help", false, "Show usage");
    try {
      CommandLine line = parser.parse( options, args );
      if (line.hasOption('h')) {
        formatter.printHelp("AQL Shell", options);
        System.exit(1);
      }
      Properties props = new Properties();
      if (line.hasOption("u")) {
        props.put("user", line.getOptionValue("username"));
      }
      if (line.hasOption("p")) {
        System.out.print("Password:");
        char[] passwd = System.console().readPassword();
        props.put("password", new String(passwd));
      }
      if (line.hasOption("a")) {
        props.put("auth_token", line.getOptionValue("auth_token"));
      }
      if (line.hasOption("s")) {
        props.put("url", line.getOptionValue("server"));
      } else {
        props.put("url", "localhost:443");
      }
      if (line.hasOption("o")) {
        props.put("outputfile",line.getOptionValue("outputfile"));
      }
      if (line.hasOption("ssl")) {
        props.put("ssl_verify", line.getOptionValue("ssl"));
      }
      Shell shell = new Shell(props, "|");
      shell.run();
    } catch (ParseException exp) {
      System.out.println( "Unexpected exception: " + exp.getMessage() );
      formatter.printHelp( "AQL Shell", options );
    } catch (Exception e) {
      e.printStackTrace(); 
    }
  }
}