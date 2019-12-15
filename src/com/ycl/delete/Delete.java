package com.ycl.delete;

import java.io.File;
import java.io.IOException;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;*/

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ycl.register.dao.impl.FileRDaoImpl;
import com.ycl.register.entity.FileR;


import net.sf.json.JSONArray;

/**
 * Servlet implementation class Delete
 */
//@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
				String filename = request.getParameter("filename"); 
			    
			    HttpSession s = request.getSession();
			    String mulu = (String)s.getAttribute("login");
			    File file = new File(getServletContext().getRealPath("/") + File.separator + mulu + File.separator + filename); 
			    if(file.exists()){
			    	 System.out.println("ex");
			    	
			    }
			    if (!file.isDirectory()) { 
			      file.delete(); 
			      System.out.println(1);
			    } else { 
			    	System.out.println(2);
			    } 
			    if(file.exists()){
			    	 
			    	
			    }else{
			    	FileRDaoImpl f_up = new FileRDaoImpl();
					FileR f = new FileR(filename,"delete");
					f_up.insert(f, mulu);
					request.setAttribute("message", "删除成功！");
					request.setCharacterEncoding("utf-8");
					//request.getRequestDispatcher("main.jsp").forward(request, response);
					response.sendRedirect("main.jsp");
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
