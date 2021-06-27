package me.kvq.supertrailspro.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class STMySQL implements Database {
	
	private Connection connection;
	private String url, dbname, login, password;
	private int port;
	
	public STMySQL(String urlOrIP, int port, String dbname, String login,String password) throws ClassNotFoundException, SQLException {
		this.url = urlOrIP; this.port = port; this.dbname = dbname; this.login = login; this.password = password; 
		getConnection();
	}

	@Override
	public void execute(DBAction action) throws SQLException {
		try {
			
			String request = action.getQuery();
			Statement statement = getConnection().createStatement();
			
			if (action.getType() == DBRequestType.UPDATE) {
				statement.executeUpdate(request);
			}
			
			if (action.getType() == DBRequestType.QUERY) {
				ResultSet result = statement.executeQuery(request);
				action.getFunc().accept(result);
			} 
			
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
	}
	
	private Connection getConnection() throws SQLException, ClassNotFoundException {
		if (connection != null && !connection.isClosed()) {
			return connection;
		}
		String connectionURL = "jdbc:mysql://"
				+ url + ":" + port +
				"/" + dbname;
	
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(connectionURL,
				login, password);
		return connection;
	}

}
