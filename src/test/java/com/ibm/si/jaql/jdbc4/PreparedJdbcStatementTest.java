package com.ibm.si.jaql.jdbc4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.ibm.si.jaql.jdbc.PreparedJdbcStatement;
import com.ibm.si.jaql.util.BaseTest;

public class PreparedJdbcStatementTest extends BaseTest {

	static final Logger logger = LogManager
			.getLogger(PreparedJdbcStatementTest.class.getName());

	@Test
	public void testStatementParamReplacement() throws SQLException {
		String sql = "select * from events where sourceip = ? and destination ip = ? and endtime > ? and eventcount > ?";
		PreparedJdbcStatement ps = new PreparedJdbcStatement(null);
		ps.setPreparedSql(sql);
		ps.setString(1, "1.2.3.4");
		ps.setString(2, "4.3.2.1");
		ps.setTimestamp(3, new Timestamp(0));
		ps.setInt(4, 0);

		String newSql = ps.getParamterizedSqlString();
		assertEquals(
				"select * from events where sourceip = '1.2.3.4' and destination ip = '4.3.2.1' and endtime > 0 and eventcount > 0",
				newSql);
	}

	@Test
	public void testEvents_StandardFields() throws Exception {
		Connection con = null;

		try {
			con = DriverManager.getConnection(
					_properties.getProperty("prop.url"),
					_properties.getProperty("prop.user"),
					_properties.getProperty("prop.password"));
			assertNotNull(con);
			PreparedStatement stmt = con
					.prepareStatement("select sourceip, username, logsourceid, endtime from events");
			assertNotNull(stmt);
			ResultSet rs = stmt.executeQuery();
			assertNotNull(rs);
			while (rs.next()) {
				final String sourceip = rs.getString(1);
				final String username = rs.getString(2);
				final long logsourceid = rs.getLong(3);
				final long endtime = rs.getLong(4);
				logger.debug(String
						.format("Source ip %s, username %s, logsourceid %d, endtime %d",
								sourceip, username, logsourceid, endtime));
				assertNotNull(sourceip);
        // Username is null for many datasets
        // assertNotNull(username);
				assertTrue(logsourceid > 0L);
				assertTrue(endtime > 0L);
			}

			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(Types.VARCHAR, rsMeta.getColumnType(1));
			assertNotNull(rsMeta.getColumnTypeName(1));

			assertNotNull(rsMeta.getColumnLabel(2));
			assertNotNull(rsMeta.getColumnType(2));
			assertEquals(Types.VARCHAR, rsMeta.getColumnType(2));
			assertNotNull(rsMeta.getColumnTypeName(2));

			assertNotNull(rsMeta.getColumnLabel(3));
			assertNotNull(rsMeta.getColumnType(3));
			assertEquals(Types.INTEGER, rsMeta.getColumnType(3));
			assertNotNull(rsMeta.getColumnTypeName(3));

			assertNotNull(rsMeta.getColumnLabel(4));
			assertNotNull(rsMeta.getColumnType(4));
			assertEquals(Types.VARCHAR, rsMeta.getColumnType(4));
			assertNotNull(rsMeta.getColumnTypeName(4));
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testEvents_StandardFields_CustomField() throws Exception {
		Connection con = null;

		try {
			con = DriverManager.getConnection(
					_properties.getProperty("prop.url"),
					_properties.getProperty("prop.user"),
					_properties.getProperty("prop.password"));
			assertNotNull(con);
			PreparedStatement stmt = con
					.prepareStatement("select sourceip, username, logsourceid, endtime, virusName from events");
			assertNotNull(stmt);
			ResultSet rs = stmt.executeQuery();
			assertNotNull(rs);
			while (rs.next()) {
				final String sourceip = rs.getString(1);
				final String username = rs.getString(2);
				final long logsourceid = rs.getLong(3);
				final long endtime = rs.getLong(4);
				final String virusName = rs.getString(5);
				logger.debug(String
						.format("Source ip %s, username ip %s, logsourceid %d, endtime %d, virusName %s",
								sourceip, username, logsourceid, endtime,
								virusName));
				assertNotNull(sourceip);
        // username is null for many datasets
        // assertNotNull(username);
				assertTrue(logsourceid > 0L);
				assertTrue(endtime > 0L);
        // assertNotNull(virusName);
			}

			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(Types.VARCHAR, rsMeta.getColumnType(1));
			assertNotNull(rsMeta.getColumnTypeName(1));

			assertNotNull(rsMeta.getColumnLabel(2));
			assertNotNull(rsMeta.getColumnType(2));
			assertEquals(Types.VARCHAR, rsMeta.getColumnType(2));
			assertNotNull(rsMeta.getColumnTypeName(2));

			assertNotNull(rsMeta.getColumnLabel(3));
			assertNotNull(rsMeta.getColumnType(3));
			assertEquals(Types.INTEGER, rsMeta.getColumnType(3));
			assertNotNull(rsMeta.getColumnTypeName(3));

			assertNotNull(rsMeta.getColumnLabel(4));
			assertNotNull(rsMeta.getColumnType(4));
			assertEquals(Types.VARCHAR, rsMeta.getColumnType(4));
			assertNotNull(rsMeta.getColumnTypeName(4));

			assertNotNull(rsMeta.getColumnLabel(5));
			assertNotNull(rsMeta.getColumnType(5));
			assertEquals(Types.VARCHAR, rsMeta.getColumnType(5));
			assertNotNull(rsMeta.getColumnTypeName(5));
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testEvents_StandardFields_MgmtField() throws Exception {
		Connection con = null;

		try {
			con = DriverManager.getConnection(
					_properties.getProperty("prop.url"),
					_properties.getProperty("prop.user"),
					_properties.getProperty("prop.password"));
			assertNotNull(con);
			PreparedStatement stmt = con
					.prepareStatement("select sourceip, username, logsourceid, endtime, 'MGMT: Bandwidth Manager - QDisc/Class/Filter - TC Object Type' from events");
			assertNotNull(stmt);
			ResultSet rs = stmt.executeQuery();
			assertNotNull(rs);
			while (rs.next()) {
				final String sourceip = rs.getString(1);
				final String username = rs.getString(2);
				final long logsourceid = rs.getLong(3);
				final long endtime = rs.getLong(4);
				final String mgmt = rs.getString(5);
				assertNotNull(sourceip);
        // Username is null for many datasets
        // assertNotNull(username);
				assertTrue(logsourceid > 0L);
				assertTrue(endtime > 0L);
				assertNotNull(mgmt);
				logger.debug(String
						.format("Source ip %s, username ip %s, logsourceid %d, endtime %d, mgmt-bandwith %s",
								sourceip, username, logsourceid, endtime, mgmt));
			}

			ResultSetMetaData rsMeta = rs.getMetaData();
			assertNotNull(rsMeta);
			assertNotNull(rsMeta.getColumnCount());
			assertNotNull(rsMeta.getColumnLabel(1));
			assertNotNull(rsMeta.getColumnType(1));
			assertEquals(Types.VARCHAR, rsMeta.getColumnType(1));
			assertNotNull(rsMeta.getColumnTypeName(1));

			assertNotNull(rsMeta.getColumnLabel(2));
			assertNotNull(rsMeta.getColumnType(2));
			assertEquals(Types.VARCHAR, rsMeta.getColumnType(2));
			assertNotNull(rsMeta.getColumnTypeName(2));

			assertNotNull(rsMeta.getColumnLabel(3));
			assertNotNull(rsMeta.getColumnType(3));
			assertEquals(Types.INTEGER, rsMeta.getColumnType(3));
			assertNotNull(rsMeta.getColumnTypeName(3));

			assertNotNull(rsMeta.getColumnLabel(4));
			assertNotNull(rsMeta.getColumnType(4));
			assertEquals(Types.VARCHAR, rsMeta.getColumnType(4));
			assertNotNull(rsMeta.getColumnTypeName(4));

			assertNotNull(rsMeta.getColumnLabel(5));
			assertNotNull(rsMeta.getColumnType(5));
			assertEquals(Types.VARCHAR, rsMeta.getColumnType(5));
			assertNotNull(rsMeta.getColumnTypeName(5));
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
