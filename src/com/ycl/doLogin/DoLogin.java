package com.ycl.doLogin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ycl.register.dao.impl.UserDaoImpl;
import com.ycl.register.entity.User;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    /*public DoLogin() {
        super();
        // TODO Auto-generated constructor stub
    }*/

	/**
	 * @param session 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		HttpSession s = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		if(name == null || pwd == null) {
			request.setAttribute("message", "未输入用户名或者密码！");
			request.getRequestDispatcher("login.jsp").forward(
				request, response);
		}
		
		if(name.trim().isEmpty() || pwd.trim().isEmpty()) {
			request.setAttribute("message", "未输入用户名或者密码！");
			request.getRequestDispatcher("login.jsp").forward(
				request, response);
		}
		
		try {		//从加载驱动开始就要注意捕获异常
			Class.forName("com.mysql.jdbc.Driver"); //加载数据库驱动
			//创建连接
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/register?useUnicode=true&characterEncoding=utf-8",
					"root", "root");
			//创建Statement对象
			stmt = con.createStatement();
			//获得结果集
			rs = stmt
					.executeQuery("SELECT * FROM user WHERE username='"
							+ name + "' AND password='" + pwd +"'");
			if (!rs.next()) {
				request.setAttribute("message", "用户名或者密码错误，请重新登录！");
				request.getRequestDispatcher("login.jsp").forward(
						request, response);
			} else {
				s.setAttribute("login", "name");
				response.sendRedirect("success.jsp");
			}
		} catch (Exception e) {
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
