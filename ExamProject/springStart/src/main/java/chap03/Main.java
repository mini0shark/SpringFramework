package chap03;

import chap03.config.Assembler;
import chap03.services.ChangePasswordService;

public class Main {
	public static void main(String[] args) {
		// ���ο��� ������� ��ü�� �����ϰ� �����ϴ� ���
//		MemberDao memberDao =new MemberDao();
//		MemberRegisterService regSvc = new MemberRegisterService(memberDao);
//		ChangePasswordService pwdSvc = new ChangePasswordService();
//		pwdSvc.setMemberDao(memberDao);
		
		Assembler assembler = new Assembler();
		ChangePasswordService changePwdSvc = 
				assembler.getChangePasswordService();
	}
}
