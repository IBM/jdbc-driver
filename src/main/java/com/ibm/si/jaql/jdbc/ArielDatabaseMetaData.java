package com.ibm.si.jaql.jdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.Driver;
import com.ibm.si.jaql.api.ArielException;
import com.ibm.si.jaql.api.IArielDatabase;
import com.ibm.si.jaql.api.pojo.ArielColumn;

/**
 * Ariel Datastore rdbms compliant meta data handler for the database itself  
 * @author IBM
 *
 */
public class ArielDatabaseMetaData implements DatabaseMetaData
{
	static final Logger logger = LogManager.getLogger(Driver.class.getName());
	
	private IArielDatabase arielDb = null;
	
	private static final String DATABASE_NAME = "Ariel";
	private static final int DATABASE_MAJOR_VERSION = 1;
	private static final int DATABASE_MINOR_VERSION = 0;
	
	private List<Map<String,String>> tableData = new ArrayList<Map<String,String>>();
	private Map<String,List<Map<String,String>>> columnData = new HashMap<String,List<Map<String,String>>>();
	
	public ArielDatabaseMetaData(final IArielDatabase db)  throws ArielException
	{
		final Map<String,String> flowsTable = new HashMap<String,String>();
		final Map<String,String> eventsTable = new HashMap<String,String>();
		final Map<String,String> simArcTable = new HashMap<String,String>();

		//order of insertion important !
		flowsTable.put("TABLE_CAT", "ariel");
		flowsTable.put("TABLE_SCHEM", "ariel");
		flowsTable.put("TABLE_NAME", "flows");
		flowsTable.put("TABLE_TYPE", "VIEW");
		flowsTable.put("REMARKS", "Flow data");
		//TYPE_CAT
		//TYPE_SCHEM
		//TYPE_NAME
		//SELF_REFERENCING_COL_NAME
		//REF_GENERATION
		
		eventsTable.put("TABLE_CAT", "ariel");
		eventsTable.put("TABLE_SCHEM", "ariel");
		eventsTable.put("TABLE_NAME", "events");
		eventsTable.put("TABLE_TYPE", "VIEW");
		eventsTable.put("REMARKS", "Event data");
		
		simArcTable.put("TABLE_CAT", "ariel");
		simArcTable.put("TABLE_SCHEM", "ariel");
		simArcTable.put("TABLE_NAME", "simarc");
		simArcTable.put("TABLE_TYPE", "VIEW");
		simArcTable.put("REMARKS", "QRM SimArc data");
		
		tableData.add(flowsTable);
		tableData.add(eventsTable);
		tableData.add(simArcTable);
		
		this.arielDb = db;
		buildColumnMetaData();
	}
	
	private void buildColumnMetaData()  throws ArielException
	{		
		for (String db :  arielDb.listDatabases())
		{
			Map<String,ArielColumn> tbl = arielDb.getMetaData(db);
			List<Map<String,String>> tableColumns = new ArrayList<Map<String,String>>();
			final Iterator<String> colIter = tbl.keySet().iterator();	
			
			while (colIter.hasNext())
			{
				Map<String,String> columnData = new HashMap<String,String>();
				final String key = colIter.next();
				final ArielColumn col = tbl.get(key);
				columnData.put("TABLE_NAME", db);
				columnData.put("COLUMN_NAME", key);
				columnData.put("DATA_TYPE", "1");
									
				final String jdbcType = Integer.toString(toJDBCType(col.getArgumentType()));
				columnData.put("DATA_TYPE", jdbcType);
				columnData.put("TYPE_NAME", col.getArgumentType());
				
				tableColumns.add(columnData);
			}
			
			columnData.put(db, tableColumns);
		}
	}
	
	public static int toJDBCType(final String type) throws ArielException
	{
		int result = 0;
		logger.debug("toJDBCType:type=" + type);
		try
		{
			final Field typeField = Types.class.getField(type);
			if (typeField != null)
			{
				result = typeField.getInt(Types.class);
				logger.debug("toJDBCType:result=" + result);
			}
		}
		catch (NoSuchFieldException e)
		{
			throw new ArielException(e);
		}
		catch (SecurityException e)
		{
			throw new ArielException(e);
		}
		catch (IllegalArgumentException e)
		{
			throw new ArielException(e);
		}
		catch (IllegalAccessException e)
		{
			throw new ArielException(e);
		}
		
		return result;
	}
	
