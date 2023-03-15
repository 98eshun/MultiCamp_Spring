package practice.model;

import org.springframework.jdbc.core.JdbcTemplate;

public class MemberDao {
	
	private String sql;
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao() {
	}
	
	public void Insert(Member member) {
		sql = "insert into member (id, email, password, name)"
				+ "values("
	}
	
	public void Delete() {
		
	}
	
	public void ListAll() {
		
	}
	
	public void Update() {
		
	}
	
}
