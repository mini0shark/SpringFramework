package chap03.member;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) {
		System.out.println(email);
		Member member = memberDao.selectByEmail(email);
		if(member==null) {
			System.out.println("������ ����\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}


	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	public void setMemberPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
}