		public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

		public boolean isWrapperFor(Class<?> iface) throws SQLException
	{
		boolean result = false;
		
		if (iface == this.getClass())
		{
			result = true;
		}
		
		return result;
	}

		public boolean allProceduresAreCallable() throws SQLException
	{
		return false;
	}

		public boolean allTablesAreSelectable() throws SQLException
	{
		return true;
	}

		public String getURL() throws SQLException
	{
		return "http://www.ibm.com/security";
	}

		public String getUserName() throws SQLException
	{
		return "NotARealUser";
	}

	/**
	 * No support for writing to Ariel over JDBC
	 */
		public boolean isReadOnly() throws SQLException
	{
		return true;
	}

		public boolean nullsAreSortedHigh() throws SQLException
	{
		return false;
	}

		public boolean nullsAreSortedLow() throws SQLException
	{
		return false;
	}

		public boolean nullsAreSortedAtStart() throws SQLException
	{
		return false;
	}

		public boolean nullsAreSortedAtEnd() throws SQLException
	{
		return false;
	}

		public String getDatabaseProductName() throws SQLException
	{
		return DATABASE_NAME;
	}

		public String getDatabaseProductVersion() throws SQLException
	{
		return String.format("%d.%d", DATABASE_MAJOR_VERSION, DATABASE_MINOR_VERSION);
	}

		public String getDriverName() throws SQLException
	{
		return "IBM QRadar Data Driver";
	}

		public String getDriverVersion() throws SQLException
	{
		return String.format("%d.%d", DATABASE_MAJOR_VERSION, DATABASE_MINOR_VERSION);
	}

		public int getDriverMajorVersion()
	{
		return DATABASE_MAJOR_VERSION;
	}

		public int getDriverMinorVersion()
	{
		return DATABASE_MINOR_VERSION;
	}

		public boolean usesLocalFiles() throws SQLException
	{
		return false;
	}

		public boolean usesLocalFilePerTable() throws SQLException
	{
		return false;
	}

		public boolean supportsMixedCaseIdentifiers() throws SQLException
	{
		return false;
	}

		public boolean storesUpperCaseIdentifiers() throws SQLException
	{
		return false;
	}

		public boolean storesLowerCaseIdentifiers() throws SQLException
	{
		return false;
	}

		public boolean storesMixedCaseIdentifiers() throws SQLException
	{
		return false;
	}

		public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException
	{
		return false;
	}

		public boolean storesUpperCaseQuotedIdentifiers() throws SQLException
	{
		return false;
	}

		public boolean storesLowerCaseQuotedIdentifiers() throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

		public boolean storesMixedCaseQuotedIdentifiers() throws SQLException
	{
		return false;
	}

		public String getIdentifierQuoteString() throws SQLException
	{
		return "'";
	}

		public String getSQLKeywords() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

		public String getNumericFunctions() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

		public String getStringFunctions() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

		public String getSystemFunctions() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

		public String getTimeDateFunctions() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

		public String getSearchStringEscape() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

		public String getExtraNameCharacters() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

		public boolean supportsAlterTableWithAddColumn() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsAlterTableWithDropColumn() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsColumnAliasing() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean nullPlusNonNullIsNull() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsConvert() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsConvert(int fromType, int toType)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsTableCorrelationNames() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsDifferentTableCorrelationNames() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsExpressionsInOrderBy() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsOrderByUnrelated() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsGroupBy() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsGroupByUnrelated() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsGroupByBeyondSelect() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsLikeEscapeClause() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsMultipleResultSets() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsMultipleTransactions() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsNonNullableColumns() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsMinimumSQLGrammar() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsCoreSQLGrammar() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsExtendedSQLGrammar() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsANSI92EntryLevelSQL() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsANSI92IntermediateSQL() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsANSI92FullSQL() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsIntegrityEnhancementFacility() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsOuterJoins() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsFullOuterJoins() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsLimitedOuterJoins() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public String getSchemaTerm() throws SQLException {
		return "schema";
	}

