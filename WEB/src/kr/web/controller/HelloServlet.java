package kr.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.web.util.MyUtil;

@WebServlet("/hs.do") //<-web.xml
public class HelloServlet extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		//1~100까지의 총 합=?
		MyUtil my = new MyUtil();
		int sum=my.hap();
		
		//요청한 클라이언트에게 응답하기
		PrintWriter out =  resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println(sum);
		out.println("</body>");
		out.println("</html>");
	}
}
