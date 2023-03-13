package chap04.model;

import org.springframework.beans.factory.annotation.Autowired;

import chap04.exception.MemberNotFoundException;
import chap04.exception.WrongIdPasswordException;

public class ChangePasswordService {

	@Autowired
	private MemberDao memberDao;	//AppContext에 있는 Bean과 이름이 같은 것을 주입하는 것
									// @Autowired는 타입을 먼저 검색하고 다음으로 이름으로 검색한다.
	public ChangePasswordService() {
	}
	
	//@Autowired	// 메서드에도 가능
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	
	public void changePassword(String email, String oldPassword, String newPassword) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		if(oldPassword.equals(newPassword)) {
			throw new WrongIdPasswordException();
		}
		
		member.changePassword(oldPassword, newPassword);
		memberDao.update(member);
	}
}
