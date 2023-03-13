package chap03.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.DuplicateFormatFlagsException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap03.config.AppContext;
import chap03.exception.MemberNotFoundException;
import chap03.exception.WrongIdPasswordException;
import chap03.model.ChangePasswordService;
import chap03.model.MemberInfoPrinter;
import chap03.model.MemberListPrinter;
import chap03.model.MemberRegisterService;
import chap03.model.RegisterRequest;
import chap03.model.VercionPrinter;

public class MainForSpring_ME {
	
	private static AnnotationConfigApplicationContext context ;
	
	static {
		context = new AnnotationConfigApplicationContext(AppContext.class);
	}
	
	private void printHelp() {
		System.out.println("\n잘못된 명령입니다. 아래 사용법을 확인하세요.");
		System.out.println("\n ### 명령어 사용법 ###");
		System.out.println("\n 명령어를 입력하세요 : new 이메일 이름 암호 암호확인 ###");
		System.out.println("\n 명령어를 입력하세요 : change 이메일 현재암호 변경암호 ###");
		System.out.println("\n 명령어를 입력하세요 : list");
		System.out.println("\n 명령어를 입력하세요 : info 이메일");
		System.out.println("\n 명령어를 입력하세요 : version");
		System.out.println("\n 명령어를 입력하세요 : exit \n");
	}
	
	private void processNewCommand(String [] args) {	//사용자가 입력한 이메일 이름 암호 암호확인을 넣어주어야 함
		if(args.length !=5) {	// args 의 크기는 5 이여야 한다 (new, 이메일, 이름, 암호, 암호확인 5개)
			this.printHelp();
			return;
		}
		MemberRegisterService regSvc = context.getBean("memberRegSvc",MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(args[1]);
		req.setName(args[2]);
		req.setPassword(args[3]);
		req.setConfirmPassword(args[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {	//암호와 암호확인 일치 여부 검사
			System.out.println("암호와 암호 확인이 일치하지 않습니다");
			return;
		}
		
		try {
			regSvc.regist(req);		// MemberRegisterService 는 예외를 발생시키기 때문에 try catch 사용
			System.out.println("회원 정보를 등록했습니다\n");
		}
		catch(DuplicateFormatFlagsException e){
			System.out.println("이미 존재하는 이메일입니다.\n");
		}
		
	}
	
	private void processListCommand() {
		MemberListPrinter listPrinter = context.getBean("listPrinter",MemberListPrinter.class);
		listPrinter.printAll();
	}
	
	private void processInfoCommand(String [] args) {	//사용자가 입력한 이메일 이름 암호 암호확인을 넣어주어야 함
		if(args.length != 2) {	// args 의 크기는 5 이여야 한다 (new, 이메일, 이름, 암호, 암호확인 5개)
			this.printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter = context.getBean("infoPrinter",MemberInfoPrinter.class);
		infoPrinter.
	}
	
	private void processVersionCommand() {
		VercionPrinter versionPrinter = context.getBean("versionPrinter",VercionPrinter.class);
		versionPrinter.print();
	}
	
	private void processChangeCommand(String [] args) {
		if(args.length !=4) {	// args 의 크기는 4 이여야 한다 (change, 이메일, 현재암호, 변경암호 4개)
			this.printHelp();
			return;
		}
		
		ChangePasswordService pwdSvc = context.getBean("changePwdSvc",ChangePasswordService.class);
		try{
			pwdSvc.changePassword(args[1], args[2], args[3]);
		}
		catch(MemberNotFoundException e){
			System.out.println("존재하지 않는 이메일입니다. \n");
		}
		catch(WrongIdPasswordException e){
			System.out.println("잘못된 아이디 또는 패스워드 입니다. \n");
		}
		
	}
	
	public static void main(String[] args) throws IOException{	//reader에 IOExecption이 발생할 수 있음
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// scanner보다 효율적인 입력방식
		MainForSpring2 main = new MainForSpring2();
		String command="";
		
		System.out.println("\n ### 회원 관리 프로그램 ###");
		
		while(true) {
			System.out.println("명령어를 입력하세요 : ");
			command = reader.readLine();	// 사용자의 입력 전체를 하나의 문자열로 통째로 받아옴
											// 이거는 IOExecption이 발생할 수 있음
			if(command.equalsIgnoreCase("exit")){	//대.소 문자를 구분하지 않고 "exit" 이면
				System.out.println("\n 프로그램을 종료합니다.");
				break;
			}
			else if(command.startsWith("new ")) {	//command가 "new "으로 시작하면
				main.processNewCommand(command.split(" "));	//split 은 구분자를 정해줌 지금 구분자는 " " (공백)	
				continue;	// main.printHelp();를 만나지 않기 위해
			}
			else if(command.startsWith("change ")) {	//command가 "new "으로 시작하면
				main.processChangeCommand(command.split(" "));	//split 은 구분자를 정해줌 지금 구분자는 " " (공백)
				continue;	// main.printHelp();를 만나지 않기 위해
			}
			else if(command.startsWith("list")) {
				main.processListCommand();
			}
			else if(command.startsWith("info ")) {
				main.processInfoCommand();
			}
			else if(command.startsWith("version")) {
				main.processVersionCommand();
			}
			/*else {
				main.printHelp();	// 이런 방식도 된다
			}*/
			main.printHelp();
		}
	}

}
