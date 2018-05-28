package com.baobang.piospa.model;

import java.util.Date;

/**
  * @author BaoBang
  * @Created May 27, 2018
  * 
  */
public class CartItemService {
	private int productId;
	private int number;
	private Date dateBooking;
	public CartItemService(int productId, int number, Date dateBooking) {
		super();
		this.productId = productId;
		this.number = number;
		this.dateBooking = dateBooking;
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
	public Date getDateBooking() {
		return dateBooking;
	}
	public void setDateBooking(Date dateBooking) {
		this.dateBooking = dateBooking;
	}
	
	
}
