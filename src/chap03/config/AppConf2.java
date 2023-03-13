package chap03.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap03.model.*;

@Configuration
public class AppConf2 {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberPrinter memberPrinter;
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(this.memberDao);
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(this.memberDao);
		
		return pwdSvc;
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(this.memberDao, this.memberPrinter);
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		//infoPrinter.setMemberDao(this.memberDao);
		//infoPrinter.setMemberPrinter(this.memberPrinter);
		
		return infoPrinter;
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		
		return versionPrinter;
	}
}
