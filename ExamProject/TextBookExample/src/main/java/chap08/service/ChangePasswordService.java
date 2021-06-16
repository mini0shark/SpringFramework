package chap08.service;

import javax.transaction.Transactional;

import chap08.dao.MemberDao;
import chap08.excptions.MemberNotFoundException;
import chap08.member.Member;

public class ChangePasswordService {
	MemberDao memberDao;
	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao= memberDao;
	}
	@Transactional
	// 트랜잭션을 확인하려면 Log4j2와 Logback을 Dependency에 추가
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if(member == null)
			throw new MemberNotFoundException();
		member.changePassword(oldPwd, newPwd);
		memberDao.update(member);		
	}
}
