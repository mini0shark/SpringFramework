package chap08.printer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import chap08.dao.MemberDao;
import chap08.member.Member;

@Component("listPrinter")
public class MemberListPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m->printer.print(m));
	}
}
