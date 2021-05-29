package com.example.sprstart;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=
				new AnnotationConfigApplicationContext(AppContext.class);
//		Greeter g = ctx.getBean("greeter", Greeter.class);	// �޼��� �̸�, ����Ÿ��
//		String msg = g.greet("������");
//		System.out.println(msg);

		Greeter g1 = ctx.getBean("greeter", Greeter.class);	// �޼��� �̸�, ����Ÿ��
		Greeter g2 = ctx.getBean("greeter", Greeter.class);	// �޼��� �̸�, ����Ÿ��
		g1.greet("abc");
		System.out.println("(g1==g2)="+(g1==g2));
		ctx.close();		
	}
}
