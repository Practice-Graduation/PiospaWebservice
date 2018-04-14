package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the product_unit database table.
 * 
 */
@Entity
@Table(name="product_unit")
@NamedQuery(name="ProductUnit.findAll", query="SELECT p FROM ProductUnit p")
public class ProductUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="product_unit_id")
	private int productUnitId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="product_unit_description")
	private String productUnitDescription;

	@Column(name="product_unit_name")
	private String productUnitName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	public ProductUnit() {
	}

	public int getProductUnitId() {
		return this.productUnitId;
	}

	public void setProductUnitId(int productUnitId) {
		this.productUnitId = productUnitId;
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

	public String getProductUnitDescription() {
		return this.productUnitDescription;
	}

	public void setProductUnitDescription(String productUnitDescription) {
		this.productUnitDescription = productUnitDescription;
	}

	public String getProductUnitName() {
		return this.productUnitName;
	}

	public void setProductUnitName(String productUnitName) {
		this.productUnitName = productUnitName;
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