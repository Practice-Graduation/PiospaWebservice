package com.baobang.piospa.model;

import com.baobang.piospa.entities.OrderProduct;

/**
  * @author BaoBang
  * @Created Jul 28, 2018
  * 
  */
public class OrderProductObject {

	private int productId;
	private String productName;
	private String productImage;
	private int price;
	private int amount;
	
	public OrderProductObject() {
		super();
	}
	public OrderProductObject(int productId, String productName, String productImage, int price, int amount) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productImage = productImage;
		this.price = price;
		this.amount = amount;
	}
	public OrderProductObject(OrderProduct orderProduct) {
		// TODO Auto-generated constructor stub
		productId = orderProduct.getProduct().getProductId();
		productName = orderProduct.getProduct().getProductName();
		productImage = orderProduct.getProduct().getImage();
		price = orderProduct.getPrice();
		amount = orderProduct.getNumber();
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
