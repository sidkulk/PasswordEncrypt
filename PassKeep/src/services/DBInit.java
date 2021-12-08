package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBInit implements DatabaseService {
	protected static Connection connection;
	protected static Statement stmt;
	private static final String URL = "jdbc:sqlite:PassKeep.db";

	@Override
	public boolean connectToDatabase() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(URL);
//			System.out.println("Connection established!");
			return true;
		} catch (Exception e) {
			System.out.println("Something went wrong! Stack trace: \n");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean createAllTables() {
		try {
			stmt = connection.createStatement();
			stmt.execute(TableClass.CREATE_USER_TAB_QUERY);
			stmt.execute(TableClass.CREATE_PASS_TAB_QUERY);
			//stmt.execute(TableClass.CREATE_USER_PASS_TAB_QUERY);
//			System.out.println("All tables created!");
			return true;
		} catch (Exception e) {
			System.out.println("Error creating all tables!");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean closeAllConnections() {
		try {
			if (connection != null)
				connection.close();
			if(stmt != null)
				stmt.close();
			
			System.out.println("Connection closed!");
			return true;
		} catch (Exception e) {
			System.out.println("Failed to close connection!");
			e.printStackTrace();
		}
		return false;
	}

}
