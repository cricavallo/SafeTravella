package backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBService {
	private static final String DB_URL = "jdbc:mysql://didattica.liceocuneo.it/db5F?useSSL=false";
	private static final String DB_USER = "quintaf";
	private static final String DB_PWD = "pluto21";
	
	@SuppressWarnings("deprecation")
	private Connection getConnection() throws Exception {
		//1) Verifica presenza del Driver
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		
		//2) Connessione alla dbms
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
	}
	
	public ResultSet query(String sql) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			set = stmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
		return set;
	}
}
