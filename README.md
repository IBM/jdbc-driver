# DEPRECATED #

This source code remains available for historical purposes, but it is no longer actively maintained or supported by IBM. 

Issues and pull requests may not be responded to in a timely fashion, or at all.

[![Build Status](https://travis.ibm.com/infosec/jdbc-driver.svg?token=Fx3jYFGyqy98EpQ63Kbn&branch=travis)](https://travis.ibm.com/infosec/jdbc-driver)

# jdbc-driver
Extracting of Ariel data (events, flows and simulated arcs) out of a QRadar system through a JDBC driver.

Overview
=======

This project aims to implement and deliver a JDBC compliant Java driver project for exposing Ariel data via AQL queries, from a QRadar system. The project is only compatible with QRadar v7.2.4+.

The usage of the project's built driver jar, is as per standard JDBC Java coded constructs; or with use in conjunction with SQL client tools or reporting engines which support custom drivers (e.g., Birt, Jasper, SqlSquirell, Crystal Reports, Spark, etc.).

These driver and project source code come without warranties of any kind. 

Any issues discovered using the samples should not be directed to QRadar support, but be reported on the Github issues tracker. 

The driver does not aim to be fully compliant to the JDBC specification; but aims to allow QRadar Advance Query Language (AQL) sql statements to be run against a QRadar system, to return readonly datasets; whilst progressively implement improved compliance against the JDBC spec.

Development notes
=============
## Prerequisites

1. git 1.7.1 (or later) - http://git-scm.com/downloads
2. maven 3.2.2 (or later) - http://maven.apache.org/download.cgi
3. Java JDK (1.7+) - http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html

### Clone:
```
git clone https://github.com/ibm-security-intelligence/jdbc-driver.git
```

### Useful Commands:

#### 1) Build, (with no tests), package the driver jar:
```
mvn -Dmaven.test.skip=true clean package
```


If you want to both build AND run the test suite, or portions of the test suite. You will need to edit all the *Test.properties files under the <jdbc-driver>/src/test directory amending all the properties in each file to suite your QRadar environment. The username/password combination must be a valid QRadar user, attached to a User Role, defined with both Log Activity and Network Activity privileges granted. For testing convenience, use the QRadar admin user for connection.


```
ip=localhost
user=admin
password=password
url=jdbc:qradar://localhost/
```


#### 2) Build, run all tests, package the driver jar:
```
mvn clean package
```

#### 2) Build, run specific test 
```
mvn -Dtest=SomeTestClass#someTestMethod test 
```

### Download and set up Eclipse dependancies
```
mvn -DoutputDirectory=./lib dependency:copy-dependencies
```

### Output

- _\<jdbc-driver\>_/target/jaql-0.2.1.jar
- _\<jdbc-driver\>_/target/jaql-0.2.1-jar-with-dependencies.jar

Use the resulting **jaql-0.2.1-jar-with-dependencies.jar** as your jdbc driver, with your reporting engine / SQL client of choice to connect to an Ariel datastore.

Usage
=====
Key notes for usage:

- **main Driver class**: `com.ibm.si.jaql.Driver`
- **url**: jdbc:qradar://_Qradar-Console_/
- **username**: _admin-user_
- **password**: _admin-user-password_
- **auth\_token**: _auth-token_

Note that you will need either the _auth_token_ or a _username_ and _password_.

# AQL SQL Syntax
The publicly available documentation stack for IBM's Qradar Security Intelligence Platform, includes a reference section for Qradar's Ariel Database Query Language.

- http://www-01.ibm.com/support/knowledgecenter/SS42VS_7.2.4/com.ibm.qradar.doc_7.2.4/c_aql_intro.html?lang=en 

## Interactive Shell
You can now use the jdbc-driver as an interactive shell to issue AQL queries against your QRadar instance. The command line has a simple syntax and supports common features, such as history (including persistence), search, line editing, emacs and vi bindings, etc. It currently relies on [Æsh](https://github.com/aeshell/aesh) [0.33.x](https://github.com/aeshell/aesh/tree/0.33.x) for the more advanced features.

```bash
$ java -jar target/jaql-0.2.1-jar-with-dependencies.jar -h
usage: AQL Shell
 -a,--auth_token <arg>   Prompt for auth token
 -h,--help               Show usage
 -p,--password           Prompt for password
 -s,--server <arg>       QRadar server
 -u,--username <arg>     QRadar Username
$ java -jar target/jaql-0.2.1-jar-with-dependencies.jar -a d0a0295e-031c-45e3-b6f0-84fe26d74d84
aql> select * from events limit 5;
+-------------+---------------+------------+------------+------------+----------+-------------+---------------+----------+-----------------+----------+-----------+------------+
|    sourceip | destinationip | eventcount | sourceport | protocolid | username | logsourceid |     starttime | category | destinationport |      qid | magnitude | identityip |
+-------------+---------------+------------+------------+------------+----------+-------------+---------------+----------+-----------------+----------+-----------+------------+
|   127.0.0.1 |     127.0.0.1 |          1 |          0 |        255 |     null |          67 | 1485966540068 |    10009 |               0 | 68750085 |         3 |    0.0.0.0 |
| 10.10.11.47 |     127.0.0.1 |          1 |          0 |        255 |     null |          65 | 1485966539906 |     8052 |               0 | 38750003 |         5 |    0.0.0.0 |
| 10.10.11.47 |     127.0.0.1 |          1 |          0 |        255 |     null |          65 | 1485966540003 |     8052 |               0 | 38750003 |         5 |    0.0.0.0 |
| 10.10.11.47 |     127.0.0.1 |          1 |          0 |        255 |     null |          65 | 1485966540003 |     8052 |               0 | 38750003 |         5 |    0.0.0.0 |
|   127.0.0.1 |     127.0.0.1 |          1 |          0 |        255 |     null |          67 | 1485966540068 |    10009 |               0 | 68750085 |         3 |    0.0.0.0 |
+-------------+---------------+------------+------------+------------+----------+-------------+---------------+----------+-----------------+----------+-----------+------------+
Returned 5 rows
aql> (reverse-i-search) `ev': select * from events limit 5;
```

## SparkSQL Support
SparkSQL supports the ability to directly query an SQL database using a JDBC driver and load the results into a DataFrame for further processing. This package has experimental support for identifying and converting Spark-generated SQL queries into valid AQL queries and handling nuances of the AQL REST interface when possible. In Spark, when reading `jdbc` formatted data, simply specify add `com.ibm.si.jaql.Driver` for the `driver` and enable Spark support through the `.option("spark", "true")`.

### Example
```scala
val dataframe_qradar = sqlContext.read.format("jdbc").option("url", "jdbc:qradar://127.0.0.1:443/")
 .option("driver", "com.ibm.si.jaql.Driver")
 .option("dbtable", "(SELECT sourceip,destinationip,username FROM events)")
 .option("user", "admin")
 .option("password", "password")
 .option("spark", "true")
 .option("auth_token", "bd576741-fdc2-41c8-9e34-728f05036eed")
 .load()
```
```
dataframe_qradar: org.apache.spark.sql.DataFrame = [sourceip: string, destinationip: string, username: string]
```
```scala
dataframe_qradar.show
```
```
+------------+-------------+--------------+
|  sourceip  |destinationip|   username   |
+------------+-------------+--------------+
|10.10.12.168|  127.0.0.1  |     NULL     |
|10.10.12.168|  127.0.0.1  |     NULL     |
|10.10.12.168| 10.10.12.168|configservices|
|10.10.12.168| 10.10.12.168|configservices|
|10.10.12.168| 10.10.12.168|configservices|
|10.10.12.168| 10.10.12.168|configservices|
|10.10.12.168| 10.10.12.168|configservices|
|10.10.12.168| 10.10.12.168|configservices|
|10.10.12.168| 10.10.12.168|configservices|
|10.10.12.168| 10.10.12.168|configservices|
|10.10.12.168| 10.10.12.168|configservices|
|10.10.12.168| 10.10.12.168|configservices|
|10.10.12.168| 10.10.12.168|configservices|
|10.10.12.168| 10.10.12.168|configservices|
|10.10.12.168|  127.0.0.1  |     NULL     |
|10.10.12.168|  127.0.0.1  |     NULL     |
|10.10.12.168|  127.0.0.1  |     NULL     |
|10.10.12.168|  127.0.0.1  |     NULL     |
|10.10.12.168|  127.0.0.1  |     NULL     |
|10.10.12.168|  127.0.0.1  |     NULL     |
+------------+-------------+--------------+
only showing top 20 rows
```
```scala
dataframe_qradar.groupBy("sourceip").count().show
```
```
+------------+-----+
|  sourceip  |count|
+------------+-----+
|10.10.12.168| 356 |
|  127.0.0.1 |  2  |
+------------+-----+
```
### Known Issues
1. Not all types are correctly mapped to SQL Types. The JDBC driver performs a best-guess regarding the type of the column returned by QRadar, but is not always correct.
  - QRadar returns the incorrect type for some columns in a `/api/ariel/databases/<table>` API call, e.g., `endtime` is returned as a `JAVA_OBJECT` and not a `BIGINT`
  - Aliases to known types will fail. For example, `DATEFORMAT(starttime, 'YYYY-MM-dd HH:mm:ss') as starttime` will return a `BIGINT` and not a `VARCHAR`
2. In Spark Mode, not all functions work correctly. E.g., getting `df.count()` on a DateFrame will yield an SQL query `SELECT 1 FROM (...)` query, which cannot be converted into AQL.
