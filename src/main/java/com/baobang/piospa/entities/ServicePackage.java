package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the service_package database table.
 * 
 */
@Entity
@Table(name="service_package")
@NamedQuery(name="ServicePackage.findAll", query="SELECT s FROM ServicePackage s")
public class ServicePackage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="service_package_id")
	private int servicePackageId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="service_package_name")
	private String servicePackageName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	//bi-directional many-to-one association to ServicePrice
	@JsonIgnore
	@OneToMany(mappedBy="servicePackage")
	private List<ServicePrice> servicePrices;

	public ServicePackage() {
	}

	public int getServicePackageId() {
		return this.servicePackageId;
	}

	public void setServicePackageId(int servicePackageId) {
		this.servicePackageId = servicePackageId;
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

	public String getServicePackageName() {
		return this.servicePackageName;
	}

	public void setServicePackageName(String servicePackageName) {
		this.servicePackageName = servicePackageName;
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

	public List<ServicePrice> getServicePrices() {
		return this.servicePrices;
	}

	public void setServicePrices(List<ServicePrice> servicePrices) {
		this.servicePrices = servicePrices;
	}

	public ServicePrice addServicePrice(ServicePrice servicePrice) {
		getServicePrices().add(servicePrice);
		servicePrice.setServicePackage(this);

		return servicePrice;
	}

	public ServicePrice removeServicePrice(ServicePrice servicePrice) {
		getServicePrices().remove(servicePrice);
		servicePrice.setServicePackage(null);

		return servicePrice;
	}

}