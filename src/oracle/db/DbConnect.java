package oracle.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
	static final String ORACLEDRIVER = "oracle.jdbc.driver.OracleDriver";
//	static final String ORACLE_CLOUD = "jdbc:oracle:thin:@bitcamp_high?TNS_ADMIN=D:/bitjava0719/OracleCloud"; //오라클 클라우드 쓸때!!!!!!!!!!!!!!!!!!!
	static final String ORACLE_CLOUD = "jdbc:oracle:thin:@bitcamp_high?TNS_ADMIN=C:/Users/PC/Desktop/study/OracleCloud"; //오라클 클라우드 쓸때!!!!!!!!!!!!!!!!!!!
	static final String OLACLE_LOCAL = "jdbc:oracle:thin:@localhost:1521:xe"; //로컬로 오라클 연결할 때!!!!!!!!!!	

	//생성자
	public DbConnect() {
		try {
			Class.forName(ORACLEDRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 실패" + e.getMessage());
			e.printStackTrace();
		}
	}

	public Connection getLocalOracle() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(OLACLE_LOCAL, "angel", "a1234");
		} catch (SQLException e) {
			System.out.println("로컬 오라클 연결 실패" + e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
	
	public Connection getCloudOracle() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(ORACLE_CLOUD, "admin", "Moon2549466**");
		} catch (SQLException e) {
			System.out.println("클라우드 오라클 연결 실패" + e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
	
	public void dbClose(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		} catch(SQLException e) {
			
		}
	}
	
	
	public void dbClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch(SQLException e) {
			
		}
	}
	
	public void dbClose(Statement stmt, Connection conn) {
		try {
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		} catch(SQLException e) {
			
		}
	}
	
	public void dbClose(PreparedStatement pstmt, Connection conn) {
		try {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch(SQLException e) {
			
		}
	}
	
	
}
