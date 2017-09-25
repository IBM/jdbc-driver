package com.ibm.si.jaql.jdbc;

import java.awt.font.NumericShaper;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.si.jaql.jdbc4.Jdbc4Connection;
import com.ibm.si.jaql.jdbc4.Jdbc4Statement;

/**
 * PreparedStatements ariel query wrapper
 * 
 * @author IBM
 *
 */
public class PreparedJdbcStatement extends Jdbc4Statement implements
		PreparedStatement {
	static final Logger logger = LogManager
			.getLogger(PreparedJdbcStatement.class.getName());

	private static final String DOUBLE_QUOTE_ESCAPED_STRING_FORMAT = "\"%s\"";
	private static final String SINGLE_QUOTE_ESCAPED_STRING_FORMAT = "'%s'";
	private static final String ESCAPED_QUESTION_MARK = "\\?";
	private String sql;
	private ResultSetMetaData resultSetMetaData = null;
	private Map<Integer, Object> paramMap = new TreeMap<Integer, Object>();

	public PreparedJdbcStatement(Jdbc4Connection connection) {
		super(connection);
	}

	public void setPreparedSql(final String sql) {
		this.sql = sql;
	}
	public String getParamterizedSqlString() {
		// update sql from paramterized version
		int previousIdx = 0;
		String newSql = this.sql;

		logger.debug("Original sql string: {}", this.sql);

		for (Entry<Integer, Object> entry : paramMap.entrySet()) {
			if (entry.getKey() != previousIdx + 1) {
				throw new IllegalStateException(
						String.format(
								"Parameter at index %d has not been set for PreparedStatement",
								previousIdx + 1));
			}

			Object value = entry.getValue();

			if (value == null) {
				newSql = newSql.replaceFirst(ESCAPED_QUESTION_MARK, "NULL");
			} else {
				Class<?> valueClz = entry.getValue().getClass();

				logger.debug("Configuring param {} : {} <{}>", previousIdx + 1,
						value, valueClz);

				if (valueClz.equals(boolean.class)
						|| valueClz.equals(Boolean.class)) {
					newSql = newSql.replaceFirst(ESCAPED_QUESTION_MARK, String
							.format(DOUBLE_QUOTE_ESCAPED_STRING_FORMAT, value));
				} else if (Number.class.isAssignableFrom(valueClz)) {
					newSql = newSql.replaceFirst(ESCAPED_QUESTION_MARK,
							String.valueOf(value));
				} else if (java.util.Date.class.isAssignableFrom(valueClz)) {
					newSql = newSql.replaceFirst(ESCAPED_QUESTION_MARK,
							String.valueOf(((java.util.Date) value).getTime()));
				} else {
					newSql = newSql.replaceFirst(ESCAPED_QUESTION_MARK, String
							.format(SINGLE_QUOTE_ESCAPED_STRING_FORMAT,
									entry.getValue()));
				}
			}

			previousIdx++;
		}

		logger.debug("Substituted sql string: {}", newSql);

		if (newSql.contains("?")) {
			throw new IllegalStateException(
					"Prepared statement has not been fully parameterized: "
							+ newSql);
		}

		return newSql;
	}

	public ResultSet executeQuery() throws SQLException {
		ResultSet results = super.executeQuery(getParamterizedSqlString());
		resultSetMetaData = results.getMetaData();
		return results;
	}

	public int executeUpdate() throws SQLException {
		throw new NotImplementedException();
	}

	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		paramMap.put(parameterIndex, null);
	}

	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		paramMap.put(parameterIndex, x);
	}

	public void setByte(int parameterIndex, byte x) throws SQLException {
		paramMap.put(parameterIndex, x);
	}

	public void setShort(int parameterIndex, short x) throws SQLException {
		paramMap.put(parameterIndex, x);
	}

	public void setInt(int parameterIndex, int x) throws SQLException {
		paramMap.put(parameterIndex, x);
	}

	public void setLong(int parameterIndex, long x) throws SQLException {
		paramMap.put(parameterIndex, x);
	}

	public void setFloat(int parameterIndex, float x) throws SQLException {
		paramMap.put(parameterIndex, x);
	}

	public void setDouble(int parameterIndex, double x) throws SQLException {
		paramMap.put(parameterIndex, x);
	}

	public void setBigDecimal(int parameterIndex, BigDecimal x)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setString(int parameterIndex, String x) throws SQLException {
		paramMap.put(parameterIndex, x);
	}

	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		throw new NotImplementedException();
	}

	public void setDate(int parameterIndex, Date x) throws SQLException {
		paramMap.put(parameterIndex, x);
	}

	public void setTime(int parameterIndex, Time x) throws SQLException {
		paramMap.put(parameterIndex, x);
	}

	public void setTimestamp(int parameterIndex, Timestamp x)
			throws SQLException {
		paramMap.put(parameterIndex, x);
	}

	public void setAsciiStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setUnicodeStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setBinaryStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void clearParameters() throws SQLException {
		paramMap.clear();
	}

	public void setObject(int parameterIndex, Object x, int targetSqlType)
			throws SQLException {
		paramMap.put(parameterIndex, x);
	}

	public void setObject(int parameterIndex, Object x) throws SQLException {
		paramMap.put(parameterIndex, x);
	}

	public boolean execute() throws SQLException {
		throw new NotImplementedException();
	}

	public void addBatch() throws SQLException {
		throw new NotImplementedException();
	}

	public void setCharacterStream(int parameterIndex, Reader reader, int length)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setRef(int parameterIndex, Ref x) throws SQLException {
		throw new NotImplementedException();
	}

	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		throw new NotImplementedException();
	}

	public void setClob(int parameterIndex, Clob x) throws SQLException {
		throw new NotImplementedException();
	}

	public void setArray(int parameterIndex, Array x) throws SQLException {
		throw new NotImplementedException();
	}

	public ResultSetMetaData getMetaData() throws SQLException {
		return this.resultSetMetaData;
	}

	public void setDate(int parameterIndex, Date x, Calendar cal)
			throws SQLException {
		throw new NotImplementedException();
	}

		public void setTime(int parameterIndex, Time x, Calendar cal)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setNull(int parameterIndex, int sqlType, String typeName)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setURL(int parameterIndex, URL x) throws SQLException {
		paramMap.put(parameterIndex, x);
	}

	public ParameterMetaData getParameterMetaData() throws SQLException {
		throw new NotImplementedException();
	}

	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		throw new NotImplementedException();
	}

	public void setNString(int parameterIndex, String value)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setNCharacterStream(int parameterIndex, Reader value,
			long length) throws SQLException {
		throw new NotImplementedException();
	}

	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		throw new NotImplementedException();
	}

	public void setClob(int parameterIndex, Reader reader, long length)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setBlob(int parameterIndex, InputStream inputStream, long length)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setNClob(int parameterIndex, Reader reader, long length)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setSQLXML(int parameterIndex, SQLXML xmlObject)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setObject(int parameterIndex, Object x, int targetSqlType,
			int scaleOrLength) throws SQLException {
		throw new NotImplementedException();
	}

	public void setAsciiStream(int parameterIndex, InputStream x, long length)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setBinaryStream(int parameterIndex, InputStream x, long length)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setCharacterStream(int parameterIndex, Reader reader,
			long length) throws SQLException {
		throw new NotImplementedException();
	}

	public void setAsciiStream(int parameterIndex, InputStream x)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setBinaryStream(int parameterIndex, InputStream x)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setCharacterStream(int parameterIndex, Reader reader)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setNCharacterStream(int parameterIndex, Reader value)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		throw new NotImplementedException();
	}

	public void setBlob(int parameterIndex, InputStream inputStream)
			throws SQLException {
		throw new NotImplementedException();
	}

	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		throw new NotImplementedException();
	}
}
