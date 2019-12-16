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
		if (files.isEmpty()){
			System.out.println("files.isEmp");
		}
		
		request.setCharacterEncoding("utf-8");
		StringBuilder str = new StringBuilder("");
		for(FileR ls:files){
			//s.append("<p class='className'>"+u.getPassword()+" "+u.getUsername()+"</p>");
			String a="<div class='record'><div class='recorddetal detalFname'>"+ls.getFilename()+"</div>";	
			String b="<div class='recorddetal detalOperation'>"+ ls.getEvent()  +"</div>";
			String c="<div class='recorddetal detalDate'>"+ls.getTime()+"</div></div>";
			str.append(a+b+c);
			System.out.println(ls.getFilename()+"  "+ ls.getEvent());
		}
		PrintWriter out = response.getWriter();
		out.print(str);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
