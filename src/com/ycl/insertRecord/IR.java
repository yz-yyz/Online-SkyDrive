package com.ycl.insertRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.ycl.register.entity.FileR;

public class IR {
	public void insert(String sname,String fileName){
		Connection con = null;
		Statement stmt = null;
		//java.sql.PreparedStatement pstmt = null;
		
		try {		//浠庡姞杞介┍鍔ㄥ紑濮嬪氨瑕佹敞鎰忔崟鑾峰紓甯�
			
			Class.forName("com.mysql.jdbc.Driver"); //鍔犺浇鏁版嵁搴撻┍鍔�
			//鍒涘缓杩炴帴
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ycl_user?useUnicode=true&characterEncoding=utf-8",
					"root", "root");
			//鍒涘缓Statement瀵硅薄
			stmt = con.createStatement();
			FileR f = new FileR(fileName,"upload");
			
			//鑾峰緱缁撴灉闆�
			int count = stmt.executeUpdate("INSERT INTO "+sname+"_db(filename,event,time) VALUES('"+f.getFilename()+"','"+f.getEvent()+"','"+f.getTime()+"')");
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
		}
	}
}
