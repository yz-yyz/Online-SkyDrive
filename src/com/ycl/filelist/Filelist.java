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
		//��ȡָ��Ŀ¼����
				//String FilePath = "D:"+File.separator+"��ͼ/";
				HttpSession s = request.getSession();
				String sname = (String)s.getAttribute("login");
				String gxPath = getServletContext().getRealPath("/") + File.separator + sname;//sname
				ArrayList<String> list = new ArrayList();
				//�洢Ҫ���ص��ļ���
				//String[] files = null;
				//Map<String, String>fileNameMap = new HashMap<String,String>();
				
				//�ݹ����filepathĿ¼�µ������ļ���Ŀ¼���ļ����洢��map������
				
				listfile(new File(gxPath),list);
				
				//��Map���Ϸ��͵�listfile.jspҳ����ʾ
				
				s.setAttribute("filelist", list);
				response.sendRedirect("listfile.jsp");
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
