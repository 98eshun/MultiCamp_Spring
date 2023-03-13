package chap04.model;

import java.util.*;

public class MemberDao {

	private static long nextId = 0;
	private Map<String, Member> members;
	
	public MemberDao() {
		this.members = new HashMap<>();
	}
	
	public Member selectByEmail(String email) {
		return members.get(email);
	}
	
	public void insert(Member member) {
		member.setId(++MemberDao.nextId);
		members.put(member.getEmail(), member);
	}
	
	public void update(Member member) {
		members.put(member.getEmail(), member);
	}
	
	public Collection<Member> selectAll() {
		return members.values();
	}
}





