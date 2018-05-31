package com.baobang.piospa.model;

import java.util.List;

import com.baobang.piospa.entities.BookingDetail;
import com.baobang.piospa.entities.OrderProduct;

/**
  * @author BaoBang
  * @Created May 31, 2018
  * 
  */
public class OrderResultResponse {
	
	private List<OrderProduct> orderProducts;
	
	private List<BookingDetail> bookingDetails;

	public OrderResultResponse(List<OrderProduct> orderProducts, List<BookingDetail> bookingDetails) {
		super();
		this.orderProducts = orderProducts;
		this.bookingDetails = bookingDetails;
	}
	
	

	public OrderResultResponse() {
		super();
	}



	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public List<BookingDetail> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(List<BookingDetail> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
	
	

}
