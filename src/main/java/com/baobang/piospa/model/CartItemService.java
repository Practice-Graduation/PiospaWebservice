package com.baobang.piospa.model;


/**
  * @author BaoBang
  * @Created May 27, 2018
  * 
  */
public class CartItemService {
	private int productId;
	private int number;
	private String dateBooking;
	private String timeBooking;
	public CartItemService(int productId, int number, String dateBooking, String timeBooking) {
		super();
		this.productId = productId;
		this.number = number;
		this.dateBooking = dateBooking;
		this.timeBooking = timeBooking;
	}
	public CartItemService() {
		super();
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getDateBooking() {
		return dateBooking;
	}
	public void setDateBooking(String dateBooking) {
		this.dateBooking = dateBooking;
	}
	public String getTimeBooking() {
		return timeBooking;
	}
	public void setTimeBooking(String timeBooking) {
		this.timeBooking = timeBooking;
	}
	
	
}
