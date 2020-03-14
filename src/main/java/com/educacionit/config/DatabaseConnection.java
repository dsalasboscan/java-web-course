package com.educacionit.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/dbo?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "Admin123#";
	
	public static Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public static void close(Connection connection) {
	    try {
	      connection.close();
	    } catch (SQLException ex) {
	      ex.printStackTrace(System.out);
	    }
	  }
}
