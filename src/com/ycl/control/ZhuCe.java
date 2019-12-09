package com.ycl.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ZhuCe
 */
@WebServlet("/ZhuCe")
public class ZhuCe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZhuCe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String ckpwd = request.getParameter("ckpwd");
		if(!pwd.equals(ckpwd)){
			request.setAttribute("message", "两次输入密码不一样！");
		}
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/register?useUnicode=true&characterEncoding=utf-8",
					"root", "root");
			stmt = con.createStatement();
			//获得结果集
			rs = stmt
					.executeQuery("SELECT * FROM user WHERE username='"
							+ name + "'");
			if(rs.next()){
				request.setAttribute("message", "注册失败，用户名已经存在，请重新注册。");
				request.getRequestDispatcher("register.jsp").forward(
						request, response);
			}else {
			    int count = stmt.executeUpdate("INSERT INTO user (username, password) VALUES('"+name+"','"+pwd+"')"	);
			    if (count == 1) {
			    	//+登陆页面！！！！！！！！！！！！！！！！！！！！！！！！！
					System.out.println("<script type='text/javascript'>"
						+ "alert('注册成功！马上登录。');"
						+ "location.href='login.jsp';"
						+ "</script>");
			    } else {
					request.setAttribute("message", "注册失败，请重试！");
					request.getRequestDispatcher("register.jsp").forward(
						request, response);
			    }
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
