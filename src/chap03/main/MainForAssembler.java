package chap03.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.DuplicateFormatFlagsException;

import chap03.assembler.Assembler;
import chap03.exception.MemberNotFoundException;
import chap03.exception.WrongIdPasswordException;
import chap03.model.ChangePasswordService;
import chap03.model.MemberRegisterService;
import chap03.model.RegisterRequest;

public class MainForAssembler {
	
	private static Assembler assembler = new Assembler();

	private void printHelp() {
		System.out.println("\n�߸��� ����Դϴ�. �Ʒ� ������ Ȯ���ϼ���.");
		System.out.println("\n ### ��ɾ� ���� ###");
		System.out.println("\n ��ɾ �Է��ϼ��� : new �̸��� �̸� ��ȣ ��ȣȮ�� ###");
		System.out.println("\n ��ɾ �Է��ϼ��� : change �̸��� �����ȣ �����ȣ ###");
		System.out.println("\n ��ɾ �Է��ϼ��� : exit \n");
	}
	
	private void processNewCommand(String [] args) {	//����ڰ� �Է��� �̸��� �̸� ��ȣ ��ȣȮ���� �־��־�� ��
		if(args.length !=5) {	// args �� ũ��� 5 �̿��� �Ѵ� (new, �̸���, �̸�, ��ȣ, ��ȣȮ�� 5��)
			this.printHelp();
			return;
		}
		MemberRegisterService regSvc = assembler.getRegSvc();
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
	
	private void processChangeCommand(String [] args) {
		if(args.length !=4) {	// args �� ũ��� 4 �̿��� �Ѵ� (change, �̸���, �����ȣ, �����ȣ 4��)
			this.printHelp();
			return;
		}
		ChangePasswordService pwdSvc = assembler.getPwdSvc();
		
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
		MainForAssembler main = new MainForAssembler();
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
				main.processNewCommand(command.split(" "));	//split �� �����ڸ� ������ ���� �����ڴ� " " (����)
				continue;	// main.printHelp();�� ������ �ʱ� ����
			}
			/*else {
				main.printHelp();	// �̷� ��ĵ� �ȴ�
			}*/
			main.printHelp();
		}
	}

}
