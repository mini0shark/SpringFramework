package chap08.printer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import chap08.dao.MemberDao;
import chap08.member.Member;

@Component("infoPrinter")
public class MemberInfoPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public MemberInfoPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao=memberDao;
		this.printer=printer;
	}
	
	public void printMemberInfo(String email) {
		System.out.println(email);
		Member member = memberDao.selectByEmail(email);
		if(member==null) {
			System.out.println("데이터 없음\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}


}
