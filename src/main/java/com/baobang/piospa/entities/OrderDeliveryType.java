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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="order_delivery_type_code")
	private String orderDeliveryTypeCode;

	@Column(name="order_delivery_type_name")
	private String orderDeliveryTypeName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	//bi-directional many-to-one association to Order
	@JsonIgnore
	@OneToMany(mappedBy="orderDeliveryType")
	private List<Order> orders;

	public OrderDeliveryType() {
	}

	public int getOrderDeliveryTypeId() {
		return this.orderDeliveryTypeId;
	}

	public void setOrderDeliveryTypeId(int orderDeliveryTypeId) {
		this.orderDeliveryTypeId = orderDeliveryTypeId;
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

	public String getOrderDeliveryTypeCode() {
		return this.orderDeliveryTypeCode;
	}

	public void setOrderDeliveryTypeCode(String orderDeliveryTypeCode) {
		this.orderDeliveryTypeCode = orderDeliveryTypeCode;
	}

	public String getOrderDeliveryTypeName() {
		return this.orderDeliveryTypeName;
	}

	public void setOrderDeliveryTypeName(String orderDeliveryTypeName) {
		this.orderDeliveryTypeName = orderDeliveryTypeName;
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
		order.setOrderDeliveryType(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setOrderDeliveryType(null);

		return order;
	}

}