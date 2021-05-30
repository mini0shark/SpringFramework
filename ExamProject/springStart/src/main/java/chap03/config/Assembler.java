package chap03.config;

import chap03.member.MemberDao;
import chap03.services.ChangePasswordService;
import chap03.services.MemberRegisterService;

// �ڽ��� �����ϰ� ������ ��ü�� �����ϴ� �޼��� ����
//  Configuration�� ���� ����
public class Assembler {
	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	
	public Assembler() {
		// �Ʒ� �ּ��� ���� ���� ������ �� ����
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
