package com.baobang.piospa.model;

import java.util.List;
import com.baobang.piospa.entities.OrderProduct;

/**
  * @author BaoBang
  * @Created May 31, 2018
  * 
  */
public class OrderResultResponse {
	
	private List<OrderProduct> orderProducts;

	public OrderResultResponse(List<OrderProduct> orderProducts) {
		super();
		this.orderProducts = orderProducts;
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

}