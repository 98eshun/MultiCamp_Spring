package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppContext.class);	//Annotation을 기반으로 한 컨테이너
		
		Greeter g1 = context.getBean("greeter",Greeter.class);
		Greeter g2 = context.getBean("greeter",Greeter.class);
		
		System.out.println("g1 == g2 : " + (g1==g2));
		// g1과 g2가 같은 객체를 가르키고 있다 = 싱글톤 객체이다.
		
		/*Greeter g = context.getBean("greeter", Greeter.class);
		
		String msg = g.greet("스프링");
		System.out.println(msg);*/
		
		context.close();
	}
}
