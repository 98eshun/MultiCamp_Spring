package chap03.model;

import java.util.*;


public class MemberDao {
	
	private static long nextId = 0;	//id �� �ڵ����� ����
	//private Map<Key, Value>	//Map�� key�� value�� �Բ� ������ �ϳ��� ������ ������
	private Map<String, Member> members;
	
	public MemberDao() {
		this.members = new HashMap<>();
	}
	
	public Member selectByEmail(String email) {
		return members.get(email);
	}
	
	public void insert(Member member) {
		member.setId(++MemberDao.nextId);	// 0���� ������ nextId�� ++ (id����)
		//	map.put(key,value); map�� ���� �־��ִ� �ڵ�
		members.put(member.getEmail(),member);	//���� �ڵ�� ���� ����
	}
	
	public void update(Member member) {	// �Ű�����=�� ���� �ִ� id�� �ִ� �ɹ���ü
		//	map.put(key,value); map�� ���� �־��ִ� �ڵ�
		members.put(member.getEmail(),member);	//���� �ڵ�� ���� ����
	}
	
	public Collection<Member> selectAll() {
		return members.values();	//Member Ŭ���� ������ �÷����� ��ȯ (members�� �ִ� ���� ��ȯ)
	}
	
	
}
