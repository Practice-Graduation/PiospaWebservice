package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the level_verb database table.
 * 
 */
@Entity
@Table(name="level_verb")
@NamedQuery(name="LevelVerb.findAll", query="SELECT l FROM LevelVerb l")
public class LevelVerb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="member_level_verb_id")
	private int memberLevelVerbId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="member_level_id")
	private int memberLevelId;

	@Column(name="member_level_verb_name")
	private String memberLevelVerbName;

	@Column(name="member_level_verb_point")
	private int memberLevelVerbPoint;

	@Column(name="order_price_max")
	private int orderPriceMax;

	@Column(name="order_price_min")
	private int orderPriceMin;

	@Column(name="product_number_max")
	private int productNumberMax;

	@Column(name="product_number_min")
	private int productNumberMin;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	public LevelVerb() {
	}

	public int getMemberLevelVerbId() {
		return this.memberLevelVerbId;
	}

	public void setMemberLevelVerbId(int memberLevelVerbId) {
		this.memberLevelVerbId = memberLevelVerbId;
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

	public int getMemberLevelId() {
		return this.memberLevelId;
	}

	public void setMemberLevelId(int memberLevelId) {
		this.memberLevelId = memberLevelId;
	}

	public String getMemberLevelVerbName() {
		return this.memberLevelVerbName;
	}

	public void setMemberLevelVerbName(String memberLevelVerbName) {
		this.memberLevelVerbName = memberLevelVerbName;
	}

	public int getMemberLevelVerbPoint() {
		return this.memberLevelVerbPoint;
	}

	public void setMemberLevelVerbPoint(int memberLevelVerbPoint) {
		this.memberLevelVerbPoint = memberLevelVerbPoint;
	}

	public int getOrderPriceMax() {
		return this.orderPriceMax;
	}

	public void setOrderPriceMax(int orderPriceMax) {
		this.orderPriceMax = orderPriceMax;
	}

	public int getOrderPriceMin() {
		return this.orderPriceMin;
	}

	public void setOrderPriceMin(int orderPriceMin) {
		this.orderPriceMin = orderPriceMin;
	}

	public int getProductNumberMax() {
		return this.productNumberMax;
	}

	public void setProductNumberMax(int productNumberMax) {
		this.productNumberMax = productNumberMax;
	}

	public int getProductNumberMin() {
		return this.productNumberMin;
	}

	public void setProductNumberMin(int productNumberMin) {
		this.productNumberMin = productNumberMin;
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