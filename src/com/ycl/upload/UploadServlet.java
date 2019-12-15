package com.ycl.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ycl.insertRecord.IR;
import com.mysql.jdbc.PreparedStatement;
import com.ycl.register.dao.impl.FileRDaoImpl;
import com.ycl.register.entity.FileR;

/**
 * Servlet implementation class UploadServlet
 */
//@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//涓婁紶鏂囦欢瀛樺偍鐩綍
	
	//private static final String UPLOAD_DIRECTORY = "test_u8";
	
	//涓婁紶閰嶇疆
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;//3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;
	//private File storeFile;
	
	
	//涓婁紶鏁版嵁鍙婁繚瀛樻枃浠�
	
       
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
			writer.println("Error:琛ㄥ崟蹇呴』鍖呭惈 enctype=multipart/form-data");
			writer.flush();
			return;
		}
		
		//閰嶇疆涓婁紶鍙傛暟
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//璁剧疆鍐呭瓨涓寸晫鍊� - 瓒呰繃鍚庡皢浜х敓涓存椂鏂囦欢骞跺瓨鍌ㄤ簬涓存椂鐩綍涓�
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		
		//璁剧疆涓存椂瀛樺偍鐩綍
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		//璁剧疆鏈�澶ф枃浠朵笂浼犲��
		upload.setFileSizeMax(MAX_FILE_SIZE);
		
		//璁剧疆鏈�澶ц姹傚�硷紙鍖呭惈鏂囦欢鍜岃〃鍗曟暟鎹級
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		//涓枃澶勭悊
		upload.setHeaderEncoding("UTF-8");
		
		//鏋勯�犱复鏃惰矾寰勬潵瀛樺偍涓婁紶鐨勬枃浠�
		//杩欎釜璺緞鐩稿褰撳墠搴旂敤鐩綍
		//String uploadPath = "d:" + File.separator+"鍦ㄧ嚎缃戠洏" + File.separator + UPLOAD_DIRECTORY;
		String uploadPath = getServletContext().getRealPath("/") + File.separator + sname;//sname
		System.out.println(uploadPath);
		
		
		
		//濡傛灉鐩綍涓嶅瓨鍦ㄥ垯鍒涘缓
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()){
			uploadDir.mkdir();
		}
		//D:\\鍦ㄧ嚎缃戠洏\\test
		try{
			//瑙ｆ瀽璇锋眰鏂囦欢
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);
			
			if(formItems !=null && formItems.size()>0){
				//杩唬琛ㄥ崟鏁版嵁
				for (FileItem item:formItems){
					//澶勭悊涓嶅湪琛ㄥ崟涓殑瀛楁
					if(!item.isFormField()){
						String fileName = new File(item.getName()).getName();
						
						//涓婁紶鏂囦欢璺緞
						String filePath = uploadPath + File.separator + fileName;
						//鍦ㄦ帶鍒跺彴杈撳嚭鏂囦欢鐨勪笂浼犺矾寰�
						System.out.println(filePath);
						System.out.println(fileName);
						//淇濆瓨鏂囦欢鍒扮‖鐩�
						File file2=new File(filePath);
						item.write(file2);
						//IR ir = new IR();
						//ir.insert(sname,fileName);
						FileRDaoImpl f_up = new FileRDaoImpl();
						FileR f = new FileR(fileName,"upload");
						f_up.insert(f, sname);
							
						request.setAttribute("message", "上传成功！");
							
						//File file=new File(filePath,fileName);
						//fileOut = new FileOutputStream(file);
						//fileOut.write(in);
						
						/*Connection con = null;
						Statement stmt = null;
						//java.sql.PreparedStatement pstmt = null;
						
						try {		//浠庡姞杞介┍鍔ㄥ紑濮嬪氨瑕佹敞鎰忔崟鑾峰紓甯�
							
							Class.forName("com.mysql.jdbc.Driver"); //鍔犺浇鏁版嵁搴撻┍鍔�
							//鍒涘缓杩炴帴
							con = DriverManager.getConnection(
									"jdbc:mysql://localhost:3306/register?useUnicode=true&characterEncoding=utf-8",
									"root", "root");
							//鍒涘缓Statement瀵硅薄
							stmt = con.createStatement();
							Date date = new Date();
							SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
							String time = format.format(date);
							//鑾峰緱缁撴灉闆�
							int count = stmt.executeUpdate("INSERT "+sname+"(filename,event,time) VALUES('"+fileName.toString()+"','upload','"+time+"')");
							//String sql = "INSERT INTO test_u8_files (filename) VALUES(?)";
							//stmt = con.prepareStatement(sql);
							//pstmt.setString(1, fileName);
							//int count = pstmt.executeUpdate(sql);
							if(count==1){
								System.out.println("璁板綍娣诲姞鎴愬姛");
							}else{
								System.out.println("璁板綍娣诲姞澶辫触");
							}
						} catch (Exception e) {
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
				}
			}
		}catch(Exception ex){
			request.setAttribute("message", "閿欒淇℃伅锛�"+ex.getMessage());
		}
		//璺宠浆鍒癿essage.jsp
		getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
