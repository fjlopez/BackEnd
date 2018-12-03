package bluebomb.urlshortener.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBmanager {
	
	private final static String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private final static String HOST = "localhost";
    private final static String PORT = "5432";
	private final static String DATABASE = "postgres";
	private final static String DRIVER_URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE;
	private final static String USER = "bluebomb";
	private final static String PASSWORD = "unizar";
	static {			
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.err);
		}	
	}

	private DBmanager(){
	}
	
	public final static Connection getConnection()
		throws SQLException {
			return DriverManager.getConnection(DRIVER_URL, USER, PASSWORD);
	}
}