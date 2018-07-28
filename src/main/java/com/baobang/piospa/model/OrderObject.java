package com.baobang.piospa.model;

import java.util.ArrayList;
import java.util.List;

import com.baobang.piospa.entities.Order;

/**
  * @author BaoBang
  * @Created Jul 28, 2018
  * 
  */
public class OrderObject {

	private Order order;
	
	private List<OrderProductObject> orderProducts = new ArrayList<>();
	private List<BookingDetailObject> bookingDetails = new ArrayList<>();
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderProductObject> getOrderProducts() {
		return orderProducts;
	}
	public void setOrderProducts(List<OrderProductObject> orderProducts) {
		this.orderProducts = orderProducts;
	}
	public List<BookingDetailObject> getBookingDetails() {
		return bookingDetails;
	}
	public void setBookingDetails(List<BookingDetailObject> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
	public OrderObject(Order order, List<OrderProductObject> orderProducts, List<BookingDetailObject> bookingDetails) {
		super();
		this.order = order;
		this.orderProducts = orderProducts;
		this.bookingDetails = bookingDetails;
	}
	public OrderObject() {
		super();
	}
	
	
	
}
