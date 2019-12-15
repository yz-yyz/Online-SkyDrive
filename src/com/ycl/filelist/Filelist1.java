package com.ycl.filelist;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/*import java.util.HashMap;
import java.util.Map;*/

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
public class Filelist1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Filelist1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��ȡָ��Ŀ¼����
				//String FilePath = "D:"+File.separator+"��ͼ/";
				HttpSession s = request.getSession();
				String sname = (String)s.getAttribute("login");
				String gxPath = getServletContext().getRealPath("/") + File.separator + sname;//sname
				ArrayList<String> list = new ArrayList<String>();
				//�洢Ҫ���ص��ļ���
				//String[] files = null;
				//Map<String, String>fileNameMap = new HashMap<String,String>();
				
				//�ݹ����filepathĿ¼�µ������ļ���Ŀ¼���ļ����洢��map������
				File f = new File(gxPath);
				if(!f.isFile()&&f.exists()){
					listfile(f,list);
				}
				
				
				//��Map���Ϸ��͵�listfile.jspҳ����ʾ
				request.setCharacterEncoding("utf-8");
				StringBuilder str = new StringBuilder("");
				for(String ls:list){
					//s.append("<p class='className'>"+u.getPassword()+" "+u.getUsername()+"</p>");
					String a=  "<div class='file'><img src='images/fileicon.png' class='fileicon' alt='1'><div class='filename'>";
					String b=ls.toString();
					String c=  "</div><a href='login.html?filename="+ls.toString()+"' class='links'><button type='button'>download<tton></a><span class='message'>message</span></div>";
					str.append(a+b+c);
					System.out.println(ls);
				}
				PrintWriter out = response.getWriter();
				out.print(s);
					/*s.setAttribute("filelist", list);
				response.sendRedirect("ReturnRecord");*/
				//request.getRequestDispatcher("listfile.jsp").forward(request, response);

	}
	private void listfile(File file, ArrayList<String> list) {
		// TODO Auto-generated method stub
		//���file����Ĳ���һ���ļ�����һ��Ŀ¼
		if(!file.isFile()){
			
			//�г�Ŀ¼���ļ�
			File[] files = file.listFiles();
			
			//����files����
			for(File f:files){
				//�ݹ�
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
