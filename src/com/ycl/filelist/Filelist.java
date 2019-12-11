package com.ycl.filelist;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				String FilePath = "D:"+File.separator+"��ͼ/";
				
				//�洢Ҫ���ص��ļ���
				Map<String, String>fileNameMap = new HashMap<String,String>();
				
				//�ݹ����filepathĿ¼�µ������ļ���Ŀ¼���ļ����洢��map������
				
				listfile(new File(FilePath),fileNameMap);
				
				//��Map���Ϸ��͵�listfile.jspҳ����ʾ
				request.setAttribute("fileNameMap", fileNameMap);
				request.getRequestDispatcher("listfile.jsp").forward(request, response);

	}
	private void listfile(File file, Map<String, String> map) {
		// TODO Auto-generated method stub
		//���file�����Ĳ���һ���ļ�����һ��Ŀ¼
		if(!file.isFile()){
			
			//�г�Ŀ¼���ļ�
			File[] files = file.listFiles();
			
			//����files����
			for(File f:files){
				//�ݹ�
				listfile(f,map);
			}
		}else{
			String fileOrgName = file.getName();
			int index =  fileOrgName.lastIndexOf(".");
			String fileName = fileOrgName.substring(0, index);
			System.out.println(file.getName());
			map.put(file.getName(), fileName);
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