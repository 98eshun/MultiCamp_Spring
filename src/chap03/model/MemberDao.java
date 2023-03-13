package chap03.model;

import java.util.*;


public class MemberDao {
	
	private static long nextId = 0;	//id 값 자동으로 관리
	//private Map<Key, Value>	//Map은 key와 value를 함께 저장함 하나의 쌍으로 저장함
	private Map<String, Member> members;
	
	public MemberDao() {
		this.members = new HashMap<>();
	}
	
	public Member selectByEmail(String email) {
		return members.get(email);
	}
	
	public void insert(Member member) {
		member.setId(++MemberDao.nextId);	// 0부터 시작한 nextId에 ++ (id설정)
		//	map.put(key,value); map에 값을 넣어주는 코드
		members.put(member.getEmail(),member);	//위의 코드와 같은 내용
	}
	
	public void update(Member member) {	// 매개변수=가 원래 있던 id가 있는 맴버객체
		//	map.put(key,value); map에 값을 넣어주는 코드
		members.put(member.getEmail(),member);	//위의 코드와 같은 내용
	}
	
	public Collection<Member> selectAll() {
		return members.values();	//Member 클래스 전용의 컬렉션을 반환 (members에 있는 값만 반환)
	}
	
	
}
