package com.baobang.piospa.model;

import java.util.List;

/**
  * @author BaoBang
  * @Created Apr 26, 2018
  * 
  */
public class CartShopping {

	private List<CartItemProduct> cartItemProducts;
	public List<CartItemProduct> getCartItemProducts() {
		return cartItemProducts;
	}
	public void setCartItemProducts(List<CartItemProduct> cartItemProducts) {
		this.cartItemProducts = cartItemProducts;
	}
	
	public CartShopping(List<CartItemProduct> cartItemProducts) {
		super();
		this.cartItemProducts = cartItemProducts;
	}
	public CartShopping() {
		super();
	}
	
	
	
}
