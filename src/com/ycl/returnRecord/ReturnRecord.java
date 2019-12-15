package com.ycl.returnRecord;

import java.io.IOException;
import java.io.PrintWriter;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;*/
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ycl.register.dao.impl.FileRDaoImpl;
import com.ycl.register.entity.FileR;

/**
 * Servlet implementation class ReturnRecord
 */
//@WebServlet("/ReturnRecord")
public class ReturnRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = request.getSession();
		String name = (String) s.getAttribute("login");
		request.setCharacterEncoding("utf-8");
		FileRDaoImpl f_up = new FileRDaoImpl();
		Vector<FileR>files = f_up.findFileRs(name);
		request.setCharacterEncoding("utf-8");
		StringBuilder str = new StringBuilder("");
		for(FileR ls:files){
			//s.append("<p class='className'>"+u.getPassword()+" "+u.getUsername()+"</p>");
			String a="<div class='record'><div class='recorddetal detalFname'>"+ls.getFilename()+"</div>";	
			String b="<div class='recorddetal detalOperation'>"+ ls.getEvent()  +"</div>";
			String c="<div class='recorddetal detalDate'>"+ls.getTime()+"</div></div>";
			str.append(a+b+c);
		}
		PrintWriter out = response.getWriter();
		out.print(s);
	}
		/*Connection con = null;
		Statement stmt = null;
		//java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/register?useUnicode=true&characterEncoding=utf-8",
					"root", "root");
			stmt = con.createStatement();
			//��ý����
			rs = stmt.executeQuery("SELECT * FROM "+name);
			ArrayList<String> recordmessage = new ArrayList<String>();
			while(rs.next()){
				recordmessage.add(rs.getString(1));
				recordmessage.add(rs.getString(2));
				recordmessage.add(rs.getString(3));
			}
			s.setAttribute("recordmessage", recordmessage);
			response.sendRedirect("main.jsp");
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
