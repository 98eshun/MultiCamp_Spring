package chap08.model;

public class MemberPrinter {

	public void print(Member member) {
		System.out.printf(" 회원 정보: 아이디=%d\t, 이메일=%s\t, 이름=%s\t, 등록일=%tF\n",
				member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
	}
}
