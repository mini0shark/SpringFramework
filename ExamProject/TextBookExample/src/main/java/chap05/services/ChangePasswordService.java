package chap05.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chap05.exceptions.MemberNotFoundException;
import chap05.member.Member;
import chap05.member.MemberDao;


@Component
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
