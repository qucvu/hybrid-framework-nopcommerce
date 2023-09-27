package jdbc.database.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerConnUtils {
	public static Connection getSQLServerConnection() {
		String hostName = "localhost";
		String sqlInstanceName = "SQLEXPRESS";
		String database = "AutomationDatabaseTesting";
		String userName = "";
		String password = "";

		return getSQLServerConnection(hostName, sqlInstanceName, database, userName, password);
	}

	public static Connection getSQLServerConnection(String hostName, String sqlInstanceName, String database, String userName, String password) {
		Connection conn = null;
		try {
			// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// Cấu trúc URL Connection dành cho SQL Server
//			String connectionURL = "jdbc:sqlserver://" + hostName + ":1433" + ";instance=" + sqlInstanceName + ";databaseName=" + database;
//			conn = DriverManager.getConnection(connectionURL, userName, password);
			
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AutomationDatabaseTesting;integratedSecurity=true;");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
