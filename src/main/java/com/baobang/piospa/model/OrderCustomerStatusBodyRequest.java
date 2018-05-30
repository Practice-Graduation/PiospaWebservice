package com.baobang.piospa.model;

/**
 * @author BaoBang
 * @Created May 30, 2018
 * 
 */
public class OrderCustomerStatusBodyRequest {

	private int customerId;
	private int orderStatusId;

	public OrderCustomerStatusBodyRequest(int customerId, int orderStatusId) {
		super();
		this.customerId = customerId;
		this.orderStatusId = orderStatusId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

}
