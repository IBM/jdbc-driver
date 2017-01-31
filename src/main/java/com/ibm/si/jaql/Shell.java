package com.ibm.si.jaql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Properties;
import java.io.Console;
import java.lang.StringBuilder;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Shell {
  Connection c;
  String sep = "|";
  boolean prompt = true;
  private static Logger logger = LogManager.getLogger();
  public Shell (Properties props, String separator) throws Exception {
    logger.info("Current props {}", props);
    sep = separator;
    c = DriverManager.getConnection("jdbc:qradar://" + props.getProperty("url"), props);
    logger.info("Initialized connection", c);
  }
  
  public void run() {
    Scanner reader = new Scanner(System.in);
    while (true) {
      if (prompt)
        System.out.print("aql> ");
      String sql = reader.nextLine().trim();
      if (sql.equalsIgnoreCase("exit") || sql.equalsIgnoreCase("quit"))
        System.exit(0);
      try {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsMeta = rs.getMetaData();
        StringBuilder buff = new StringBuilder();
        for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
          buff.append((i > 1 ? " " : "") + sep + " " + rsMeta.getColumnLabel(i));
        }
        buff.append(" " + sep);
        logger.info("Line length {}", buff.length());
        System.out.print("+");
        for (int i = 0; i < buff.length()-2; i++)
          System.out.print("-");
        System.out.println("+");
        System.out.println(buff.toString());
        System.out.print("+");
        for (int i = 0; i < buff.length()-2; i++)
          System.out.print("-");
        System.out.println("+");
        while (rs.next()) {
          for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
            System.out.print((i > 1 ? " " : "") + sep + " " + rs.getString(i));
          }
          System.out.println(" " + sep);
        }
        System.out.print("+");
        for (int i = 0; i < buff.length()-2; i++)
          System.out.print("-");
        System.out.println("+");
      } catch (Exception e) {
        System.err.println("Error: " + e);
      }
    }
  }
  
  public static void main(String[] args) throws Exception {
    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();
    Options options = new Options();
    options.addOption("u", "username", true, "QRadar Username");
    options.addOption("p", "password", false, "Prompt for password");
    options.addOption("a", "auth_token", true, "Prompt for auth token");
    options.addOption("s", "server", true, "QRadar server");
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
      Shell shell = new Shell(props, "|");
      shell.run();
    } catch (ParseException exp) {
      System.out.println( "Unexpected exception: " + exp.getMessage() );
      formatter.printHelp( "AQL Shell", options );
    }
  }
}