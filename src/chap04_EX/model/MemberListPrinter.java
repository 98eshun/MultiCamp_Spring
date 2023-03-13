package chap04.model;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberListPrinter {

	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public MemberListPrinter() {
	}
	
	/*
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	*/
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Autowired
	// => 빈 이름으로 식별
	// public void setPrinter(MemberPrinter memberPrinter2) {
	
	// 한정자를 메서드의 매개변수 이름으로 사용할 수 없다!
	// public void setPrinter(MemberPrinter printerQ) {
	
	/* => 한정자 사용
	@Qualifier("printerQ")
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	*/
	
	// => 한정자를 이용한 자식 클래스(MemberSummaryPrinter) 빈 적용
	@Qualifier("printerC")
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}

	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m -> printer.print(m));
	}
}
