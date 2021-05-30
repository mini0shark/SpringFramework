package chap03;

import chap03.config.Assembler;
import chap03.services.ChangePasswordService;

public class Main {
	public static void main(String[] args) {
		// 메인에서 의존대상 객체를 생성하고 주입하는 방법
//		MemberDao memberDao =new MemberDao();
//		MemberRegisterService regSvc = new MemberRegisterService(memberDao);
//		ChangePasswordService pwdSvc = new ChangePasswordService();
//		pwdSvc.setMemberDao(memberDao);
		
		Assembler assembler = new Assembler();
		ChangePasswordService changePwdSvc = 
				assembler.getChangePasswordService();
	}
}
