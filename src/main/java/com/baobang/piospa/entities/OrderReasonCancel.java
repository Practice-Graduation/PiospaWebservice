package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the order_reason_cancel database table.
 * 
 */
@Entity
@Table(name="order_reason_cancel")
@NamedQuery(name="OrderReasonCancel.findAll", query="SELECT o FROM OrderReasonCancel o")
public class OrderReasonCancel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_reason_cancel_id")
	private int orderReasonCancelId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="order_reason_cancel_descripton")
	private String orderReasonCancelDescripton;

	@Column(name="order_reason_cancel_name")
	private String orderReasonCancelName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="orderReasonCancel")
	private List<Order> orders;

	public OrderReasonCancel() {
	}

	public int getOrderReasonCancelId() {
		return this.orderReasonCancelId;
	}

	public void setOrderReasonCancelId(int orderReasonCancelId) {
		this.orderReasonCancelId = orderReasonCancelId;
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

	public String getOrderReasonCancelDescripton() {
		return this.orderReasonCancelDescripton;
	}

	public void setOrderReasonCancelDescripton(String orderReasonCancelDescripton) {
		this.orderReasonCancelDescripton = orderReasonCancelDescripton;
	}

	public String getOrderReasonCancelName() {
		return this.orderReasonCancelName;
	}

	public void setOrderReasonCancelName(String orderReasonCancelName) {
		this.orderReasonCancelName = orderReasonCancelName;
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
		order.setOrderReasonCancel(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setOrderReasonCancel(null);

		return order;
	}

}