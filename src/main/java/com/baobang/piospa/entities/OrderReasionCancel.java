package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the order_reasion_cancel database table.
 * 
 */
@Entity
@Table(name="order_reasion_cancel")
@NamedQuery(name="OrderReasionCancel.findAll", query="SELECT o FROM OrderReasionCancel o")
public class OrderReasionCancel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="order_reason_cancel_id")
	private int orderReasonCancelId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="order_reason_cancel_descripton")
	private String orderReasonCancelDescripton;

	@Column(name="order_reason_cancel_name")
	private String orderReasonCancelName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	public OrderReasionCancel() {
	}

	public int getOrderReasonCancelId() {
		return this.orderReasonCancelId;
	}

	public void setOrderReasonCancelId(int orderReasonCancelId) {
		this.orderReasonCancelId = orderReasonCancelId;
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