package chap08.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
public class MemberDaoJdbc extends MemberDao{
	
	@Autowired
	private DataSource ds;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private String sql="";
	
	
	public MemberDaoJdbc() {
	}
	@Override
	public Member selectByEmail(String email) {
		Member member = null;
		this.sql = "select * from member where email = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,email);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new Member();
				member.setId(rs.getLong("id"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setRegisterDateTime(rs.getTimestamp("regdate").toLocalDateTime());
			
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(!conn.isClosed()) {
					conn.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return member;
	}
	
	public void insert(Member member) {

		sql = "insert into member(id, email, password, name)"
				+ " values(member_id_seq.nextval,?,?,?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

	}
	
	public void update(Member member) {
		sql = "update member set password = ? where email = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getEmail());
			pstmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

	}
	
	public Collection<Member> selectAll() {
		ArrayList<Member> list = new ArrayList<Member>();
		Member member = null;
		this.sql = "select * from member order by id";
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				member = new Member();
				member.setId(rs.getLong("id"));
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setRegisterDateTime(rs.getTimestamp("regdate").toLocalDateTime());
			
				list.add(member);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(!conn.isClosed()) {
					conn.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
}
