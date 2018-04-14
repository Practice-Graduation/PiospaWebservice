package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the service_price database table.
 * 
 */
@Entity
@Table(name="service_price")
@NamedQuery(name="ServicePrice.findAll", query="SELECT s FROM ServicePrice s")
public class ServicePrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="service_price_id")
	private int servicePriceId;

	@Column(name="all_price")
	private String allPrice;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="retail_price")
	private String retailPrice;

	@Column(name="service_group_id")
	private int serviceGroupId;

	@Column(name="service_id")
	private int serviceId;

	@Column(name="service_package_id")
	private int servicePackageId;

	@Column(name="service_type_id")
	private int serviceTypeId;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	public ServicePrice() {
	}

	public int getServicePriceId() {
		return this.servicePriceId;
	}

	public void setServicePriceId(int servicePriceId) {
		this.servicePriceId = servicePriceId;
	}

	public String getAllPrice() {
		return this.allPrice;
	}

	public void setAllPrice(String allPrice) {
		this.allPrice = allPrice;
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

	public String getRetailPrice() {
		return this.retailPrice;
	}

	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}

	public int getServiceGroupId() {
		return this.serviceGroupId;
	}

	public void setServiceGroupId(int serviceGroupId) {
		this.serviceGroupId = serviceGroupId;
	}

	public int getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getServicePackageId() {
		return this.servicePackageId;
	}

	public void setServicePackageId(int servicePackageId) {
		this.servicePackageId = servicePackageId;
	}

	public int getServiceTypeId() {
		return this.serviceTypeId;
	}

	public void setServiceTypeId(int serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
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