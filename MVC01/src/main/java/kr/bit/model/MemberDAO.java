package kr.bit.model;
//JDBC->myBatis, JPA
import java.sql.*;
public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps; //sql을 전송하는 객체
	private ResultSet rs; //데이터베이스의 데이터를 가지고와서 저장하는 객체
}
