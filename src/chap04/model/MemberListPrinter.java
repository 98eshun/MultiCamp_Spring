package chap04.model;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberListPrinter {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	@Qualifier("printC")
	private MemberPrinter printer;

	
	public MemberListPrinter() {
	}
	
	/*
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	*/
	@Qualifier("printerC")
	public void setPrinter(MemberPrinter printer){
		this.printer = printer;
	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m -> printer.print(m));
	}
}
