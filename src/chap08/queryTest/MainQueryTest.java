package chap08.queryTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainQueryTest {
	
	static private AnnotationConfigApplicationContext context;
	
	static {
		context = new AnnotationConfigApplicationContext(AppContext.class);
	}
	
	public static void main(String [] args) {
		MemberCountBean memberCount = context.getBean("memberCount", MemberCountBean.class);	// 이름이 "memberCount"이고 타입이 MemberCountBean 이다 
		System.out.println("전체 회원의 수 : " + memberCount.getMemberCount() + "명 입니다.");
	}
}
