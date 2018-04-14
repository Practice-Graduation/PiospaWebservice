package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the customer_group database table.
 * 
 */
@Entity
@Table(name="customer_group")
@NamedQuery(name="CustomerGroup.findAll", query="SELECT c FROM CustomerGroup c")
public class CustomerGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="customer_group_id")
	private int customerGroupId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="customer_group_code")
	private String customerGroupCode;

	@Lob
	@Column(name="customer_group_description")
	private String customerGroupDescription;

	@Column(name="customer_group_image")
	private String customerGroupImage;

	@Column(name="customer_group_name")
	private String customerGroupName;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	public CustomerGroup() {
	}

	public int getCustomerGroupId() {
		return this.customerGroupId;
	}

	public void setCustomerGroupId(int customerGroupId) {
		this.customerGroupId = customerGroupId;
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

	public String getCustomerGroupCode() {
		return this.customerGroupCode;
	}

	public void setCustomerGroupCode(String customerGroupCode) {
		this.customerGroupCode = customerGroupCode;
	}

	public String getCustomerGroupDescription() {
		return this.customerGroupDescription;
	}

	public void setCustomerGroupDescription(String customerGroupDescription) {
		this.customerGroupDescription = customerGroupDescription;
	}

	public String getCustomerGroupImage() {
		return this.customerGroupImage;
	}

	public void setCustomerGroupImage(String customerGroupImage) {
		this.customerGroupImage = customerGroupImage;
	}

	public String getCustomerGroupName() {
		return this.customerGroupName;
	}

	public void setCustomerGroupName(String customerGroupName) {
		this.customerGroupName = customerGroupName;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
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