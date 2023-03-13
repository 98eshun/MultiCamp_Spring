package chap04.model;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import chap04.exception.DuplicateMemberException;

public class MemberRegisterService {

	@Autowired
	private MemberDao memberDao;
	
	public MemberRegisterService() {
	}
	
	/*
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	*/
	
	public long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		
		if(member != null) {
			throw new DuplicateMemberException();
		}
		
		member = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		memberDao.insert(member);
		
		return member.getId();
	}
}
