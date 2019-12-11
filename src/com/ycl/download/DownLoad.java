package com.ycl.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//1.文件读取路径？？
//2.文件保存路径
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
		//得到要下载的文件名
		
        String fileName = request.getParameter("filename");
        //要下载的文件的目录
        String fileSavePath = "d:" + File.separator+"在线网盘" ;
        HttpSession s = request.getSession();
		String mulu = (String)s.getAttribute("login");
		String uploadPath = getServletContext().getRealPath("/") + File.separator + mulu;//sname
		
        //要下载的文件
        File file = new File(fileSavePath + File.separator + fileName);
        //如果文件不存在
        if(!file.exists()){
            request.setAttribute("info","您要下载的资源已被删除!");
            request.getRequestDispatcher("messagedownload.jsp").forward(request,response);
            return;
        }
 
        //设置响应头,控制浏览器下载该文件
        response.setHeader("content-disposition","attachment;filename*=UTF-8''" + URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20"));
        //读取要下载的文件,保存到文件输入流
        FileInputStream in = new FileInputStream(fileSavePath + File.separator + fileName);
        System.out.println(fileSavePath + File.separator + fileName);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while(0<=(len=in.read(buffer))){
            //输出缓冲区的内容到浏览器,实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入输出流
        out.close();
        in.close();
        Connection con = null;
		Statement stmt = null;
		//java.sql.PreparedStatement pstmt = null;
		
		 try {		//从加载驱动开始就要注意捕获异常
				
				Class.forName("com.mysql.jdbc.Driver"); //加载数据库驱动
				//创建连接
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/register?useUnicode=true&characterEncoding=utf-8",
						"root", "root");
				//创建Statement对象
				stmt = con.createStatement();
				Date date = new Date();
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String time = format.format(date);
				//获得结果集
				int count = stmt.executeUpdate("INSERT INTO "+mulu+"(filename,events,time) VALUES('"+fileName.toString()+"','dowmload','"+time+"')");
				//String sql = "INSERT INTO test_u8_files (filename) VALUES(?)";
				//stmt = con.prepareStatement(sql);
				//pstmt.setString(1, fileName);
				//int count = pstmt.executeUpdate(sql);
				if(count==1){
					System.out.println("添加表单成功");
				}else{
					System.out.println("添加表单失败");
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
