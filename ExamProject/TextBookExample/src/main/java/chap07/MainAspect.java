package chap07;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.calc.Calculator;
import chap07.calc.RecCalculator;
import chap07.config.AppCtxWithCache;

public class MainAspect {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtxWithCache.class);
		Calculator cal = ctx.getBean("calculator", Calculator.class);

		System.out.println(cal.factorial(7));
		System.out.println(cal.factorial(7));
		System.out.println(cal.factorial(4));
		System.out.println(cal.factorial(4));
		System.out.println(cal.factorial(5));
		System.out.println(cal.factorial(5));
		ctx.close();
	}

}