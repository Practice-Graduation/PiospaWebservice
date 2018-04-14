package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the product_origin database table.
 * 
 */
@Entity
@Table(name="product_origin")
@NamedQuery(name="ProductOrigin.findAll", query="SELECT p FROM ProductOrigin p")
public class ProductOrigin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="product_origin_id")
	private int productOriginId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="product_origin_description")
	private String productOriginDescription;

	@Column(name="product_origin_name")
	private String productOriginName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	public ProductOrigin() {
	}

	public int getProductOriginId() {
		return this.productOriginId;
	}

	public void setProductOriginId(int productOriginId) {
		this.productOriginId = productOriginId;
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

	public String getProductOriginDescription() {
		return this.productOriginDescription;
	}

	public void setProductOriginDescription(String productOriginDescription) {
		this.productOriginDescription = productOriginDescription;
	}

	public String getProductOriginName() {
		return this.productOriginName;
	}

	public void setProductOriginName(String productOriginName) {
		this.productOriginName = productOriginName;
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