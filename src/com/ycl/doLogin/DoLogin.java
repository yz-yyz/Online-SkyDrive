package com.ycl.doLogin;

import java.io.IOException;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;*/
import java.util.Vector;

import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
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
//@WebServlet("/DoLogin")
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
		HttpSession s = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		if(name == null || pwd == null) {
			request.setAttribute("message", "密码或者名字不能为空！");
			request.getRequestDispatcher("login.jsp").forward(
				request, response);
		}
		
		if(name.trim().isEmpty() || pwd.trim().isEmpty()) {
			request.setAttribute("message", "密码或者名字不能为空！");
			request.getRequestDispatcher("login.jsp").forward(
				request, response);
		}
		UserDaoImpl u_lg = new UserDaoImpl();
		int count = u_lg.countUserByName(name);
		if(count==0){
			request.setAttribute("message", "用户不存在！");
			request.getRequestDispatcher("login.jsp").forward(
				request, response);
		}
		Vector<User> users = u_lg.findUsersByName(name);
		for(User u:users){
			
			if(u.getPassword().trim().equals(pwd.trim())){
				s.setAttribute("login", name);
				//request.getRequestDispatcher("testloginsession.jsp").forward(request, response);
				response.sendRedirect("load.jsp");
			}
			else{
				
				request.setAttribute("message", "密码不正确！");
				request.getRequestDispatcher("login.jsp").forward(
					request, response);
			}
		}
		// TODO Auto-generated method stub
		/*Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {		//�Ӽ���������ʼ��Ҫע�Ⲷ���쳣
			Class.forName("com.mysql.jdbc.Driver"); //�������ݿ�����
			//��������
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/register?useUnicode=true&characterEncoding=utf-8",
					"root", "root");
			//����Statement����
			stmt = con.createStatement();
			//��ý����
			rs = stmt
					.executeQuery("SELECT * FROM user WHERE username='"
							+ name + "' AND password='" + pwd +"'");
			if (!rs.next()) {
				request.setAttribute("message", "�û�������������������µ�¼��");
				request.getRequestDispatcher("login.jsp").forward(
						request, response);
			} else {
				s.setAttribute("login", name);
				//request.getRequestDispatcher("testloginsession.jsp").forward(request, response);
				response.sendRedirect("Filelist");
				
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
		}	*/	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
