package chap05.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import chap05.member.MemberPrinter;
import chap05.member.MemberSummaryPrinter;
import chap05.member.VersionPrinter;


@Configuration
@ComponentScan(basePackages = {"chap05"},
excludeFilters = @Filter(type=FilterType.ASPECTJ, pattern = "chap05.*Dao"))
public class AppCtx {
	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}

// @Component�� ����� ���
// Ctx�� �������� ����� @Bean�� �̸��� �ٸ���( ex �������� ����Ҷ� memberDao2()�� ����Ұ�� )
// �� �� ��� �����Ͽ� @Qualifire�� ������ ����ؾ��Ѵ�.

// @ComponentScan(basePackages = {"chap05", "chap04"})�� ���� �� ��Ű���� ������� ��ĵ���� ��
// ���� �̸��� Bean�� ������ �浹�� ���� ������ ��������� �̸��� �����Ͽ� �浹�� ���ؾ� �Ѵ�.


// ��ĵ ���
//@Component
//@Controller	-> �� ����
//@Service		-> 
//@Repository	-> DB ����
//@Aspect
//@Configuration