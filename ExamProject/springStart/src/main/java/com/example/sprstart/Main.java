package com.example.sprstart;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=
				new AnnotationConfigApplicationContext(AppContext.class);
//		Greeter g = ctx.getBean("greeter", Greeter.class);	// 메서드 이름, 리턴타입
//		String msg = g.greet("스프링");
//		System.out.println(msg);

		Greeter g1 = ctx.getBean("greeter", Greeter.class);	// 메서드 이름, 리턴타입
		Greeter g2 = ctx.getBean("greeter", Greeter.class);	// 메서드 이름, 리턴타입
		g1.greet("abc");
		System.out.println("(g1==g2)="+(g1==g2));
		ctx.close();		
	}
}
