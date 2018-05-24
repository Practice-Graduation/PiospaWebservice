package com.baobang.piospa.model;
/**
  * @author BaoBang
  * @Created May 14, 2018
  * 
  */
public class BookingDetailRequest {

	private String date;

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public BookingDetailRequest(String date) {
		super();
		this.date = date;
	}
	public BookingDetailRequest() {
		super();
	}
	
	
}
