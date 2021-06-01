package chap04.services;

import org.springframework.beans.factory.annotation.Autowired;

import chap04.exceptions.MemberNotFoundException;
import chap04.member.Member;
import chap04.member.MemberDao;


public class ChangePasswordService {
	@Autowired
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
