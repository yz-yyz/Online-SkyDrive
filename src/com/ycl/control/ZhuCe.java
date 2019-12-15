package com.ycl.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ycl.register.dao.impl.UserDaoImpl;
import com.ycl.register.entity.User;

/**
 * Servlet implementation class ZhuCe
 */
//@WebServlet("/ZhuCe")
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String ckpwd = request.getParameter("ckpwd");
		if(!pwd.equals(ckpwd)){
			request.setAttribute("register", "两次输入密码不一样");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		UserDaoImpl u = new UserDaoImpl();
		int count = u.countUserByName(name);
		if (count==1){
			request.setAttribute("message", "账户以存在！");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}else{
			u.insert(new User(name,pwd));
			Connection con = null;
			Statement stmt = null;
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/ycl_user?useUnicode=true&characterEncoding=utf-8",
						"root", "root");
				stmt = con.createStatement();
				boolean create_table = stmt.execute("Create table "+name+"_db as select * from u");
			    if (!create_table) {
			    } else {
					request.setAttribute("message", "注册失败！");
					request.getRequestDispatcher("register.jsp").forward(
						request, response);
			    }
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
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
			
			response.sendRedirect("login.jsp");
		}
	}
		/*Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ycl_user?useUnicode=true&characterEncoding=utf-8",
					"root", "root");
			stmt = con.createStatement();
			//��ý����
			rs = stmt
					.executeQuery("SELECT * FROM user WHERE username='"
							+ name + "'");
			if(rs.next()){
				request.setAttribute("message", "ע��ʧ�ܣ��û����Ѿ����ڣ�������ע�ᡣ");
				request.getRequestDispatcher("register.jsp").forward(
						request, response);
			}else {
			    int count = stmt.executeUpdate("INSERT INTO user (username, password) VALUES('"+name+"','"+pwd+"')"	);
			    boolean create_table = stmt.execute("Create table "+name+" as select * from us_files");
			    //System.out.println(create_table);
			    if (count == 1&&!create_table) {
			    	//+��½ҳ�棡������������������������������������������������
			    	
					/*System.out.println("<script type='text/javascript'>"
						+ "alert('ע��ɹ������ϵ�¼��');"
						+ "location.href='login.jsp';"
						+ "</script>");*/
			    	/*request.getRequestDispatcher("login.jsp").forward(
							request, response);
					
			    } else {
					request.setAttribute("message", "注册失败！");
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
		}*/
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
