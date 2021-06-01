package chap04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap04.config.AppCtx;
import chap04.exceptions.DuplicateMemberException;
import chap04.exceptions.MemberNotFoundException;
import chap04.exceptions.WrongIdPasswordException;
import chap04.member.MemberInfoPrinter;
import chap04.member.MemberListPrinter;
import chap04.member.VersionPrinter;
import chap04.services.ChangePasswordService;
import chap04.services.MemberRegisterService;
import chap04.services.RegisterRequest;

public class MainForSpring {
	private static ApplicationContext ctx = null;
	public static void main(String[] args) throws IOException {
		ctx=new AnnotationConfigApplicationContext(AppCtx.class);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.println("��ɾ �Է��ϼ���:");
			String command = br.readLine();
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("�����մϴ�.");
				break;
			}
			if(command.startsWith("new")) {
				processNewCommand(command.split(" "));
				continue;
			}else if(command.startsWith("change")) {
				processChangeCommand(command.split(" "));
				continue;
			}else if(command.startsWith("list")) {
				processListCommand();
				continue;
			}else if(command.startsWith("info")) {
				processInfoCommand(command.split(" "));
				continue;
			}else if(command.startsWith("version")) {
				processVersionCommand();
				continue;
			}
			printHelp();
		}
	}
	
	private static void processNewCommand(String[] arg) {
		if(arg.length!=5) {
			printHelp();
			return ;
		}
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("��ȣ�� Ȯ���� ��ġ���� �ʽ��ϴ�.");
			return ;
		}
		try {
			regSvc.regist(req);
			System.out.println("����߽��ϴ�.\n");
		}catch(DuplicateMemberException e) {
			System.out.println("�̹� �����ϴ� �̸��� �Դϴ�.\n");
		}
	}
	
	private static void processChangeCommand(String[] arg) {
		if(arg.length!=4) {
			printHelp();
			return ;
		}
		ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);
		
		try {
			changePwdSvc.changePassword(arg[1],arg[2],arg[3]);
			System.out.println("��ȣ�� �����߽��ϴ�.\n");
		}catch(MemberNotFoundException e) {
			System.out.println("�������� �ʴ� �̸��� �Դϴ�.\n");
		}catch(WrongIdPasswordException e) {
			System.out.println("�̸��ϰ� ��ȣ�� ��ġ���� �ʽ��ϴ�.\n");
		}
	}

	private static void processListCommand() {
		MemberListPrinter memberListPrinter=ctx.getBean("memberListPrinter", MemberListPrinter.class);
		memberListPrinter.printAll();
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("�߸��� ����Դϴ�. �Ʒ� ��ɾ� ������ Ȯ���ϼ���.");
		System.out.println("--- ��ɾ� ���� ---");
		System.out.println("new �̸��� �̸� ��ȣ ��ȣȮ��");
		System.out.println("change �̸��� ������ ������");
		System.out.println();
	}

	private static void processInfoCommand(String[] arg) {
		MemberInfoPrinter memberInfoPrinter =ctx.getBean("memberInfoPrinter", MemberInfoPrinter.class);
		memberInfoPrinter.printMemberInfo(arg[1]);
	}
	
	private static void processVersionCommand() {
		VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.print();
	}

}
