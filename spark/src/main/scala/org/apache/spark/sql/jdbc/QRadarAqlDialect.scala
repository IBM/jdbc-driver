package org.apache.spark.sql.jdbc

case object QRadarAqlDialect extends JdbcDialect {

  override def canHandle(url : String): Boolean = url.startsWith("jdbc:qradar")

  override def getTableExistsQuery(table: String): String = {
    val p1 = "^SELECT\\s+(.*)\\s+FROM\\s+(\\S+)\\s+WHERE\\s+(.*)$".r
    val p2 = "^SELECT\\s+(.*)\\s+FROM\\s+(\\S+)\\s+$".r
    table match {
      case p1(fields,tab,wh) => s"SELECT 1 FROM $tab LIMIT 1"
      case p2(fields,tab) => s"SELECT 1 FROM $tab LIMIT 1"
      case _ => s"SELECT 1 FROM $table LIMIT 1"
    }
  }
}