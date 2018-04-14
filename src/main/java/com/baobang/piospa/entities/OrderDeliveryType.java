package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


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
	@Column(name="order_delivery_type_id")
	private int orderDeliveryTypeId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="order_delivery_type_code")
	private String orderDeliveryTypeCode;

	@Column(name="order_delivery_type_name")
	private String orderDeliveryTypeName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	public OrderDeliveryType() {
	}

	public int getOrderDeliveryTypeId() {
		return this.orderDeliveryTypeId;
	}

	public void setOrderDeliveryTypeId(int orderDeliveryTypeId) {
		this.orderDeliveryTypeId = orderDeliveryTypeId;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
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

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

}