package chap03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import chap03.member.MemberDao;
import chap03.member.MemberInfoPrinter;
import chap03.member.MemberListPrinter;
import chap03.member.MemberPrinter;
import chap03.member.VersionPrinter;
import chap03.services.ChangePasswordService;
import chap03.services.MemberRegisterService;

@Configuration
public class AppCtx1 {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
}
