package com.minsu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class ConnectSql {
	private static Connection conn;
//	private ConnectSql() {}
	private static Map<String, String> env=System.getenv();
	public Connection getConnection() {
		try {
			
			if(conn==null||conn.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/jsp",
						"scott",
						"tiger");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
