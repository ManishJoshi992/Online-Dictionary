package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {
	private Connection jdbcConnection;
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;

	public DbConnection(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public Connection getConnetion() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
		return jdbcConnection;
	}

	public List<Dictionary> listAll(String input) throws SQLException {
		List<Dictionary> list = new ArrayList<>();
		String sql = "SELECT * FROM entries WHERE word= '" + input + "'";
		Connection conn = this.getConnetion();
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			String word = resultSet.getString("word");
			String wordType = resultSet.getString("wordtype");
			String definition = resultSet.getString("definition");
			Dictionary dictionary = new Dictionary(word, wordType, definition);
			list.add(dictionary);
		}
		resultSet.close();
		statement.close();
		if (null != conn) {
			conn.close();
		}
		return list;
	}
}