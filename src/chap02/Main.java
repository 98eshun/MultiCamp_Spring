package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppContext.class);	//Annotation�� ������� �� �����̳�
		
		Greeter g1 = context.getBean("greeter",Greeter.class);
		Greeter g2 = context.getBean("greeter",Greeter.class);
		
		System.out.println("g1 == g2 : " + (g1==g2));
		// g1�� g2�� ���� ��ü�� ����Ű�� �ִ� = �̱��� ��ü�̴�.
		
		/*Greeter g = context.getBean("greeter", Greeter.class);
		
		String msg = g.greet("������");
		System.out.println(msg);*/
		
		context.close();
	}
}
