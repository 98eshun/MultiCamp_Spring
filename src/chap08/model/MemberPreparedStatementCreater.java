package chap08.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class MemberPreparedStatementCreater implements PreparedStatementCreator {



	private Member member;
	private String [] keyColumns;
	
	public MemberPreparedStatementCreater(Member member) {	//KeyHolder를 사용하지 않을 때 생성자 오버로딩
		this(member, new String[]{});
	}
	
	public MemberPreparedStatementCreater(Member member, String [] keyColumns) {	//KeyHolder를 사용할 때 생성자 오버로딩
		this.member = member;
		this.keyColumns = keyColumns;
	}
	
	@Override
	public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
		String sql = "insert into member(id, email, password, name) values(member_id_seq.nextval,?,?,?)";
		PreparedStatement pstmt = null;
		
		if(keyColumns.length > 0) {
			pstmt = conn.prepareStatement(sql,keyColumns);	// KeyHolder를 사용할 때
		}
		else {
			pstmt = conn.prepareStatement(sql);				// KeyHolder를 사용하지 않을 때
		}
		
		pstmt.setString(1, member.getEmail());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getName());
		
		return pstmt;
	}

}

