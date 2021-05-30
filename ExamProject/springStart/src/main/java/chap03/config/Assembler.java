package chap03.config;

import chap03.member.MemberDao;
import chap03.services.ChangePasswordService;
import chap03.services.MemberRegisterService;

// 자신이 생성하고 조립한 객체를 리턴하는 메서드 제공
//  Configuration과 같은 역할
public class Assembler {
	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	
	public Assembler() {
		// 아래 주석과 같이 쉽게 변경할 수 있음
		// memberDao = new CachedMemberDao();
		memberDao = new MemberDao();
		regSvc = new MemberRegisterService(memberDao);
		pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
	}
	
	public MemberDao getMemberDao() {
		return memberDao;
	}
	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}
	public ChangePasswordService getChangePasswordService() {
		return pwdSvc;
	}
}
