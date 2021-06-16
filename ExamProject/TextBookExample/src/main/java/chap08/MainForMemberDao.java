package chap08;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap08.ctx.AppCtx;
import chap08.dao.MemberDao;
import chap08.excptions.MemberNotFoundException;
import chap08.excptions.WrongIdPasswordException;
import chap08.member.Member;
import chap08.service.ChangePasswordService;

public class MainForMemberDao {
	private static MemberDao memberDao;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		memberDao = ctx.getBean(MemberDao.class);
		
//		selectAll();
//		updateMember();
//		insertMember();
		System.out.println("--------------------------start--------------------------");
		
		ChangePasswordService cps=ctx.getBean("changePasswordService", ChangePasswordService.class);
		
		try {
			cps.changePassword("mini0shark@naver.com", "1234", "1111");
			System.out.println("��ȣ�� �����߽��ϴ�.");
		}catch(MemberNotFoundException me) {
			System.out.println("ȸ�� �����Ͱ� �������� �ʽ��ϴ�.");
		}catch(WrongIdPasswordException we) {
			System.out.println("��ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		
		ctx.close();
	}

	static void selectAll() {
		System.out.println("----- selectAll -----");
		int total = memberDao.count();
		System.out.println("��ü ������: "+total);
		List<Member> members = memberDao.selectAll();
		for(Member m: members) {
			System.out.println(m.getId() + ":"+m.getEmail()+":"+m.getName());
		}
	}
	static void updateMember() {
		System.out.println("----- updateMember -----");
		Member member = memberDao.selectByEmail("mini0shark@naver.com");
		String oldPw = member.getPassword();
		String newPw = Double.toHexString(Math.random());
		member.changePassword(oldPw, newPw);
		
		memberDao.update(member);
		System.out.println("��ȣ ���� : "+oldPw+" > "+newPw);
	}
	static void insertMember() {
		System.out.println("----- inserteMember -----");
		
		String prefix = formatter.format(LocalDateTime.now());
		Member member = new Member(prefix + "@test.com", prefix, prefix, LocalDateTime.now());
		memberDao.insert(member);
		System.out.println(member.getId()+" ������ �߰�");
	}
}
