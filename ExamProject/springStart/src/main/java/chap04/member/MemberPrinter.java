package chap04.member;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sun.istack.Nullable;

public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� MM�� dd��"); 
	}
	public void print(Member member) {
		if(dateTimeFormatter == null) {
			System.out.printf(
					"ȸ������: ���̵� =%d, �̸���=%s, �̸�=%s, �����=%tF\n",
					member.getId(), member.getEmail(),
					member.getName(), member.getRegisterDateTime());
		}else {
			System.out.printf(
					"ȸ������: ���̵� =%d, �̸���=%s, �̸�=%s, �����=%s\n",
					member.getId(), member.getEmail(), 
					member.getName(), dateTimeFormatter.format(member.getRegisterDateTime()));
		}
	}
	// ���� ���� �� ������ �ȳ��� null�� ����ϴ� ��� 1
	@Autowired(required=false)
	public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
		System.out.println("printer setter ȣ��");
		this.dateTimeFormatter = dateTimeFormatter;
	}

//	// ���� ���� �� ������ �ȳ��� null�� ����ϴ� ��� 2
//	@Autowired
//	public void setDateTimeFormatter(Optional<DateTimeFormatter> formatterOpt) {
//		if(formatterOpt.isPresent()) {
//			this.dateTimeFormatter = dateTimeFormatter;	
//		}else {
//			this.dateTimeFormatter=null;
//		}
//	}

	// ���� ���� �� ������ �ȳ��� null�� ����ϴ� ��� 3
	// ���2 (reqired=false)���� �������� 
	// 3���� Bean�� ��� setter�� ȣ�������� 
	// 2���� setter ��ü�� ȣ������ �ʴ´�.
	// �ȵȴ�.... �־ȵ���.. 
//	@Autowired
//	public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
//		this.dateTimeFormatter = dateTimeFormatter;
//	}
}
 