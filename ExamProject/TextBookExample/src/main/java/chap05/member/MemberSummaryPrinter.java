package chap05.member;

public class MemberSummaryPrinter extends MemberPrinter{

	public MemberSummaryPrinter() {
		System.out.println("��� ����Ʈ ���Ӹ�");
	}
	@Override
	public void print(Member member) {
		System.out.printf(
				"ȸ������: ���̵� =%d, �̸�=%s \n",
				member.getId(),member.getName());
	}
	
}
