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
	
//-----------------------------------selectByEmail(String email) ���� --------------------------
	/*	// 1.RowMapper �������̽��� �͸� Ŭ������ �����ϴ� ���
	@Override
	public Member selectByEmail(String email) {
		this.sql = "select * from member where email = ?";
		
		List<Member> results = jdbcTemplate.query(this.sql,
				new RowMapper<Member>() {	// �������̽��� �����ϴ� �͸��� Ŭ������ ����
			@Override // �������̽��� �߻� �޼��� �������̵� // �͸��� Ŭ������ �̿��Ͽ� �����ϴ� ���
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
	
	/*	// 2. RowMapper �������̽��� ���� ǥ����(Lambda Expression)���� �����ϴ� ���
		// �͸�Ŭ������ ���� ǥ������ �̿��� �����ϸ� ������ �Ұ��� �ϴ�.
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
	
		// 3. RowMapper �������̽��� ���� �����ϴ� MemberRowMapper Ŭ������ �����Ͽ� �����ϴ� ���
	/*	// List ���·� ��ȯ���� = query
	@Override
	public Member selectByEmail(String email) {
		this.sql = "select * from member where email = ?";
		
		List<Member> results = jdbcTemplate.query(this.sql, new MemberRowMapper(), email);
			
		return results.isEmpty() ? null : results.get(0);
	}
	*/
		// 4. queryForObject() �޼��带 ����Ͽ� �����ϴ� ���
		// ��ü�� �״�� ��ȯ�� = queryForObject = �ݵ�� �ϳ��� ���� ��ȯ���� ������ ���� �߻�
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
//-----------------------------------insert(Member member) ���� --------------------------

	/*
	@Override
		// PreparedStatementCreator �������̽��� �͸� Ŭ������ �����ϸ鼭 KeyHolder , ��� Ŭ������ �����ؼ� ����ص� ����
	public void insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.sql = "insert into member(id, email, password, name) values(member_id_seq.nextval,?,?,?)";
		
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String [] {"id"} ); // �ڵ� �߱޵Ǿ����� ���ڸ� �о���� ���� �迭
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
		// PreparedStatementCreator �������̽��� ������ MemberPreparedStatementCreater Ŭ������ �̿��� �����ϸ鼭 KeyHolder ���
	public void insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new MemberPreparedStatementCreater(member,new String [] {"id"}),keyHolder);
		
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	
	}
	/*
	@Override		// PreparedStatementCreator �������̽��� ������ MemberPreparedStatementCreater Ŭ������ �̿��� �����ϸ鼭 KeyHolder ������� ����
	public void insert(Member member) {
		jdbcTemplate.update(new MemberPreparedStatementCreater(member);
	}
	*/
//-----------------------------------update(Member member) ���� --------------------------
	@Override
	public void update(Member member) {
		sql = "update member set password = ? where email = ?";
		jdbcTemplate.update(sql,member.getPassword(),member.getEmail());
		
	}
//-----------------------------------selectAll() ���� --------------------------
	/*	// 1. �͸�Ŭ������ �����ϴ� ���
	public Collection<Member> selectAll() {
		this.sql = "select * from member order by id";
		
		List<Member> results = jdbcTemplate.query(this.sql,
				new RowMapper<Member>() {	// �������̽��� �����ϴ� �͸��� Ŭ������ ����
			@Override						// �͸��� Ŭ������ �̿��Ͽ� �����ϴ� ���
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
	
	
	// 2.MemberRowMapper Ŭ������ �����Ͽ� �����ϴ� ���
	@Override
	public Collection<Member> selectAll() {
		this.sql = "select * from member order by id";
		List<Member> results = jdbcTemplate.query(this.sql, new MemberRowMapper());
		
		return results;
	}
}

