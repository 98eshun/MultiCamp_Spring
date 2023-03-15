package chap08.model;

import chap08.exception.MemberNotFoundException;
import chap08.exception.WrongIdPasswordException;

public class ChangePasswordService {

	private MemberDao memberDao;
	
	public ChangePasswordService() {
	}
	
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
