package chap08.ctx;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap08.dao.MemberDao;

@Configuration
public class AppCtx {
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("org.mariadb.jdbc.Driver");
		ds.setUrl("jdbc:mariadb://localhost:3306/spring5fs?characterEncoding=utf8");
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2);	// 커넥션 풀 초기화 시 초기 커넥션 개수를 지정한다.(기본 10개)
		ds.setMaxActive(10);	// 커넥션 풀에서 가져올 수 있는 최대 커넥션 수를 지정(기본 100);
		
		ds.setTestWhileIdle(true);	// 유휴 커넥션 검사
		ds.setMinEvictableIdleTimeMillis(1000*60*3);	// 1000ms(1초) *60(1분) * 3 = 3분 으로 최소 유휴시간을 정함
		ds.setTimeBetweenEvictionRunsMillis(1000*10);	// 10초 주기
		return ds;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
}
