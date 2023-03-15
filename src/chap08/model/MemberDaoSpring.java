package chap08.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
public class MemberDaoSpring extends MemberDao{
	
	private JdbcTemplate jdbcTemplate;
	private String sql;
	
	public MemberDaoSpring(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}
	
//-----------------------------------selectByEmail(String email) 구현 --------------------------
	/*	// 1.RowMapper 인터페이스를 익명 클래스로 구현하는 방법
	@Override
	public Member selectByEmail(String email) {
		this.sql = "select * from member where email = ?";
		
		List<Member> results = jdbcTemplate.query(this.sql,
				new RowMapper<Member>() {	// 인터페이스를 구현하는 익명의 클래스를 만듬
			@Override // 인터페이스의 추상 메서드 오버라이딩 // 익명의 클래스를 이용하여 구현하는 방법
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setRegisterDateTime(rs.getTimestamp("regdate").toLocalDateTime());
				
				return member;
			}	
		}, email);
			
		return results.isEmpty() ? null : results.get(0);
	}
	*/
	
	/*	// 2. RowMapper 인터페이스를 람다 표현식(Lambda Expression)으로 구현하는 방법
		// 익명클래스와 람다 표현식을 이용해 구현하면 재사용이 불가능 하다.
	@Override
	public Member selectByEmail(String email) {
		this.sql = "select * from member where email = ?";
		
		List<Member> results = jdbcTemplate.query(this.sql,
				(ResultSet rs, int rowNum) -> {
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setRegisterDateTime(rs.getTimestamp("regdate").toLocalDateTime());
				
				return member;	
		}, email);
			
		return results.isEmpty() ? null : results.get(0);
	}
	*/
	
		// 3. RowMapper 인터페이스를 직접 구현하는 MemberRowMapper 클래스를 생성하여 구현하는 방법
	/*	// List 형태로 반환해줌 = query
	@Override
	public Member selectByEmail(String email) {
		this.sql = "select * from member where email = ?";
		
		List<Member> results = jdbcTemplate.query(this.sql, new MemberRowMapper(), email);
			
		return results.isEmpty() ? null : results.get(0);
	}
	*/
		// 4. queryForObject() 메서드를 사용하여 구현하는 방법
		// 객체를 그대로 반환함 = queryForObject = 반드시 하나의 행을 반환하지 않으면 예외 발생
	@Override
	public Member selectByEmail(String email) {
		this.sql = "select * from member where email = ?";
		Member member = null;
		try {
			member = jdbcTemplate.queryForObject(sql,  new MemberRowMapper(), email);
		}
		catch(EmptyResultDataAccessException e) {
			// throw new MemberNotFoundException();
		}
		return member;
	}
//-----------------------------------insert(Member member) 구현 --------------------------

	/*
	@Override
		// PreparedStatementCreator 인터페이스를 익명 클래스로 구현하면서 KeyHolder , 사용 클래스를 생성해서 사용해도 가능
	public void insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.sql = "insert into member(id, email, password, name) values(member_id_seq.nextval,?,?,?)";
		
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String [] {"id"} ); // 자동 발급되어지는 숫자를 읽어오기 위한 배열
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				
				return pstmt;
			}
		},keyHolder);
		
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());

	}
	*/
	
	@Override
		// PreparedStatementCreator 인터페이스를 구현한 MemberPreparedStatementCreater 클래스를 이용해 구현하면서 KeyHolder 사용
	public void insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new MemberPreparedStatementCreater(member,new String [] {"id"}),keyHolder);
		
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	
	}
	/*
	@Override		// PreparedStatementCreator 인터페이스를 구현한 MemberPreparedStatementCreater 클래스를 이용해 구현하면서 KeyHolder 사용하지 않음
	public void insert(Member member) {
		jdbcTemplate.update(new MemberPreparedStatementCreater(member);
	}
	*/
//-----------------------------------update(Member member) 구현 --------------------------
	@Override
	public void update(Member member) {
		sql = "update member set password = ? where email = ?";
		jdbcTemplate.update(sql,member.getPassword(),member.getEmail());
		
	}
//-----------------------------------selectAll() 구현 --------------------------
	/*	// 1. 익명클래스로 구현하는 방법
	public Collection<Member> selectAll() {
		this.sql = "select * from member order by id";
		
		List<Member> results = jdbcTemplate.query(this.sql,
				new RowMapper<Member>() {	// 인터페이스를 구현하는 익명의 클래스를 만듬
			@Override						// 익명의 클래스를 이용하여 구현하는 방법
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setRegisterDateTime(rs.getTimestamp("regdate").toLocalDateTime());

				return member;
			}	
		});
		return results;
	}
	*/
	
	
	// 2.MemberRowMapper 클래스를 생성하여 구현하는 방법
	@Override
	public Collection<Member> selectAll() {
		this.sql = "select * from member order by id";
		List<Member> results = jdbcTemplate.query(this.sql, new MemberRowMapper());
		
		return results;
	}
}

