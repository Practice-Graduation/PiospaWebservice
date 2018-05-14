package com.baobang.piospa.model;
/**
  * @author BaoBang
  * @Created May 14, 2018
  * 
  */
public class BookingDetailRequest {

	private int roomId;
	private String date;
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public BookingDetailRequest(int roomId, String date) {
		super();
		this.roomId = roomId;
		this.date = date;
	}
	public BookingDetailRequest() {
		super();
	}
	
	
}
