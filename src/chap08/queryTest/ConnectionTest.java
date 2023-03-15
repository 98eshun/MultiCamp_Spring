package chap08.queryTest;

import java.sql.*;

import org.apache.tomcat.jdbc.pool.DataSource;	// 얘를 import 해야함

public class ConnectionTest {
	
	private DataSource ds;

	public ConnectionTest() {
		ds = new DataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("scott");
		ds.setPassword("tiger");
		ds.setInitialSize(2);
		ds.setMinIdle(2);
		ds.setMaxIdle(5);
	}
	
	public static void main(String[] args) {
		ConnectionTest test = new ConnectionTest();
		Connection conn = null;
		
		try {
			conn = test.ds.getConnection();
			
			System.out.println(conn.isClosed() ? "연결 실패!" : "연결 성공!");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!conn.isClosed()) {
					conn.close();
					System.out.println(conn.isClosed() ? "연결 종료" : "");
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}













