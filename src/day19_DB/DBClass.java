package day19_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBClass {//데이터베이스에 관련된 내용들만 여기에
	private String url;
	private String id;
	private String pwd;
	private Connection con; //sql에 있는거 가져오기
	public DBClass() {//생성자 만들기
		try {
			//자바에서 오라클에 연결할 수 있게끔 도와주는 라이브러리를 등록하는 것
			//오라클의 기능을 자바에서 사용하기 위한, 무조건 처음 실행 시켜 준다
			Class.forName("oracle.jdbc.driver.OracleDriver"); //이 문구는 그냥 이대로 무조건 쓰는거(잘 외워서 쓰지는 않음)
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			id="itbank";
			pwd="it";
			con = DriverManager.getConnection(url,id,pwd); //통로가 생김. 이를 통해 얻은 내용을 저장('저장만')
			System.out.println(con);
		}catch (Exception e) { // Exception 으로 해야 다 처리됨
			e.printStackTrace();
		}// 예외전가를 해야하는데 메인클래스에서의 ~를 위해 try catch 사용
	}
	/*
	 1. 드라이브 로드(오라클 기능 사용)
	 2. 연결된 객체를 얻어온다.
	 3. 연결된 객체를 이용해서 명령어(쿼리문)을 전송할 수 있는 전송 객체를 얻어온다.
	 4. 전송 객체를 이용해서 데이터베이스에 전송후 결과를 얻어온다.
	 5. 얻어온 결과는 int 또는 ResultSet으로 받는다
	 */
	public ArrayList<StudentDTO> getUsers(){
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		String sql = "select * from newst"; //"   " : 쿼리문(명령어)
		try {
			PreparedStatement ps = con.prepareStatement(sql); //3.  //명령어를 전송하는 역할
			ResultSet rs = ps.executeQuery(); // 리턴 받은 데이터를 rs라는 변수에 저장 (오라클에 저장된 데이터를 갖고 오는거)
			while(rs.next()) {
				StudentDTO dto = new StudentDTO();
				dto.setStNum(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public int saveData(String stNum, String name, int age) {
		// insert into newst values('111', '홍길동',20);
		String sql = "insert into newst values('"+stNum+"', '"+name+"',"+age+")";
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			//ResultSet rs = ps.executeQuery();
			result = ps.executeUpdate(); // 저장 성공시 1을 반환, 실패시 catch이동이나 0을 반환
		}catch (Exception e) {
			e.printStackTrace(); //얘를 안쓰거나 주석처리를 해버리면 뭐가 문제인지 알 수 없음
		}
		return result;
	}
	public int saveData02(String stNum, String name, int age) {// 이게 더 편하지만 위에랑 동작하는건 동일
		String sql = "insert into newst values(?,?,?)";
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, stNum);
			ps.setString(2, name);
			ps.setInt(3, age);
			result = ps.executeUpdate(); 
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public int delete(String userNum) {
		int result = 0;
		//delete from newst where id = 'uesrNum';
		String sql = "delete from newst where id = '"+userNum+"'";
		//String sql = "delete from newst where id = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int modify(String stNum, String name, int age) {
		int result = 0;
		// update newst set name='홍길동', age=20 where id='test';
		String sql = "update newst set name='홍길동', age=20 where id= ? ";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, stNum);
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
