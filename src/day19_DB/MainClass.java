package day19_DB;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass { // �⺻Ʋ�� ���⿡
	public static void main(String[] args) {
		DBClass db = new DBClass();
		Scanner input = new Scanner(System.in);
		int num;
		while(true) {
			System.out.println("1.��� 2.��ü���� 3.���� 4.����");
			num = input.nextInt();
			switch(num) {
			case 1:
				System.out.println("�й� �Է�");
				String stNum = input.next();
				System.out.println("�̸� �Է�");
				String name = input.next();
				System.out.println("���� �Է�");
				int age = input.nextInt();
				//int result = db.saveData(stNum, name, age);
				int result = db.saveData02(stNum, name, age);
				if(result == 1) {
					System.out.println("���������� ����");
				}else {
					System.out.println("������ ���̵� �����մϴ�");
				}
				break;
			case 2:
				ArrayList<StudentDTO> list = db.getUsers();
				if(list.size()==0) {
					System.out.println("����� �����Ͱ� �����ϴ�.");
				}else {
					for(int i=0; i<list.size(); i++) {
						System.out.println("�й� : "+list.get(i).getStNum());
						System.out.println("�̸� : "+list.get(i).getName());
						System.out.println("���� : "+list.get(i).getAge());
						System.out.println("------------------------------");
					}
				}
				break;
			case 3:
				System.out.println("���� �й� �Է�");
				String userNum = input.next();
				int re = db.delete(userNum);
				if(re ==1 ) {
					System.out.println("���� �Ǿ����ϴ�");
				}else {
					System.out.println("�ش� �й��� �������� �ʽ��ϴ�");
				}
				break;
			case 4:
				System.out.println("������ ���̵� �Է�(�����ϴ� ���̵� �Է��� �ּ���)");
				String stNum1 = input.next();
				System.out.println("������ �̸� �Է�");
				String name1 = input.next();
				System.out.println("������ ���� �Է�");
				int age1 = input.nextInt();
				
				if(db.modify(stNum1, name1, age1) == 1) {
					System.out.println("������ �Ϸ� �Ǿ����ϴ�");
				}else {
					System.out.println("�ش� ���̵�� �������� �ʽ��ϴ�");
				}
				
				break;
		
			}
		}
		
	}

}
