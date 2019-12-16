package com.ycl.register.dao;

import java.util.Vector;

import com.ycl.register.entity.FileR;

public interface FileDao {
	//查找用户
		
		public Vector<FileR> findFileRs(String tablename);
		//添加用户
		public int insert(FileR file,String tablename);
}
