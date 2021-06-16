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
	// Ʈ������� Ȯ���Ϸ��� Log4j2�� Logback�� Dependency�� �߰�
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if(member == null)
			throw new MemberNotFoundException();
		member.changePassword(oldPwd, newPwd);
		memberDao.update(member);		
	}
}
