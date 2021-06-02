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

	// MemberPrint�� MemberSummaryPrinter�� ����߱� �����̴�.
	// MemberSummaryPrinterŬ������  MemberPrinter Ÿ�Կ��� �Ҵ��� �� �����Ƿ�
	// MemberPrinterŸ�� ���� �ڵ� �����ؾ��ϴ� @Autowired �ֳ����̼� �±׸� ������ 
	// memberPrint1 ��� memberPrint2 �� �� ������� �����ؾ��ϴ� �� �𸥴�.
	// => Quailifier�� �����ؾ���.
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
		// �̷��� ���� ������ �ߴ��� MemberInfoPrinter�� MemberInfoPrinter�� @Autowired @Qualifier("printer")�� �Ǿ� �ִٸ�
		// memberPrinter1�� ���Եȴ�.
		// => �ڵ������� �����ϱ� ����� �ڵ带 ������ �������� �ڵ������� ����ϴ°� ����. 
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
