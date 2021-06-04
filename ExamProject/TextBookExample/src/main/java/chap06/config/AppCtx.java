package chap06.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import chap06.Client;
import chap06.Client2;

@Configuration
@ComponentScan(basePackages = {"chap06"})
public class AppCtx {
	@Bean
	@Scope("prototype")
	public Client getClient() {
		Client client = new Client();
		client.setHost("client");
		// client.afterPropertiesSet(); -> �̰� �߰��ϸ� �ι� �����ϴ� ���̴�.
		return client;
	}
	@Bean(initMethod = "connect", destroyMethod="close")
	public Client2 getClient2() {
		Client2 client2 = new Client2();
		client2.setHost("client2");
		return client2;
	}
}