		public String getProcedureTerm() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

		public String getCatalogTerm() throws SQLException {
		return "database";
	}

		public boolean isCatalogAtStart() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public String getCatalogSeparator() throws SQLException {
		return ".";
	}

		public boolean supportsSchemasInDataManipulation() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsSchemasInProcedureCalls() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsSchemasInTableDefinitions() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsSchemasInIndexDefinitions() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsCatalogsInDataManipulation() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsCatalogsInProcedureCalls() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsCatalogsInTableDefinitions() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsPositionedDelete() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsPositionedUpdate() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsSelectForUpdate() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsStoredProcedures() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsSubqueriesInComparisons() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsSubqueriesInExists() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsSubqueriesInIns() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsSubqueriesInQuantifieds() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsCorrelatedSubqueries() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsUnion() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsUnionAll() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public int getMaxBinaryLiteralLength() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxCharLiteralLength() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxColumnNameLength() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxColumnsInGroupBy() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxColumnsInIndex() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxColumnsInOrderBy() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxColumnsInSelect() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxColumnsInTable() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxConnections() throws SQLException
	{
		return 10;
	}

		public int getMaxCursorNameLength() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxIndexLength() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxSchemaNameLength() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxProcedureNameLength() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxCatalogNameLength() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxRowSize() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public int getMaxStatementLength() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxStatements() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxTableNameLength() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxTablesInSelect() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getMaxUserNameLength() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getDefaultTransactionIsolation() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public boolean supportsTransactions() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsTransactionIsolationLevel(int level)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsDataDefinitionAndDataManipulationTransactions()
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsDataManipulationTransactionsOnly()
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public ResultSet getProcedures(String catalog, String schemaPattern,
			String procedureNamePattern) throws SQLException {
		// TODO Auto-generated method stub
		logger.debug("getProcedures>>");
		return new MetaDataResultSet(new HashMap<String,String>());
	}

		public ResultSet getProcedureColumns(String catalog, String schemaPattern,
			String procedureNamePattern, String columnNamePattern)
			throws SQLException {
		logger.debug("getProcedureColumns>>" + tableData.size());
		// TODO Auto-generated method stub
		return new MetaDataResultSet(new HashMap<String,String>());
	}

		public ResultSet getTables(String catalog, 
								String schemaPattern,
								String tableNamePattern, 
								String[] types) throws SQLException
	{
		logger.debug("getTables>>" + tableData.size());
		StringBuffer strBuf = new StringBuffer(); 
		if (types != null)
		{
			for (String type: types)
			{
				strBuf.append(type).append(", ");
			}
		}
		logger.debug(String.format("ArielDatabaseMetaData>getTables>> catalog=(%s), schemaPattern=(%s), " +
				"tableNamePattern=(%s),types=(%s)", catalog, schemaPattern, tableNamePattern, strBuf.toString()) );
		return new MetaDataResultSet(tableData);
	}

		public ResultSet getSchemas() throws SQLException {
		List<Map<String,String>> cols = new ArrayList<Map<String,String>>();
		Map<String,String> col = new HashMap<String,String>();
		
		col.put("TABLE_SCHEM", "ariel");
		col.put("TABLE_CATALOG", "ariel");
		cols.add(col);
		logger.debug(String.format("ArielDatabaseMetaData>getSchemas()>> catalog=ariel, schema=ariel") );
		return new MetaDataResultSet(cols);
	}

		public ResultSet getCatalogs() throws SQLException
	{
		logger.debug("getCatalogs()>>");
		final Map<String,String> catalogs = new HashMap<String,String>();
		catalogs.put("TABLE_CAT", "ariel");
		return new MetaDataResultSet(catalogs);
	}

