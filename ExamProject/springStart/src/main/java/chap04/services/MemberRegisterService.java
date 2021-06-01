package chap04.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import chap04.exceptions.DuplicateMemberException;
import chap04.member.Member;
import chap04.member.MemberDao;

public class MemberRegisterService {
	@Autowired
	private MemberDao memberDao;

	
	
	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member != null) {
			throw new DuplicateMemberException("dup email "+req.getEmail());
		}
		
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(),
				LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
