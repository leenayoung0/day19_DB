package quiz;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		DBClass db = new DBClass();
		Scanner sc = new Scanner(System.in);
		int num, age;
		String memNum;
		String name;
		int result;
		
		System.out.println("==== ȸ������ ====");
		while(true) {
			System.out.println("1.ȸ�� ��� 2.ȸ�� �߰� 3.ȸ�� ���� 4.ȸ�� ����");
			num = sc.nextInt();
			switch(num) {
			case 1: 
				ArrayList<MemberDTO> list = db.getUsers();
				if(list.size()==0) {
					System.out.println("����� �����Ͱ� �����ϴ�");
				}else {
					for(int i =0; i<list.size(); i++) {
						System.out.println("ȸ�� ��ȣ : "+list.get(i).getMemNum());
						System.out.println("�̸� : "+list.get(i).getName());
						System.out.println("���� : "+list.get(i).getAge());
						System.out.println("------------------------------------");
					}
				}	
				break;
			case 2:
				System.out.println("ȸ����ȣ �Է�");
				memNum = sc.next();
				System.out.println("�̸� �Է�");
				name = sc.next();
				System.out.println("���� �Է�");
				age = sc.nextInt();
				result = db.saveData(memNum, name, age);
				if(result == 1) {
					System.out.println("���������� ����");
				}else {
					System.out.println("������ ȸ���� �����մϴ�");
				}
				break;
			case 3: 
				System.out.println("������ ���̵� �Է�(�����ϴ� ȸ����ȣ�� �Է��� �ּ���)");
				String memNum1 = sc.next();
				System.out.println("������ �̸� �Է�");
				String name1 = sc.next();
				System.out.println("������ ���� �Է�");
				int age1 = sc.nextInt();
				if(db.modify(memNum1, name1, age1)==1) {
					System.out.println("������ �Ϸ� �Ǿ����ϴ�");
				}else {
					System.out.println("�ش� ���̵�� �������� �ʽ��ϴ�");
				}
				break;
			case 4: 
				System.out.println("������ ȸ����ȣ �Է�");
				String userNum = sc.next();
				result = db.delete(userNum);
				if(result == 1) {
					System.out.println("���� �Ǿ����ϴ�");
				}else {
					System.out.println("�ش� ȸ����ȣ�� �������� �ʽ��ϴ�");
				}	
				break;
			}
		}
	}

}
