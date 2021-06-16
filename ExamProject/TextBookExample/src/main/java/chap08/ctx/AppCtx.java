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
		ds.setInitialSize(2);	// Ŀ�ؼ� Ǯ �ʱ�ȭ �� �ʱ� Ŀ�ؼ� ������ �����Ѵ�.(�⺻ 10��)
		ds.setMaxActive(10);	// Ŀ�ؼ� Ǯ���� ������ �� �ִ� �ִ� Ŀ�ؼ� ���� ����(�⺻ 100);
		
		ds.setTestWhileIdle(true);	// ���� Ŀ�ؼ� �˻�
		ds.setMinEvictableIdleTimeMillis(1000*60*3);	// 1000ms(1��) *60(1��) * 3 = 3�� ���� �ּ� ���޽ð��� ����
		ds.setTimeBetweenEvictionRunsMillis(1000*10);	// 10�� �ֱ�
		return ds;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
}
