package chap06;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import chap06.config.AppCtx;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
//		Client2 client = ctx.getBean("getClient2", Client2.class);
//		client.send();
//		ctx.close();
//		=================== ������ =============================
//		Client.afterPropertiesSet() ����
//		Client.afterPropertiesSet() ����
//		21:49:38.993 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'getClient2'
//		Client2.afterPropertiesSet() ����
//		Client2.send() to client2
//		21:49:39.035 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@2ea227af, started on Fri Jun 04 21:49:38 KST 2021
//		Client2.destroy() ����
//		Client.destroy() ����
		Client client1 = ctx.getBean("getClient", Client.class);
		Client client2 = ctx.getBean("getClient", Client.class);

//		@Bean
//		@Scope("singletone") ���� �� ������ -> ��������� �����ϰ� ���� ��
		System.out.println(client1==client2); 	// true


//		@Bean
//		@Scope("prototype") ���� �� ������	//=> 2���� client����(afterPropertiesSet() 2������)
//		���� �Ҹ�ó�� �ʿ�
		System.out.println(client1==client2); 	// false
		ctx.close();
	}
}
