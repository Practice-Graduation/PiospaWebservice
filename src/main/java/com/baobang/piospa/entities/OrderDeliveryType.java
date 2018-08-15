package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Date;
import java.util.List;


/**
 * The persistent class for the order_delivery_type database table.
 * 
 */
@Entity
@Table(name="order_delivery_type")
@NamedQuery(name="OrderDeliveryType.findAll", query="SELECT o FROM OrderDeliveryType o")
public class OrderDeliveryType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_delivery_type_id")
	private int orderDeliveryTypeId;

	@Column(name="order_delivery_type_name")
	private String orderDeliveryTypeName;

	@Column(name="order_delivery_type_price")
	private int price;
	
	//bi-directional many-to-one association to Order
	@JsonIgnore
	@OneToMany(mappedBy="orderDeliveryType")
	private List<Order> orders;

	public OrderDeliveryType() {
	}

	public int getOrderDeliveryTypeId() {
		return this.orderDeliveryTypeId;
	}
	
	

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setOrderDeliveryTypeId(int orderDeliveryTypeId) {
		this.orderDeliveryTypeId = orderDeliveryTypeId;
	}

	

	public String getOrderDeliveryTypeName() {
		return this.orderDeliveryTypeName;
	}

	public void setOrderDeliveryTypeName(String orderDeliveryTypeName) {
		this.orderDeliveryTypeName = orderDeliveryTypeName;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setOrderDeliveryType(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setOrderDeliveryType(null);

		return order;
	}

}