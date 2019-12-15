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
			    	/*Connection con = null;
					Statement stmt = null;
					//java.sql.PreparedStatement pstmt = null;
					
			    	try{
			    		Class.forName("com.mysql.jdbc.Driver"); //�������ݿ�����
						//��������
						con = DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/register?useUnicode=true&characterEncoding=utf-8",
								"root", "root");
						//����Statement����
						stmt = con.createStatement();
						Date date = new Date();
						SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
						String time = format.format(date);
						//��ý����
						int count = stmt.executeUpdate("INSERT INTO "+mulu+"(filename,events,time) VALUES('"+filename.toString()+"','delete','"+time+"')");
						//String sql = "INSERT INTO test_u8_files (filename) VALUES(?)";
						//stmt = con.prepareStatement(sql);
						//pstmt.setString(1, fileName);
						//int count = pstmt.executeUpdate(sql);
						if(count==1){
							System.out.println("��ӱ��ɹ�");
						}else{
							System.out.println("��ӱ�ʧ��");
						}
						response.sendRedirect("Filelist");
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
					}*/
			    }
			    response.sendRedirect("load.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
