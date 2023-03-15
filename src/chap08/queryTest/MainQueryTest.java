package chap08.queryTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainQueryTest {
	
	static private AnnotationConfigApplicationContext context;
	
	static {
		context = new AnnotationConfigApplicationContext(AppContext.class);
	}
	
	public static void main(String [] args) {
		MemberCountBean memberCount = context.getBean("memberCount", MemberCountBean.class);	// �̸��� "memberCount"�̰� Ÿ���� MemberCountBean �̴� 
		System.out.println("��ü ȸ���� �� : " + memberCount.getMemberCount() + "�� �Դϴ�.");
	}
}
