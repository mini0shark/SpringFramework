package chap05.member;

public class MemberSummaryPrinter extends MemberPrinter{

	public MemberSummaryPrinter() {
		System.out.println("멤버 프린트 서머리");
	}
	@Override
	public void print(Member member) {
		System.out.printf(
				"회원정보: 아이디 =%d, 이름=%s \n",
				member.getId(),member.getName());
	}
	
}
