package bluebomb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	

	public static final	JDBCDriverPostgresSQL = "com.postgres.jdbc.Driver";
	public static final String JDBCURLPostgresSQL = "jdbc:postgresql://localhost:5432/Server_BackEnd";
    private final String user = "postgres";
    private final String password = "postgres";
    /*
private final String url = "jdbc:postgresql://localhost/dvdrental";

*/
	protected Statement statement;
	protected Connection connection;
	
	public Statement getStatement() {
		return statement;
	}

	public Connection getConnection() {
		return connection;
	}

	/**
	 * Establishes the connection with DB
	 * ResultSet.TYPE_FORWARD_ONLY
	 * ResultSet.TYPE_SCROLL_INSENSITIVE
	 * ResultSet.TYPE_SCROLL_SENSITIVE
	 *
	 * ResultSet.CONCUR_READ_ONLY
	 * ResultSet.CONCUR_UPDATABLE
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void open() throws ClassNotFoundException, SQLException {
		Class.forName(JDBCDriverPostgresSQL);
		connection = DriverManager.getConnection(JDBCURLPostgresSQL);
		statement = connection.createStatement();
		statement.setQueryTimeout(30); 
	}

	public void verify() throws SQLException {
		try {
		//	statement.executeQuery("SELECT * FROM  LIMIT 1");
		} catch (SQLException e) {
		//	statement.executeUpdate("INSERT INTO URI (id, title, ) VALUES...");
		}
	}

	public ResultSet executeQuery(String query) throws SQLException {
		return statement.executeQuery(query);
	}

	public int executeUpdate(String query) throws SQLException {
		return statement.executeUpdate(query);
	}

	public void close() throws SQLException {
		if (connection != null) {
			statement.close();
			connection.close();
		}
	}

}
