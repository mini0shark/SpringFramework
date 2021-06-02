package chap03.services;

import org.springframework.beans.factory.annotation.Autowired;

import chap03.exceptions.MemberNotFoundException;
import chap03.member.Member;
import chap03.member.MemberDao;

public class ChangePasswordService {
	private MemberDao memberDao;
	
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if(member == null)
			throw new MemberNotFoundException();
		
		member.changePassword(oldPwd, newPwd);
		memberDao.update(member);
	}
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao=memberDao;
	}
}
