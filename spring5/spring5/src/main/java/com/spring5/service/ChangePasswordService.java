package com.spring5.service;

import com.spring5.excptions.MemberNotFoundException;
import com.spring5.member.Member;
import com.spring5.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {
	MemberRepository memberRepository;
	public ChangePasswordService(MemberRepository memberRepository) {
		this.memberRepository= memberRepository;
	}
	@Transactional
	// Ʈ������� Ȯ���Ϸ��� Log4j2�� Logback�� Dependency�� �߰�
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberRepository.selectByEmail(email);
		if(member == null)
			throw new MemberNotFoundException();
		member.changePassword(oldPwd, newPwd);
		memberRepository.update(member);
	}
}
