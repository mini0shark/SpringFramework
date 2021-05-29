package com.example.sprstart;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration	// ������ ���� Ŭ������ ����
public class AppContext {
	
	@Bean
	public Greeter greeter() {
		Greeter g = new Greeter();
		g.setFormat("%s, �ȳ��ϼ���!");
		return g;
	}

}