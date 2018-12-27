package com.biuzai.larva.engine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBEngine {
	private DataSource ds;

	public DBEngine(DBConfig config) {
		HikariConfig hkconfig = new HikariConfig();
		hkconfig.setAutoCommit(false);
		hkconfig.setConnectionTimeout(2000L);
		hkconfig.setJdbcUrl(config.jdbcUrl());
		hkconfig.setUsername(config.getUsername());
		hkconfig.setPassword(config.getPassword());
		hkconfig.setMinimumIdle(config.getMinIdle());
		hkconfig.setMaximumPoolSize(config.getMaxPoolSize());
		hkconfig.setConnectionTestQuery("select 1");
		hkconfig.setDriverClassName(config.driverClass());
		hkconfig.setAutoCommit(true);
		HikariDataSource dataSource = new HikariDataSource(hkconfig);
		this.ds = dataSource;
	}

	// 数据写操作
	public boolean exec(String sql, Object... args) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = this.ds.getConnection();
			ps = conn.prepareStatement(sql);
			if (args != null && args.length != 0) {
				for (int i = 0; i < args.length; i++) {
					ps.setObject(i + 1 , args[i]);
				}
			}
			return !ps.execute();
		}finally {
			this.close(conn, ps);
		}
	}

	// 数据库读操作
	public ResultSet query(String sql, Object... args) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = this.ds.getConnection();
			ps = conn.prepareStatement(sql);
			if (args != null && args.length != 0) {
				for (int i = 0; i < args.length; i++) {
					ps.setObject(i, args[i]);
				}
			}
			return ps.executeQuery();
		}finally {
			this.close(conn, ps);
		}
	}

	public void close(Connection conn, Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
