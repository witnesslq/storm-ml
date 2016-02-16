package com.disruptive.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class MySQLHelper {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(MySQLHelper.class);

	private static final String DB_DRIVER;
	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PASSWORD;

	private static final HikariConfig config = new HikariConfig();
	private static final HikariDataSource ds;

	static {
		DB_DRIVER = PropertiesUtil.getPropertyString("mysql.db_driver");
		DB_URL = PropertiesUtil.getPropertyString("mysql.db_url");
		DB_USER = PropertiesUtil.getPropertyString("mysql.db_user");
		DB_PASSWORD = PropertiesUtil.getPropertyString("mysql.db_password");

		config.setMinimumIdle(1);
		config.setMaximumPoolSize(1);
		config.setDriverClassName(DB_DRIVER);
		config.setJdbcUrl(DB_URL);
		config.setUsername(DB_USER);
		config.setPassword(DB_PASSWORD);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		ds = new HikariDataSource(config);
	}

	private static Connection getConn() throws ClassNotFoundException,
			SQLException {
		// Class.forName(DB_DRIVER);
		// return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		return ds.getConnection();
	}

	public static int executeUpdate(final String sql) throws Exception {
		int result = -1;
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = getConn();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			LOGGER.info("ERROR:{}", e.getMessage());
			throw e;
		} catch (SQLException e) {
			LOGGER.info("ERROR:{}", e.getMessage());
			throw e;
		} finally {
			close(conn, stmt, null);
		}
		return result;
	}

	public static int executeUpdate(String sql, Object[] params)
			throws Exception {
		int result = -1;
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = getConn();
			stmt = conn.prepareStatement(sql);
			//System.out.println(sql + "|" + params.length);
			if (params != null) {
				int i = 0;
				for (Object obj : params) {
					stmt.setObject(i + 1, obj);
					i++;
				}
			}
			result = stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} finally {
			close(conn, stmt, null);
		}
		return result;
	}

	public static boolean dbIsHas(String tableName) throws Exception {
		ResultSet rs = null;
		boolean re = false;
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("show tables ");
			while (rs.next()) {
				if (rs.getString(1).trim().equals(tableName)) {
					re = true;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn, stmt, rs);
		}
		return re;
	}

	public static List<HashMap<String, Object>> ExecuteQuery(final String sql,
			Object[] params) throws Exception {
		List<HashMap<String, Object>> datas = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		Connection conn = getConn();
		try {
			sta = conn.prepareStatement(sql);
			if (params != null) {
				int i = 0;
				for (Object obj : params) {
					sta.setObject(i + 1, obj);
					i++;
				}
			}
			rs = sta.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int recount = rsmd.getColumnCount();
			String[] colLabels = new String[recount];
			for (int i = 0; i < recount; i++) {
				colLabels[i] = rsmd.getColumnLabel(i + 1);
			}
			datas = new ArrayList<HashMap<String, Object>>();
			while (rs.next()) {
				HashMap<String, Object> data = new HashMap<String, Object>();
				for (int i = 0; i < colLabels.length; i++) {
					data.put(colLabels[i], rs.getObject(colLabels[i]));
				}
				datas.add(data);
			}
		} catch (Exception e) {
			LOGGER.info("ERROR:{}", e.getMessage());
			throw e;
		} finally {
			close(conn, sta, rs);
		}
		return datas;
	}

	private static void close(final Connection conn, final Statement stmt,
			final ResultSet rs) throws Exception {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException ex) {
			throw ex;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				throw e;
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					throw e;
				}
			}

		}
	}

}
