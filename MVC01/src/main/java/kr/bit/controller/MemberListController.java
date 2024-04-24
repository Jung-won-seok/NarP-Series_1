package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@WebServlet("/memberList.do")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. 클라이언트의 요청받기(/memberList.do)
		//2. 회원전체 리스트 가져오기(Model연동)
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = dao.memberList();
		//3. 회원전체 리스트를 HTML로 만들어서 응답하기
		// - 응답되는 데이터 안에 한글이 있는경우 -> 인코딩
		//System.out.println(list);
		response.setContentType("text/html;charset=utf-8"); //MINE Type(서버가 클라이언트에게 어떤 형식으로 전송할지)
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");

		out.println("<table>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>번호</th>");
		out.println("<th>아이디</th>");
		out.println("<th>비밀번호</th>");
		out.println("<th>이름</th>");
		out.println("<th>나이</th>");
		out.println("<th>이메일</th>");
		out.println("<th>전화번호</th>");
	    out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		
		for(MemberVO vo: list) {
		out.println("<tr>");
		out.println("<td>"+vo.getNum()+"</td>");
		out.println("<td>"+vo.getId()+"</td>");
		out.println("<td>"+vo.getPass()+"</td>");
		out.println("<td>"+vo.getName()+"</td>");
		out.println("<td>"+vo.getAge()+"</td>");
		out.println("<td>"+vo.getEmail()+"</td>");
		out.println("<td>"+vo.getPhone()+"</td>");
		out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}
}
