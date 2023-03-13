package chap04.model;

import org.springframework.beans.factory.annotation.Autowired;
import chap04.exception.*;

public class ChangePasswordService {

	@Autowired
	private MemberDao memberDao;
	
	public ChangePasswordService() {
	}
	
	// @Autowired
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
