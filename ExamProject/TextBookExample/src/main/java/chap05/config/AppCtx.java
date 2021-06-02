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

// @Component로 등록한 빈과
// Ctx에 수동으로 등록한 @Bean의 이름이 다르면( ex 수동으로 등록할때 memberDao2()로 등록할경우 )
// 두 빈 모두 존재하여 @Qualifire로 적절히 사용해야한다.

// @ComponentScan(basePackages = {"chap05", "chap04"})와 같이 두 패키지를 대상으로 스캔했을 때
// 같은 이름의 Bean이 있으면 충돌이 나기 때문에 명시적으로 이름을 변경하여 충돌을 피해야 한다.


// 스캔 대상
//@Component
//@Controller	-> 웹 관련
//@Service		-> 
//@Repository	-> DB 관련
//@Aspect
//@Configuration