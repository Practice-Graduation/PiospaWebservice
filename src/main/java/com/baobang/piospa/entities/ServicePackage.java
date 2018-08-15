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

	private String image;
	
	private int time;
	

	@Column(name="is_active")
	private int isActive;
	

	@Column(name="service_package_name")
	private String servicePackageName;

	//bi-directional many-to-many association to Service
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="service_package_detail"
		, joinColumns={
			@JoinColumn(name="service_package_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="service_id")
			}
		)
	private List<Service> services;

	//bi-directional many-to-one association to ServicePackageDetail
	@JsonIgnore
	@OneToMany(mappedBy="servicePackage")
	private List<ServicePackageDetail> servicePackageDetails;

	//bi-directional many-to-one association to ServicePrice
	@JsonIgnore
	@OneToMany(mappedBy="servicePackage")
	private List<ServicePrice> servicePrices;

	public ServicePackage() {
	}

	public int getIsActive() {
		return isActive;
	}




	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}




	public int getServicePackageId() {
		return this.servicePackageId;
	}

	public void setServicePackageId(int servicePackageId) {
		this.servicePackageId = servicePackageId;
	}

	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getServicePackageName() {
		return this.servicePackageName;
	}

	public void setServicePackageName(String servicePackageName) {
		this.servicePackageName = servicePackageName;
	}
	public List<Service> getServices() {
		return this.services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<ServicePackageDetail> getServicePackageDetails() {
		return this.servicePackageDetails;
	}

	public void setServicePackageDetails(List<ServicePackageDetail> servicePackageDetails) {
		this.servicePackageDetails = servicePackageDetails;
	}

	public ServicePackageDetail addServicePackageDetail(ServicePackageDetail servicePackageDetail) {
		getServicePackageDetails().add(servicePackageDetail);
		servicePackageDetail.setServicePackage(this);

		return servicePackageDetail;
	}

	public ServicePackageDetail removeServicePackageDetail(ServicePackageDetail servicePackageDetail) {
		getServicePackageDetails().remove(servicePackageDetail);
		servicePackageDetail.setServicePackage(null);

		return servicePackageDetail;
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