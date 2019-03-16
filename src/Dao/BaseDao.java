package Dao;
import java.sql.*;

public class BaseDao {
	private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
	private static final String DB_USER = "RICHRAIL_ADMIN";
	private static final String DB_PASS = "richrail_admin1234";
	private static Connection conn;

	protected static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}
} 