		public ResultSet getTableTypes() throws SQLException
	{
		logger.debug("getTableTypes()>>");
		final Map<String,String> types = new HashMap<String,String>();
		types.put("TABLE_TYPE", "VIEW");
		return new MetaDataResultSet(types);
	}

		public ResultSet getColumns(String catalog, 
								String schemaPattern,
								String tableNamePattern, 
								String columnNamePattern)	
			throws SQLException
	{
		logger.debug(String.format("ArielDatabaseMetaData>>getColumns(): catalog=(%s), schemaPattern=(%s), tableNamePattern=(%s), columnNamePattern=(%s)", catalog, schemaPattern, tableNamePattern, columnNamePattern) );
		
		return new MetaDataResultSet(columnData.get(tableNamePattern));
	}

		public ResultSet getColumnPrivileges(String catalog, String schema,
			String table, String columnNamePattern) throws SQLException
	{
		logger.debug("getColumnPrivileges()>>");
		final Map<String,String> privs = new HashMap<String,String>();
		return new MetaDataResultSet(privs);
	}

		public ResultSet getTablePrivileges(String catalog, String schemaPattern,
			String tableNamePattern) throws SQLException {
		// TODO Auto-generated method stub
		return new MetaDataResultSet(new HashMap<String,String>());
	}

		public ResultSet getBestRowIdentifier(String catalog, String schema,
			String table, int scope, boolean nullable) throws SQLException {
		// TODO Auto-generated method stub
		logger.debug("getBestRowIdentifier()>>");
		return new MetaDataResultSet(new HashMap<String,String>());
	}

		public ResultSet getVersionColumns(String catalog, String schema,
			String table) throws SQLException {
		logger.debug("getVersionColumns()>>");
		// TODO Auto-generated method stub
		return new MetaDataResultSet(new HashMap<String,String>());
	}

		public ResultSet getPrimaryKeys(String catalog, String schema, String table)
			throws SQLException {
		logger.debug("getPrimaryKeys()>>");
		// TODO Auto-generated method stub
		return new MetaDataResultSet(new HashMap<String,String>());
	}

		public ResultSet getImportedKeys(String catalog, String schema, String table)
			throws SQLException {
		logger.debug("getImportedKeys()>>");
		// TODO Auto-generated method stub
		return new MetaDataResultSet(new HashMap<String,String>());
	}

		public ResultSet getExportedKeys(String catalog, String schema, String table)
			throws SQLException {
		logger.debug("getExportedKeys()>>");
		// TODO Auto-generated method stub
		return new MetaDataResultSet(new HashMap<String,String>());
	}

		public ResultSet getCrossReference(String parentCatalog,
			String parentSchema, String parentTable, String foreignCatalog,
			String foreignSchema, String foreignTable) throws SQLException {
		// TODO Auto-generated method stub
		logger.debug("getCrossReference()>>");
		return new MetaDataResultSet(new HashMap<String,String>());
	}

		public ResultSet getTypeInfo() throws SQLException
	{
		logger.debug("getTypeInfo()>>");
		Map<String,String> typeInfo = new HashMap<String,String>();
		typeInfo.put("TYPE_INFO", Integer.toString(Types.DISTINCT));
		return new MetaDataResultSet(new HashMap<String,String>());
	}

		public ResultSet getIndexInfo(String catalog, String schema, String table,
			boolean unique, boolean approximate) throws SQLException
	{
		logger.debug("getIndexInfo()>>");
		// TODO Auto-generated method stub
		return new MetaDataResultSet(new HashMap<String,String>());
	}

		public boolean supportsResultSetType(int type) throws SQLException
	{
		boolean result = true;
		if (type == Types.ARRAY)
		{
			result = false;
		}
		
		return result;
	}

