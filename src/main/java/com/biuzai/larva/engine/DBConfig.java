package com.biuzai.larva.engine;

public class DBConfig {
	private String dbtype; //数据库类型
	private String host;
	private int port;
	private String dbname;
	private String username;
	private String password;
	private int minIdle;   //最小空闲连接数
	private int maxPoolSize; //最大连接数
	
	public DBConfig(String host, String dbname , String username, String password) {
		this.dbtype = "mysql";
		this.host = host;
		this.port = 3306;
		this.dbname = dbname;
		this.username = username;
		this.password = password;
		this.minIdle = 5;
		this.maxPoolSize = 30;
	}
	
	public DBConfig(String dbtype, String host, int port, String dbname, String username, String password, int minIdle, int maxPoolSize) {
		this.dbtype = dbtype;
		this.host = host;
		this.port = port;
		this.dbname = dbname;
		this.username = username;
		this.password = password;
		this.minIdle = minIdle;
		this.maxPoolSize = maxPoolSize;
	}
	
	public String driverClass() {
		String driverClass = "com.mysql.cj.jdbc.Driver";
		switch (this.dbtype) {
		case "mysql":
			driverClass = "com.mysql.cj.jdbc.Driver";
			break;
		}
		return driverClass;
	}
	
	public String jdbcUrl() {
		return String.format("jdbc:%s://%s:%d/%s?useUnicode=true&characterEncoding=utf-8&autoReconnect=true", this.dbtype, this.host, this.port, this.dbname);
	}
	
	public String getDbtype() {
		return dbtype;
	}

	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}

	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	public int getMaxPoolSize() {
		return maxPoolSize;
	}
	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}
}
