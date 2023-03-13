package chap03.config;

import org.springframework.context.annotation.Bean;

import chap03.model.MemberDao;
import chap03.model.MemberPrinter;

public class AppConf1 {

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
}