		public boolean supportsResultSetConcurrency(int type, int concurrency)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean ownUpdatesAreVisible(int type) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean ownDeletesAreVisible(int type) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean ownInsertsAreVisible(int type) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean othersUpdatesAreVisible(int type) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean othersDeletesAreVisible(int type) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean othersInsertsAreVisible(int type) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean updatesAreDetected(int type) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean deletesAreDetected(int type) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean insertsAreDetected(int type) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsBatchUpdates() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public ResultSet getUDTs(String catalog, String schemaPattern,
			String typeNamePattern, int[] types) throws SQLException {
		
		logger.debug("getUDTs()>>");
		
		Map<String,String> udtMap = new HashMap<String,String>();
		udtMap.put("TYPE_CAT", "Types.VARCHAR");
		udtMap.put("TYPE_SCHEM", "Types.VARCHAR");
		udtMap.put("TYPE_NAME", "Types.VARCHAR");
		udtMap.put("CLASS_NAME", "Types.VARCHAR");
		udtMap.put("DATA_TYPE", "Types.VARCHAR");
		udtMap.put("REMARKS", "Types.VARCHAR");
		udtMap.put("BASE_TYPE", "Types.SMALLINT");
		
        return new MetaDataResultSet(udtMap);
	}

		public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

		public boolean supportsSavepoints() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsNamedParameters() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsMultipleOpenResults() throws SQLException {
		// TODO Auto-generated method stub
	 	return false;
	}

		public boolean supportsGetGeneratedKeys() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public ResultSet getSuperTypes(String catalog, String schemaPattern,
			String typeNamePattern) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

		public ResultSet getSuperTables(String catalog, String schemaPattern,
			String tableNamePattern) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

		public ResultSet getAttributes(String catalog, String schemaPattern,
			String typeNamePattern, String attributeNamePattern)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

		public boolean supportsResultSetHoldability(int holdability)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public int getResultSetHoldability() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getDatabaseMajorVersion() throws SQLException
	{
		return DATABASE_MAJOR_VERSION;
	}

		public int getDatabaseMinorVersion() throws SQLException
	{
		return DATABASE_MINOR_VERSION;
	}

		public int getJDBCMajorVersion() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getJDBCMinorVersion() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public int getSQLStateType() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

		public boolean locatorsUpdateCopy() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean supportsStatementPooling() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public RowIdLifetime getRowIdLifetime() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

		public ResultSet getSchemas(String catalog, String schemaPattern)
			throws SQLException {
		//final Map<String,String> schemaInfo = new HashMap<String,String>();
		//schemaInfo.put("TABLE_SCHEM", "ariel");
		
		//return new MetaDataResultSet(schemaInfo);
		return getSchemas();
	}

		public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

		public ResultSet getClientInfoProperties() throws SQLException {
		// TODO Auto-generated method stub
		logger.debug("getClientInfoProperties()>>");
		
		final Map<String,String> clientInfo = new HashMap<String,String>();
		clientInfo.put("NAME", "ariel");
		clientInfo.put("MAX_LEN", "255");
		clientInfo.put("DEFAULT_VALUE", "ariel");
		clientInfo.put("DESCRIPTION", "ariel");
		
		//return new MetaDataResultSet(new HashMap<String,String>());
		return new MetaDataResultSet(clientInfo);
	}

		public ResultSet getFunctions(String catalog, String schemaPattern,
			String functionNamePattern) throws SQLException {
		// TODO Auto-generated method stub
		logger.debug("getFunctions()>>");
		return new MetaDataResultSet(new HashMap<String,String>());
	}

		public ResultSet getFunctionColumns(String catalog, String schemaPattern,
			String functionNamePattern, String columnNamePattern)
			throws SQLException {
		logger.debug("getFunctionColumns()>>");
		// TODO Auto-generated method stub
		return new MetaDataResultSet(new HashMap<String,String>());
	}

		public ResultSet getPseudoColumns(String catalog, String schemaPattern,
			String tableNamePattern, String columnNamePattern)
			throws SQLException {
		logger.debug("getPseudoColumns()>>");
		// TODO Auto-generated method stub
		return new MetaDataResultSet(new HashMap<String,String>());
	}

		public boolean generatedKeyAlwaysReturned() throws SQLException {
		// TODO Auto-generated method stub
		logger.debug("generatedKeyAlwaysReturned()>>");
		return false;
	}

}
