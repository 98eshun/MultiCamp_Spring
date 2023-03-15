package chap04.model;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class MemberPrinter {

	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 mm월 dd일");
	}
	
	public void print(Member member) {
		if(dateTimeFormatter == null) {
			System.out.printf(" 회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
					member.getId(), member.getEmail(), member.getName(), 
					member.getRegisterDateTime());
		}
		else {
		System.out.printf(" 회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
				member.getId(), member.getEmail(), member.getName(), 
				dateTimeFormatter.format(member.getRegisterDateTime()));
		}
	}
	

	@Autowired(required = false)	// 주입할 빈이 있으면 주입하고 없으면 주입하지 않는다.
	public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}

	/*
	@Autowired
	public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
	*/
}
