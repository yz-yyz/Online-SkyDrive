package com.ycl.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//上传文件存储目录
	
	//private static final String UPLOAD_DIRECTORY = "test_u8";
	
	//上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;//3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;
	//private File storeFile;
	
	
	//上传数据及保存文件
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 //DataInputStream in = null;
		HttpSession s = request.getSession();
		String sname = (String)s.getAttribute("login");
		FileOutputStream fileOut = null;
		 
		 
		if(!ServletFileUpload.isMultipartContent(request)){
			PrintWriter writer = response.getWriter();
			writer.println("Error:表单必须包含 enctype=multipart/form-data");
			writer.flush();
			return;
		}
		
		//配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		
		//设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);
		
		//设置最大请求值（包含文件和表单数据）
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		//中文处理
		upload.setHeaderEncoding("UTF-8");
		
		//构造临时路径来存储上传的文件
		//这个路径相对当前应用目录
		//String uploadPath = "d:" + File.separator+"在线网盘" + File.separator + UPLOAD_DIRECTORY;
		String uploadPath = getServletContext().getRealPath("/") + File.separator + sname;//sname
		System.out.println(uploadPath);
		
		
		
		//如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()){
			uploadDir.mkdir();
		}
		//D:\\在线网盘\\test
		try{
			//解析请求文件
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);
			
			if(formItems !=null && formItems.size()>0){
				//迭代表单数据
				for (FileItem item:formItems){
					//处理不在表单中的字段
					if(!item.isFormField()){
						String fileName = new File(item.getName()).getName();
						
						//上传文件路径
						String filePath = uploadPath + File.separator + fileName;
						//在控制台输出文件的上传路径
						System.out.println(filePath);
						System.out.println(fileName);
						//保存文件到硬盘
						File file2=new File(filePath);
						//File file=new File(filePath,fileName);
						//fileOut = new FileOutputStream(file);
						//fileOut.write(in);
						item.write(file2);
						Connection con = null;
						Statement stmt = null;
						//java.sql.PreparedStatement pstmt = null;
						ResultSet rs = null;
						try {		//从加载驱动开始就要注意捕获异常
							
							Class.forName("com.mysql.jdbc.Driver"); //加载数据库驱动
							//创建连接
							con = DriverManager.getConnection(
									"jdbc:mysql://localhost:3306/register?useUnicode=true&characterEncoding=utf-8",
									"root", "root");
							//创建Statement对象
							stmt = con.createStatement();
							//获得结果集
							int count = stmt.executeUpdate("INSERT INTO test_u7_files(filename) VALUES('"+fileName+"')");
							//String sql = "INSERT INTO test_u8_files (filename) VALUES(?)";
							//stmt = con.prepareStatement(sql);
							//pstmt.setString(1, fileName);
							//int count = pstmt.executeUpdate(sql);
							if(count==1){
								System.out.println("添加表单成功");
							}else{
								System.out.println("添加表单失败");
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							try{
								rs.close();
							}catch(Exception e){
								e.printStackTrace();
							}
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
						request.setAttribute("message", "文件上传成功");
						
					}
				}
			}
		}catch(Exception ex){
			request.setAttribute("message", "错误信息："+ex.getMessage());
		}
		//跳转到message.jsp
		getServletContext().getRequestDispatcher("/message2.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
