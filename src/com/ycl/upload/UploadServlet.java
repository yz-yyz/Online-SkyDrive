package com.ycl.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//�ϴ��ļ��洢Ŀ¼
	private static final String UPLOAD_DIRECTORY = "test";
	
	//�ϴ�����
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;//3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;
	//private File storeFile;
	
	
	//�ϴ����ݼ������ļ�
	
       
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
		 FileOutputStream fileOut = null;
		 
		if(!ServletFileUpload.isMultipartContent(request)){
			PrintWriter writer = response.getWriter();
			writer.println("Error:����������� enctype=multipart/form-data");
			writer.flush();
			return;
		}
		
		//�����ϴ�����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//�����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		
		//������ʱ�洢Ŀ¼
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		//��������ļ��ϴ�ֵ
		upload.setFileSizeMax(MAX_FILE_SIZE);
		
		//�����������ֵ�������ļ��ͱ������ݣ�
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		//���Ĵ���
		upload.setHeaderEncoding("UTF-8");
		
		//������ʱ·�����洢�ϴ����ļ�
		//���·����Ե�ǰӦ��Ŀ¼
		//String uploadPath = "d:" + File.separator+"��������" + File.separator + UPLOAD_DIRECTORY;
		String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;
		System.out.println(uploadPath);
		
		
		
		//���Ŀ¼�������򴴽�
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()){
			uploadDir.mkdir();
		}
		//D:\\��������\\test
		try{
			//���������ļ�
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);
			
			if(formItems !=null && formItems.size()>0){
				//������������
				for (FileItem item:formItems){
					//�������ڱ����е��ֶ�
					if(!item.isFormField()){
						String fileName = new File(item.getName()).getName();
						
						//�ϴ��ļ�·��
						String filePath = "d:" + File.separator+"��������" + File.separator + UPLOAD_DIRECTORY+File.separator+ fileName;
						
						//�ڿ���̨����ļ����ϴ�·��
						System.out.println(filePath);
						//�����ļ���Ӳ��
						File file2=new File(filePath);
						//File file=new File(filePath,fileName);
						//fileOut = new FileOutputStream(file);
						//fileOut.write(in);
						item.write(file2);
						
						request.setAttribute("message", "�ļ��ϴ��ɹ�");
						
					}
				}
			}
		}catch(Exception ex){
			request.setAttribute("message", "������Ϣ��"+ex.getMessage());
		}
		//��ת��message.jsp
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