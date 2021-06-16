package chap08.ctx;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import chap08.dao.MemberDao;
import chap08.printer.MemberInfoPrinter;
import chap08.printer.MemberListPrinter;
import chap08.printer.MemberPrinter;
import chap08.service.ChangePasswordService;
import chap08.service.MemberRegisterService;

@Configuration
@EnableTransactionManagement
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
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	@Bean
	public MemberRegisterService memberRegisterService() {
		MemberRegisterService memberRegisterService = new MemberRegisterService(memberDao());
		return memberRegisterService;
	}
	@Bean
	public ChangePasswordService changePasswordService() {
		ChangePasswordService changePasswordService = new ChangePasswordService(memberDao());
		return changePasswordService;
	}
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao(), memberPrinter());
	}
	
	@Bean
	public MemberInfoPrinter memberInfoPrinter() {
		MemberInfoPrinter memberInfoPrinter=new MemberInfoPrinter(memberDao(), memberPrinter());
		return memberInfoPrinter;
	}
}
