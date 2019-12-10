package com.ycl.filelist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Filelist
 */
@WebServlet("/Filelist")
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
		//获取指定目录！！
				//String FilePath = "D:"+File.separator+"杂图/";
				HttpSession s = request.getSession();
				String sname = (String)s.getAttribute("login");
				String gxPath = getServletContext().getRealPath("/") + File.separator + sname;//sname
				ArrayList<String> list = new ArrayList();
				//存储要下载的文件名
				//String[] files = null;
				//Map<String, String>fileNameMap = new HashMap<String,String>();
				
				//递归遍历filepath目录下的所有文件和目录将文件名存储到map集合中
				
				listfile(new File(gxPath),list);
				
				//将Map集合发送到listfile.jsp页面显示
				
				s.setAttribute("filelist", list);
				response.sendRedirect("listfile.jsp");
				//request.getRequestDispatcher("listfile.jsp").forward(request, response);

	}
	private void listfile(File file, ArrayList<String> list) {
		// TODO Auto-generated method stub
		//如果file代表的不是一个文件而是一个目录
		if(!file.isFile()){
			
			//列出目录下文件
			File[] files = file.listFiles();
			
			//遍历files数组
			for(File f:files){
				//递归
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
