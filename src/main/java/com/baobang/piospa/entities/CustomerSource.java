package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the customer_source database table.
 * 
 */
@Entity
@Table(name="customer_source")
@NamedQuery(name="CustomerSource.findAll", query="SELECT c FROM CustomerSource c")
public class CustomerSource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="customer_source_id")
	private int customerSourceId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="customer_source_code")
	private String customerSourceCode;

	@Lob
	@Column(name="customer_source_description")
	private String customerSourceDescription;

	@Column(name="customer_source_name")
	private String customerSourceName;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	public CustomerSource() {
	}

	public int getCustomerSourceId() {
		return this.customerSourceId;
	}

	public void setCustomerSourceId(int customerSourceId) {
		this.customerSourceId = customerSourceId;
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

	public String getCustomerSourceCode() {
		return this.customerSourceCode;
	}

	public void setCustomerSourceCode(String customerSourceCode) {
		this.customerSourceCode = customerSourceCode;
	}

	public String getCustomerSourceDescription() {
		return this.customerSourceDescription;
	}

	public void setCustomerSourceDescription(String customerSourceDescription) {
		this.customerSourceDescription = customerSourceDescription;
	}

	public String getCustomerSourceName() {
		return this.customerSourceName;
	}

	public void setCustomerSourceName(String customerSourceName) {
		this.customerSourceName = customerSourceName;
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