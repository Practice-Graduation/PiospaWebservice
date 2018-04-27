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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="is_delete")
	private byte isDelete;

	@Column(name="order_delivery_status_description")
	private String orderDeliveryStatusDescription;

	@Column(name="order_delivery_status_name")
	private String orderDeliveryStatusName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

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

	public byte getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(byte isDelete) {
		this.isDelete = isDelete;
	}

	public String getOrderDeliveryStatusDescription() {
		return this.orderDeliveryStatusDescription;
	}

	public void setOrderDeliveryStatusDescription(String orderDeliveryStatusDescription) {
		this.orderDeliveryStatusDescription = orderDeliveryStatusDescription;
	}

	public String getOrderDeliveryStatusName() {
		return this.orderDeliveryStatusName;
	}

	public void setOrderDeliveryStatusName(String orderDeliveryStatusName) {
		this.orderDeliveryStatusName = orderDeliveryStatusName;
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

//	public Order addOrder(Order order) {
//		getOrders().add(order);
//		order.setOrderDeliveryStatus(this);
//
//		return order;
//	}
//
//	public Order removeOrder(Order order) {
//		getOrders().remove(order);
//		order.setOrderDeliveryStatus(null);
//
//		return order;
//	}

}