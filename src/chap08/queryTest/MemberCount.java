package chap08.queryTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.tomcat.jdbc.pool.DataSource;


public class MemberCount {
	
	private DataSource ds;

	public Connection() {
		ds = new DataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("scott");
		ds.setPassword("tiger");
		ds.setInitialSize(2);
		ds.setMinIdle(2);
		ds.setMaxIdle(5);
	}
	
	private int getMemberCount() {
		int rowCount = 0 ;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) as count "
					+ "from member";
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				rowCount = rs.getInt("count");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public static void main(String[] args) {
		MemberCount mc = new MemberCount();
		System.out.println("전체 회원의 수 : " + mc.getMemberCount() + "명 입니다.");

	}

}
