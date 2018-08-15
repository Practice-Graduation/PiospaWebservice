package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the order_delivery_status database table.
 * 
 */
@Entity
@Table(name="order_delivery_status")
@NamedQuery(name="OrderDeliveryStatus.findAll", query="SELECT o FROM OrderDeliveryStatus o")
public class OrderDeliveryStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_delivery_status_id")
	private int orderDeliveryStatusId;
	

	@Column(name="order_delivery_status_name")
	private String orderDeliveryStatusName;

	//bi-directional many-to-one association to Order
	@JsonIgnore
	@OneToMany(mappedBy="orderDeliveryStatus")
	private List<Order> orders;

	public OrderDeliveryStatus() {
	}

	public int getOrderDeliveryStatusId() {
		return this.orderDeliveryStatusId;
	}

	public void setOrderDeliveryStatusId(int orderDeliveryStatusId) {
		this.orderDeliveryStatusId = orderDeliveryStatusId;
	}

	
	public String getOrderDeliveryStatusName() {
		return this.orderDeliveryStatusName;
	}

	public void setOrderDeliveryStatusName(String orderDeliveryStatusName) {
		this.orderDeliveryStatusName = orderDeliveryStatusName;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setOrderDeliveryStatus(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setOrderDeliveryStatus(null);

		return order;
	}

}