package com.baobang.piospa.model;
/**
  * @author BaoBang
  * @Created Apr 26, 2018
  * 
  */
public class CartItem {

	private int productId;
	private int number;
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
	public CartItem(int productId, int number) {
		super();
		this.productId = productId;
		this.number = number;
	}
	public CartItem() {
		super();
	}
	
	
}
