package chap03.model;

import org.springframework.beans.factory.annotation.Autowired;

import chap03.exception.MemberNotFoundException;

public class MemberInfoPrinter {
	
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberPrinter memberPrinter;
	
	public MemberInfoPrinter() {
	}
	
	/*
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void setMemberPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	*/
	
	public void printMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		memberPrinter.print(member);
		System.out.println();
	}
}
