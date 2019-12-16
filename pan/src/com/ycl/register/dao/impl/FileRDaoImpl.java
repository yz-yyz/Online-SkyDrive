package com.ycl.register.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.ycl.register.dao.BaseDao;
import com.ycl.register.dao.FileDao;
import com.ycl.register.dao.RSProcessor;
import com.ycl.register.entity.FileR;


public class FileRDaoImpl extends BaseDao implements FileDao{

	
	@Override
	public Vector<FileR> findFileRs(String tablename) {
		String sql = "select * from  "+tablename+"_db order by time desc";
		Object[] params = {};

		RSProcessor getFileRsProcessor = new RSProcessor(){

			public Object process(ResultSet rs) throws SQLException {
				Vector<FileR> files = new Vector<FileR>();
				
				while(rs.next()) {
					String filename = rs.getString("filename");
					String event = rs.getString("event");
					String time = rs.getString("time");
					
					FileR file = new FileR( filename,event, time);
					files.add(file);
				}
				
				return files;
				
			}

		};
		
		return (Vector<FileR>)this.executeQuery(getFileRsProcessor, sql, params);
	}

	@Override
	public int insert(FileR file,String tablename) {
		String sql = "insert "+tablename+"_db"+"  values(?,?,?)";
		Object[] params = {file.getFilename(),file.getEvent(),file.getTime()};
		return this.exceuteUpdate(sql, params);
	}

}
