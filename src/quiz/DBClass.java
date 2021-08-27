package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBClass {
	private String url;
	private String id;
	private String pwd;
	private Connection con;
	public DBClass() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			id = "itbank";
			pwd = "it";
			con = DriverManager.getConnection(url,id,pwd);
			System.out.println(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int saveData(String memNum, String name, int age) {
		int result = 0;
		String sql = "insert into newmem values(?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, memNum);
			ps.setString(2, name);
			ps.setInt(3, age);
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public ArrayList<MemberDTO> getUsers(){
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		String sql = "select * from newmem";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setMemNum(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				list.add(dto);			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public int modify(String memNum, String name, int age) {
		int result = 0;
		String sql = "update newmem set name='ȫ�浿', age=20 where id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, memNum);
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int delete(String userNum) {
		int result = 0;
		String sql = "delete from newmem where id =?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userNum);
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
