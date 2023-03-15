package chap04.config;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap04.model.*;

@Configuration
public class AppContext {

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		MemberRegisterService memberRegSvc = new MemberRegisterService();
		return memberRegSvc;
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService changePwdSvc = new ChangePasswordService();
		// pwdSvc.setMemberDao(this.memberDao());  // 직접 의존 주석 처리
		
		return changePwdSvc;
	}
	/*
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	*/
	/*
	@Bean
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
												// 같은 타입을 리턴하는 메소드 2개를 만듬
												// 타입은 같지만 이름이 다르기 때문에 구분할 수 없어 예외 발생
	@Bean
	public MemberPrinter memberPrinter2() {
		return new MemberPrinter();
	}
	*/
	
	@Bean
	//@Qualifier("printerQ")	// 한정자 설정
	@Qualifier("printerP")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("printC")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		MemberListPrinter listPrinter = new MemberListPrinter();
		return listPrinter;
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		//infoPrinter.setMemberDao(memberDao());
		//infoPrinter.setMemberPrinter(memberPrinter());
		
		return infoPrinter;
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		
		return versionPrinter;
	}
	/*
	@Bean
	public DateTimeFormatter dateTimeFormatter() {
		return DateTimeFormatter.ofPattern("yyyy년 mm월 dd일");	// 사용자가 원하는 형식 지정
	}
	*/
	
}















