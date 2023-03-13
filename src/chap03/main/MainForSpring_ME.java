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
		System.out.println("\n�߸��� ����Դϴ�. �Ʒ� ������ Ȯ���ϼ���.");
		System.out.println("\n ### ��ɾ� ���� ###");
		System.out.println("\n ��ɾ �Է��ϼ��� : new �̸��� �̸� ��ȣ ��ȣȮ�� ###");
		System.out.println("\n ��ɾ �Է��ϼ��� : change �̸��� �����ȣ �����ȣ ###");
		System.out.println("\n ��ɾ �Է��ϼ��� : list");
		System.out.println("\n ��ɾ �Է��ϼ��� : info �̸���");
		System.out.println("\n ��ɾ �Է��ϼ��� : version");
		System.out.println("\n ��ɾ �Է��ϼ��� : exit \n");
	}
	
	private void processNewCommand(String [] args) {	//����ڰ� �Է��� �̸��� �̸� ��ȣ ��ȣȮ���� �־��־�� ��
		if(args.length !=5) {	// args �� ũ��� 5 �̿��� �Ѵ� (new, �̸���, �̸�, ��ȣ, ��ȣȮ�� 5��)
			this.printHelp();
			return;
		}
		MemberRegisterService regSvc = context.getBean("memberRegSvc",MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(args[1]);
		req.setName(args[2]);
		req.setPassword(args[3]);
		req.setConfirmPassword(args[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {	//��ȣ�� ��ȣȮ�� ��ġ ���� �˻�
			System.out.println("��ȣ�� ��ȣ Ȯ���� ��ġ���� �ʽ��ϴ�");
			return;
		}
		
		try {
			regSvc.regist(req);		// MemberRegisterService �� ���ܸ� �߻���Ű�� ������ try catch ���
			System.out.println("ȸ�� ������ ����߽��ϴ�\n");
		}
		catch(DuplicateFormatFlagsException e){
			System.out.println("�̹� �����ϴ� �̸����Դϴ�.\n");
		}
		
	}
	
	private void processListCommand() {
		MemberListPrinter listPrinter = context.getBean("listPrinter",MemberListPrinter.class);
		listPrinter.printAll();
	}
	
	private void processInfoCommand(String [] args) {	//����ڰ� �Է��� �̸��� �̸� ��ȣ ��ȣȮ���� �־��־�� ��
		if(args.length != 2) {	// args �� ũ��� 5 �̿��� �Ѵ� (new, �̸���, �̸�, ��ȣ, ��ȣȮ�� 5��)
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
		if(args.length !=4) {	// args �� ũ��� 4 �̿��� �Ѵ� (change, �̸���, �����ȣ, �����ȣ 4��)
			this.printHelp();
			return;
		}
		
		ChangePasswordService pwdSvc = context.getBean("changePwdSvc",ChangePasswordService.class);
		try{
			pwdSvc.changePassword(args[1], args[2], args[3]);
		}
		catch(MemberNotFoundException e){
			System.out.println("�������� �ʴ� �̸����Դϴ�. \n");
		}
		catch(WrongIdPasswordException e){
			System.out.println("�߸��� ���̵� �Ǵ� �н����� �Դϴ�. \n");
		}
		
	}
	
	public static void main(String[] args) throws IOException{	//reader�� IOExecption�� �߻��� �� ����
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// scanner���� ȿ������ �Է¹��
		MainForSpring2 main = new MainForSpring2();
		String command="";
		
		System.out.println("\n ### ȸ�� ���� ���α׷� ###");
		
		while(true) {
			System.out.println("��ɾ �Է��ϼ��� : ");
			command = reader.readLine();	// ������� �Է� ��ü�� �ϳ��� ���ڿ��� ��°�� �޾ƿ�
											// �̰Ŵ� IOExecption�� �߻��� �� ����
			if(command.equalsIgnoreCase("exit")){	//��.�� ���ڸ� �������� �ʰ� "exit" �̸�
				System.out.println("\n ���α׷��� �����մϴ�.");
				break;
			}
			else if(command.startsWith("new ")) {	//command�� "new "���� �����ϸ�
				main.processNewCommand(command.split(" "));	//split �� �����ڸ� ������ ���� �����ڴ� " " (����)	
				continue;	// main.printHelp();�� ������ �ʱ� ����
			}
			else if(command.startsWith("change ")) {	//command�� "new "���� �����ϸ�
				main.processChangeCommand(command.split(" "));	//split �� �����ڸ� ������ ���� �����ڴ� " " (����)
				continue;	// main.printHelp();�� ������ �ʱ� ����
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
				main.printHelp();	// �̷� ��ĵ� �ȴ�
			}*/
			main.printHelp();
		}
	}

}
