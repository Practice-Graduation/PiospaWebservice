package com.baobang.piospa.model;
/**
  * @author BaoBang
  * @Created Aug 16, 2018
  * 
  */
public class DateRequest {

	private String date;
	private String time;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public DateRequest(String date, String time) {
		super();
		this.date = date;
		this.time = time;
	}
	public DateRequest() {
		super();
	}
	
	
}
