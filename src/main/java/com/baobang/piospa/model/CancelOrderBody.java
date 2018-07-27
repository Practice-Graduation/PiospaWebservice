package com.baobang.piospa.model;
/**
  * @author BaoBang
  * @Created Jul 27, 2018
  * 
  */
public class CancelOrderBody {
	private int orderId;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public CancelOrderBody() {
		super();
	}

	public CancelOrderBody(int orderId) {
		super();
		this.orderId = orderId;
	}
	
	
}
