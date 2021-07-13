package com.spring5.service;

import java.time.LocalDateTime;

import com.spring5.domain.RegisterRequest;
import com.spring5.excptions.DuplicateMemberException;
import com.spring5.domain.Member;
import com.spring5.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class MemberRegisterService {
	private MemberRepository memberRepository;

	@Autowired
	public MemberRegisterService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	
	public Long regist(RegisterRequest req) {
		Member member = memberRepository.selectByEmail(req.getEmail());
		if(member != null) {
			throw new DuplicateMemberException("dup email "+req.getEmail());
		}

		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(),
				LocalDateTime.now());
		memberRepository.insert(newMember);
		return newMember.getId();
	}
}
