package chap06;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


public class Client2{
	private String host;

	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}

	public void connect() {
		System.out.println("Client2.afterPropertiesSet() 실행");
		
	}
	public void send() {
		System.out.println("Client2.send() to "+ host);
	}
	
	public void close(){
		System.out.println("Client2.destroy() 실행");
	}
	

}
