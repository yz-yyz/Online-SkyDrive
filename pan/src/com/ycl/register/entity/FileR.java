package com.ycl.register.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileR {
	private String filename;
	private String event;
	private String time;
	public FileR(String filename,String event,String time){
		this.filename=filename;
		this.event=event;
		this.time=time;
	}
	public FileR(String filename,String event){
		this.filename=filename;
		this.event=event;
		Date date = new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		this.time = format.format(date);
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
