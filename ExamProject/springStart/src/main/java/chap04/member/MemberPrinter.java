package chap04.member;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sun.istack.Nullable;

public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"); 
	}
	public void print(Member member) {
		if(dateTimeFormatter == null) {
			System.out.printf(
					"회원정보: 아이디 =%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
					member.getId(), member.getEmail(),
					member.getName(), member.getRegisterDateTime());
		}else {
			System.out.printf(
					"회원정보: 아이디 =%d, 이메일=%s, 이름=%s, 등록일=%s\n",
					member.getId(), member.getEmail(), 
					member.getName(), dateTimeFormatter.format(member.getRegisterDateTime()));
		}
	}
	// 빈이 없을 때 에러가 안나게 null을 허용하는 방법 1
	@Autowired(required=false)
	public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
		System.out.println("printer setter 호출");
		this.dateTimeFormatter = dateTimeFormatter;
	}

//	// 빈이 없을 때 에러가 안나게 null을 허용하는 방법 2
//	@Autowired
//	public void setDateTimeFormatter(Optional<DateTimeFormatter> formatterOpt) {
//		if(formatterOpt.isPresent()) {
//			this.dateTimeFormatter = dateTimeFormatter;	
//		}else {
//			this.dateTimeFormatter=null;
//		}
//	}

	// 빈이 없을 때 에러가 안나게 null을 허용하는 방법 3
	// 방법2 (reqired=false)와의 차이점은 
	// 3번은 Bean이 없어도 setter를 호출하지만 
	// 2번은 setter 자체를 호출하지 않는다.
	// 안된다.... 왜안되지.. 
//	@Autowired
//	public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
//		this.dateTimeFormatter = dateTimeFormatter;
//	}
}
 