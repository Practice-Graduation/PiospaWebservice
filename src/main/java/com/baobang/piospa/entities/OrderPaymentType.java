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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="order_payment_type_description")
	private String orderPaymentTypeDescription;

	@Column(name="order_payment_type_name")
	private String orderPaymentTypeName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

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

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public String getOrderPaymentTypeDescription() {
		return this.orderPaymentTypeDescription;
	}

	public void setOrderPaymentTypeDescription(String orderPaymentTypeDescription) {
		this.orderPaymentTypeDescription = orderPaymentTypeDescription;
	}

	public String getOrderPaymentTypeName() {
		return this.orderPaymentTypeName;
	}

	public void setOrderPaymentTypeName(String orderPaymentTypeName) {
		this.orderPaymentTypeName = orderPaymentTypeName;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
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