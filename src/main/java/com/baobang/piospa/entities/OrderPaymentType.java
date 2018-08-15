package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the order_payment_type database table.
 * 
 */
@Entity
@Table(name="order_payment_type")
@NamedQuery(name="OrderPaymentType.findAll", query="SELECT o FROM OrderPaymentType o")
public class OrderPaymentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_payment_type_id")
	private int orderPaymentTypeId;

	@Column(name="order_payment_type_name")
	private String orderPaymentTypeName;

	//bi-directional many-to-one association to Order
	@JsonIgnore
	@OneToMany(mappedBy="orderPaymentType")
	private List<Order> orders;

	public OrderPaymentType() {
	}

	public int getOrderPaymentTypeId() {
		return this.orderPaymentTypeId;
	}

	public void setOrderPaymentTypeId(int orderPaymentTypeId) {
		this.orderPaymentTypeId = orderPaymentTypeId;
	}



	public String getOrderPaymentTypeName() {
		return this.orderPaymentTypeName;
	}

	public void setOrderPaymentTypeName(String orderPaymentTypeName) {
		this.orderPaymentTypeName = orderPaymentTypeName;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setOrderPaymentType(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setOrderPaymentType(null);

		return order;
	}

}