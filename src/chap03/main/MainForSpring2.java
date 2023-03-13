package chap03.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap03.config.AppConf1;
import chap03.config.AppConf2;
import chap03.exception.DuplicateMemberException;
import chap03.exception.MemberNotFoundException;
import chap03.exception.WrongIdPasswordException;
import chap03.model.ChangePasswordService;
import chap03.model.MemberInfoPrinter;
import chap03.model.MemberListPrinter;
import chap03.model.MemberRegisterService;
import chap03.model.RegisterRequest;
import chap03.model.VersionPrinter;

public class MainForSpring2 {

	private static AnnotationConfigApplicationContext context;
	
	static {
		context = new AnnotationConfigApplicationContext(AppConf1.class, AppConf2.class);
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
	
	private void processNewCommand(String [] args) {
		if(args.length != 5) {
			this.printHelp();
			return;
		}
		
		MemberRegisterService regSvc = context.getBean("memberRegSvc", MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		
		req.setEmail(args[1]);
		req.setName(args[2]);
		req.setPassword(args[3]);
		req.setConfirmPassword(args[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println(" ?��?��?? ?��?�� ?��?��?�� ?��치하�? ?��?��?��?��.\n");
			return;
		}
		
		try {
			regSvc.regist(req);
			System.out.println(" ?��?�� ?��보�?? ?��록했?��?��?��.\n");
		}
		catch(DuplicateMemberException e) {
			System.out.println(" ?���? 존재?��?�� ?��메일?��?��?��.\n");
		}		
	}
	
	private void processChangeCommand(String [] args) {
		if(args.length != 4) {
			this.printHelp();
			return;
		}
		
		ChangePasswordService pwdSvc = context.getBean("changePwdSvc", ChangePasswordService.class);
		
		try {
			pwdSvc.changePassword(args[1], args[2], args[3]);
			System.out.println(" 비�?번호�? ?�� �?경했?��?��?��!\n");
		}
		catch(MemberNotFoundException e) {
			System.out.println(" 존재?���? ?��?�� ?��메일?��?��?��.\n");
		}
		catch(WrongIdPasswordException e) {
			System.out.println(" ?��못된 ?��?��?�� ?��?�� ?��?��?��?�� ?��?��?��.\n");
		}
	}
	
	private void processListCommand() {		
		MemberListPrinter listPrinter = context.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	}
	
	private void processInfoCommand(String [] args) {
		if(args.length != 2) {
			this.printHelp();
			return;
		}
		
		MemberInfoPrinter infoPrinter = context.getBean("infoPrinter", MemberInfoPrinter.class);
		
		try {
			infoPrinter.printMemberInfo(args[1]);
		}
		catch(MemberNotFoundException e) {
			System.out.println(" 존재?���? ?��?�� ?��메일?��?��?��.\n");
		}
	}
	
	private void processVersionCommand() {		
		VersionPrinter versionPrinter = context.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.print();
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		MainForSpring2 main = new MainForSpring2();
		String command = "";
		
		System.out.println("\n ### ?��?�� �?�? ?��로그?�� ###\n");
		
		while(true) {
			System.out.print(" 명령?���? ?��?��?��?��?��: ");
			command = reader.readLine();
			
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("\n ?��로그?��?�� 종료?��?��?��.");
				break;
			}
			else if(command.startsWith("new ")) {
				main.processNewCommand(command.split(" "));
			}
			else if(command.startsWith("change ")) {
				main.processChangeCommand(command.split(" "));
			}
			else if(command.startsWith("list")) {
				main.processListCommand();
			}
			else if(command.startsWith("info ")) {
				main.processInfoCommand(command.split(" "));
			}
			else if(command.startsWith("version")) {
				main.processVersionCommand();
			}
			else {
				main.printHelp();
			}
		}
	}
}






















