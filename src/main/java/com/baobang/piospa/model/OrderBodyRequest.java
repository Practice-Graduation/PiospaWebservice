package com.baobang.piospa.model;

import com.baobang.piospa.entities.Order;

/**
  * @author BaoBang
  * @Created Apr 26, 2018
  * 
  */
public class OrderBodyRequest {

	private Order order;
	private CartShopping cartShopping;
	public OrderBodyRequest(Order order, CartShopping cartShopping) {
		super();
		this.order = order;
		this.cartShopping = cartShopping;
	}
	public OrderBodyRequest() {
		super();
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public CartShopping getCartShopping() {
		return cartShopping;
	}
	public void setCartShopping(CartShopping cartShopping) {
		this.cartShopping = cartShopping;
	}
	
	
}
