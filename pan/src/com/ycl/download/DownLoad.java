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
import javax.servlet.http.HttpSession;

import com.ycl.register.dao.impl.FileRDaoImpl;
import com.ycl.register.entity.FileR;

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
        String fileName = request.getParameter("filename");
        System.out.println(fileName);
        String fileSavePath = "d:" + File.separator+"在线网盘" ;
        HttpSession s = request.getSession();
		String mulu = (String)s.getAttribute("login");
		System.out.println("mulu:"+mulu);
		String uploadPath = getServletContext().getRealPath("/") + File.separator + mulu;//sname
		System.out.println("uplad"+uploadPath);
        File file = new File(uploadPath+ File.separator + fileName);
        if(!file.exists()){
            request.setAttribute("message","文件不存在!");
            request.getRequestDispatcher("main.jsp").forward(request,response);
            return;
        }
        response.setHeader("content-disposition","attachment;filename*=UTF-8''" + URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20"));
        FileInputStream in = new FileInputStream(uploadPath + File.separator + fileName);
        System.out.println(fileSavePath + File.separator + fileName);
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        while(0<=(len=in.read(buffer))){
            out.write(buffer, 0, len);
        }
        out.close();
        in.close();
        FileRDaoImpl f_up = new FileRDaoImpl();
		FileR f = new FileR(fileName,"dowmload");
		f_up.insert(f, mulu);
		request.setAttribute("message", "下载成功！");
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
