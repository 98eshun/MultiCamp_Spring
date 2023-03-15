package chap08.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.*;

public class MemberRowMapper implements RowMapper<Member>{

	@Override						
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member();
		member.setId(rs.getLong("id"));
		member.setEmail(rs.getString("email"));
		member.setPassword(rs.getString("password"));
		member.setName(rs.getString("name"));
		member.setRegisterDateTime(rs.getTimestamp("regdate").toLocalDateTime());
		
		return member;
	}
}
