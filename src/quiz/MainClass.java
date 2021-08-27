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
		
		System.out.println("==== 회원관리 ====");
		while(true) {
			System.out.println("1.회원 목록 2.회원 추가 3.회원 수정 4.회원 삭제");
			num = sc.nextInt();
			switch(num) {
			case 1: 
				ArrayList<MemberDTO> list = db.getUsers();
				if(list.size()==0) {
					System.out.println("저장된 데이터가 없습니다");
				}else {
					for(int i =0; i<list.size(); i++) {
						System.out.println("회원 번호 : "+list.get(i).getMemNum());
						System.out.println("이름 : "+list.get(i).getName());
						System.out.println("나이 : "+list.get(i).getAge());
						System.out.println("------------------------------------");
					}
				}	
				break;
			case 2:
				System.out.println("회원번호 입력");
				memNum = sc.next();
				System.out.println("이름 입력");
				name = sc.next();
				System.out.println("나이 입력");
				age = sc.nextInt();
				result = db.saveData(memNum, name, age);
				if(result == 1) {
					System.out.println("성공적으로 저장");
				}else {
					System.out.println("동일한 회원이 존재합니다");
				}
				break;
			case 3: 
				System.out.println("수정할 아이디 입력(존재하는 회원번호를 입력해 주세요)");
				String memNum1 = sc.next();
				System.out.println("수정할 이름 입력");
				String name1 = sc.next();
				System.out.println("수정할 나이 입력");
				int age1 = sc.nextInt();
				if(db.modify(memNum1, name1, age1)==1) {
					System.out.println("수정이 완료 되었습니다");
				}else {
					System.out.println("해당 아이디는 존재하지 않습니다");
				}
				break;
			case 4: 
				System.out.println("삭제할 회원번호 입력");
				String userNum = sc.next();
				result = db.delete(userNum);
				if(result == 1) {
					System.out.println("삭제 되었습니다");
				}else {
					System.out.println("해당 회원번호는 존재하지 않습니다");
				}	
				break;
			}
		}
	}

}
