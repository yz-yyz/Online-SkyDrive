package com.ycl.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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
//1.�ļ���ȡ·������
//2.�ļ�����·��
/**
 * Servlet implementation class DownLoad
 */
//@WebServlet("/DownLoad")
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
        String fileSavePath = "d:" +File.separator+"在线网盘" ;
        HttpSession s = request.getSession();
		String mulu = (String)s.getAttribute("login");
		String uploadPath = getServletContext().getRealPath("/") + File.separator + mulu;//sname
		
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
        FileRDaoImpl f_up = new FileRDaoImpl();
		FileR f = new FileR(fileName,"dowmload");
		f_up.insert(f, mulu);
			
		request.setAttribute("message", "下载成功！");
        
        
       /* Connection con = null;
		Statement stmt = null;
		//java.sql.PreparedStatement pstmt = null;
		
		 try {		//�Ӽ���������ʼ��Ҫע�Ⲷ���쳣
				
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
				int count = stmt.executeUpdate("INSERT INTO "+mulu+"(filename,events,time) VALUES('"+fileName.toString()+"','dowmload','"+time+"')");
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
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
