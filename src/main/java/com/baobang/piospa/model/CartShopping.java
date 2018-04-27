package com.baobang.piospa.model;

import java.util.List;

/**
  * @author BaoBang
  * @Created Apr 26, 2018
  * 
  */
public class CartShopping {

	List<CartItem> cartItems;

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public CartShopping(List<CartItem> cartItems) {
		super();
		this.cartItems = cartItems;
	}

	public CartShopping() {
		super();
	}
	
	
	
}
