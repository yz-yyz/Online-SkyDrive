package com.ycl.filelist;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Filelist
 */
//@WebServlet("/Filelist")
public class Filelist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Filelist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				HttpSession s = request.getSession();
				String sname = (String)s.getAttribute("login");
				String gxPath = getServletContext().getRealPath("/") + File.separator + sname;//sname
				ArrayList<String> list = new ArrayList<String>();
				File f = new File(gxPath);
				if(!f.isFile()&&f.exists()){
					listfile(f,list);
				}
				request.setCharacterEncoding("utf-8");
				StringBuilder str = new StringBuilder("");
				for(String ls:list){
					//s.append("<p class='className'>"+u.getPassword()+" "+u.getUsername()+"</p>");
					String a=  "<div class='file'><img src='images/fileicon.png' class='fileicon' alt='1'><div class='filename'>";
					String b=ls;
					String c=  "</div><a href='DownLoad?filename="+ls+"' class='links'><button type='button'>download</button></a><span class='message'>message</span> <a  href='Delete?filename="+ls+"' class='links1'><button type='button'>delete</button></a></div>";
					str.append(a+b+c);
				}
				PrintWriter out = response.getWriter();
				out.print(str);
	}
	private void listfile(File file, ArrayList<String> list) {
		if(!file.isFile()){
			File[] files = file.listFiles();
			for(File f:files){
				listfile(f,list);
			}
		}else{
			String fileOrgName = file.getName();
			int index =  fileOrgName.lastIndexOf(".");
			String fileName = fileOrgName.substring(0, index);
			System.out.print(file.getName()+"         ");
			System.out.println(fileName);
			list.add(file.getName());
		
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
