package com.baobang.piospa.model;

import java.util.List;

/**
  * @author BaoBang
  * @Created Apr 26, 2018
  * 
  */
public class CartShopping {

	private List<CartItemProduct> cartItemProducts;
	private List<CartItemService> cartItemServices;
	public List<CartItemProduct> getCartItemProducts() {
		return cartItemProducts;
	}
	public void setCartItemProducts(List<CartItemProduct> cartItemProducts) {
		this.cartItemProducts = cartItemProducts;
	}
	public List<CartItemService> getCartItemServices() {
		return cartItemServices;
	}
	public void setCartItemServices(List<CartItemService> cartItemServices) {
		this.cartItemServices = cartItemServices;
	}
	public CartShopping(List<CartItemProduct> cartItemProducts, List<CartItemService> cartItemServices) {
		super();
		this.cartItemProducts = cartItemProducts;
		this.cartItemServices = cartItemServices;
	}
	public CartShopping() {
		super();
	}
	
	
	
}
