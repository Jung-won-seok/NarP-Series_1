package kr.bit.model;

//JDBC->myBatis, JPA
import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps; // sql을 전송하는 객체
	private ResultSet rs; // 데이터베이스의 데이터를 가지고와서 저장하는 객체

	// 데이터베이스 연결객체 생성
	public void getConnect() {
		// 데이터베이스 접속URL
		String URL = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimeZone=UTC";
		String user = "root";
		String password = "admin12345";
		// MYSQL Driver Loading
		try {
			// 동적로딩(실행시점에서 객체를 생성하는 방법)
			Class.forName("com.mysql.jdbc.Driver"); // 동적으로 드라이버를 로딩하고, 그것과 자바의 드라이버를 내부적으로 연결한다.
			conn = DriverManager.getConnection(URL, user, password); // 파라메타 가져와서 접속시도
		} catch (Exception e) { // 예외처리
			e.printStackTrace();
		}
	}

	// 회원저장 동작
	public int memberInsert(MemberVO vo) {
		// ?(파라메터)
		String SQL = "insert into member(id, pass, name, age, email, phone) values(?,?,?,?,?,?)";
		getConnect();
		// SQL문장을 전송하는 객체 생성
		int cnt = -1;
		try {
			ps = conn.prepareStatement(SQL); // 미리 컴파일을 시킨다.(속도가 빠르기 때문)
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPass());
			ps.setString(3, vo.getName());
			ps.setInt(4, vo.getAge());
			ps.setString(5, vo.getEmail());
			ps.setString(6, vo.getPhone());

			// 1,0
			cnt = ps.executeUpdate(); // 전송(실행)

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 에러의 유무에 상관없이 무조건 실행되는 블럭
			dbClose();
		}
		return cnt; // 1 or 0
	}

	// 회원(VO)전체 리스트(ArrayList) 가져오기
	public ArrayList<MemberVO> memberList() {
		String SQL = "select * from member";
		getConnect();
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		try {
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery(); // rs->커서(rs가 결과 집합을 가리키는 객체이다.)
			while(rs.next()) { //데이터가 있을 때 까지 db에서 데이터를 가져와서 묶고(생성자로), 담고(ArrayList)
				int num = rs.getInt("num");
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				//묶고 -> 담고
				MemberVO vo = new MemberVO(num, id, pass, name, age, email, phone);
				list.add(vo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}// memberList
	
	public int memeberDelete(int num) {
		String SQL = "delete from member where num=?";
		getConnect();
		int cnt = -1;
		try {
			ps=conn.prepareStatement(SQL);
			ps.setInt(1, num);
			cnt=ps.executeUpdate(); //1 or 0
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	public void memberContent(int num) {
		String SQL = "select * from member where num=?";
		getConnect();
		try {
			ps=conn.prepareStatement(SQL);
			ps.setInt(1, num);
			rs=ps.executeQuery();
			if(rs.next() ) {
				//회원한명의 정보를 가져와서 ->묶고(VO)
			}
		}catch(Exception e){
			
		}finally {
			dbClose();
		}
	}

	// 데이터베이스 연결 끊기
	public void dbClose() {
		try {
		if(rs!=null) rs.close();
		if(ps!=null) ps.close();
		if(conn!=null) conn.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
//Driver클래스를 메모리에 로딩을 하고 getConnection메서드로 URL, user, password의 접속정보를 주면
//커넥션이 만들어진다.(드라이버 관리 -> DriverManager)
