package chap03.main;

import java.io.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import chap03.model.*;
import chap03.exception.*;
import chap03.config.AppContext;

public class MainForSpring {

	private static AnnotationConfigApplicationContext context;
	
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
			System.out.println(" 암호와 암호 확인이 일치하지 않습니다.\n");
			return;
		}
		
		try {
			regSvc.regist(req);
			System.out.println(" 회원 정보를 등록했습니다.\n");
		}
		catch(DuplicateMemberException e) {
			System.out.println(" 이미 존재하는 이메일입니다.\n");
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
			System.out.println(" 존재하지 않는 이메일입니다.!\n");
		}
		catch(MemberNotFoundException e) {
			System.out.println(" 議댁옱�븯吏� �븡�뒗 �씠硫붿씪�엯�땲�떎.\n");
		}
		catch(WrongIdPasswordException e) {
			System.out.println(" �옒紐삳맂 �븘�씠�뵒 �삉�뒗 �뙣�뒪�썙�뱶 �엯�땲�떎.\n");
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
			System.out.println(" 議댁옱�븯吏� �븡�뒗 �씠硫붿씪�엯�땲�떎.\n");
		}
	}
	
	private void processVersionCommand() {		
		VersionPrinter versionPrinter = context.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.print();
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		MainForSpring main = new MainForSpring();
		String command = "";
		
		System.out.println("\n ### �쉶�썝 愿�由� �봽濡쒓렇�옩 ###\n");
		
		while(true) {
			System.out.print(" 紐낅졊�뼱瑜� �엯�젰�븯�꽭�슂: ");
			command = reader.readLine();
			
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("\n �봽濡쒓렇�옩�쓣 醫낅즺�빀�땲�떎.");
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






















