package chap03.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap03.config.AppConf1;
import chap03.config.AppConf2;

public class MainForGetBean {

private static AnnotationConfigApplicationContext context;
	
	static {
		context = new AnnotationConfigApplicationContext(AppConf1.class, AppConf2.class);
	}
	
	public static void main(String [] args) {
		// context.getBean("beanName", beanClassType);
		context.getBean("beanName", AppConf1.class);	// "beanName�� ���� ����"
		
		if(AppConf1 != null) {
			System.out.println("conf1 ���� �����մϴ�.");
		}
		else {
			System.out.println("conf1 ���� �������� �ʽ����.");
		}
		
	}
}
