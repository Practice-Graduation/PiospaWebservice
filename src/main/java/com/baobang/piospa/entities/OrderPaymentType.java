package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


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
	@Column(name="order_payment_type_id")
	private int orderPaymentTypeId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="order_payment_type_description")
	private String orderPaymentTypeDescription;

	@Column(name="order_payment_type_name")
	private String orderPaymentTypeName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	public OrderPaymentType() {
	}

	public int getOrderPaymentTypeId() {
		return this.orderPaymentTypeId;
	}

	public void setOrderPaymentTypeId(int orderPaymentTypeId) {
		this.orderPaymentTypeId = orderPaymentTypeId;
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