package chap04.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap04.member.MemberDao;
import chap04.member.MemberInfoPrinter;
import chap04.member.MemberListPrinter;
import chap04.member.MemberPrinter;
import chap04.member.MemberSummaryPrinter;
import chap04.member.VersionPrinter;
import chap04.services.ChangePasswordService;
import chap04.services.MemberRegisterService;


@Configuration
public class AppCtx {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService();
	}
	@Bean
	public ChangePasswordService changePwdSvc() {
		return new ChangePasswordService();
	}
//	@Bean
//	public MemberPrinter memberPrinter() {
//		return new MemberPrinter();
//	}

	// MemberPrint를 MemberSummaryPrinter가 상속했기 때문이다.
	// MemberSummaryPrinter클래스를  MemberPrinter 타입에도 할당할 수 있으므로
	// MemberPrinter타입 빈을 자동 주입해야하는 @Autowired 애노태이션 태그를 만나면 
	// memberPrint1 빈과 memberPrint2 빈 중 어느것을 주입해야하는 지 모른다.
	// => Quailifier로 한정해야함.
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
	public MemberListPrinter memberListPrinter() {
		return new MemberListPrinter();
	}
	@Bean
	public MemberInfoPrinter memberInfoPrinter() {
		MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
		// memberInfoPrinter.setMemberPrinter(memberPrinter2());
		// 이렇게 직접 주입을 했더라도 MemberInfoPrinter에 MemberInfoPrinter가 @Autowired @Qualifier("printer")가 되어 있다면
		// memberPrinter1이 주입된다.
		// => 자동주입을 적용하기 어려운 코드를 제외한 나머지는 자동주입을 사용하는게 좋다. 
		return memberInfoPrinter;
	}
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
