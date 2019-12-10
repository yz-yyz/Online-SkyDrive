package com.ycl.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//1.�ļ���ȡ·������
//2.�ļ�����·��
/**
 * Servlet implementation class DownLoad
 */
@WebServlet("/DownLoad")
public class DownLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�õ�Ҫ���ص��ļ���
        String fileName = request.getParameter("filename");
        //Ҫ���ص��ļ���Ŀ¼
        String fileSavePath = "d:" + File.separator+"��������" ;
        //Ҫ���ص��ļ�
        File file = new File(fileSavePath + File.separator + fileName);
        //����ļ�������
        if(!file.exists()){
            request.setAttribute("info","��Ҫ���ص���Դ�ѱ�ɾ��!");
            request.getRequestDispatcher("messagedownload.jsp").forward(request,response);
            return;
        }
 
        //������Ӧͷ,������������ظ��ļ�
        response.setHeader("content-disposition","attachment;filename*=UTF-8''" + URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20"));
        //��ȡҪ���ص��ļ�,���浽�ļ�������
        FileInputStream in = new FileInputStream(fileSavePath + File.separator + fileName);
        System.out.println(fileSavePath + File.separator + fileName);
        //���������
        OutputStream out = response.getOutputStream();
        //����������
        byte buffer[] = new byte[1024];
        int len = 0;
        //ѭ�����������е����ݶ�ȡ������������
        while(0<=(len=in.read(buffer))){
            //��������������ݵ������,ʵ���ļ�����
            out.write(buffer, 0, len);
        }
        //�ر��ļ����������
        out.close();
        in.close();
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
