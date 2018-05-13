package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the services database table.
 * 
 */
@Entity
@Table(name="services")
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="service_id")
	private int serviceId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	private String description;

	private String detail;

	private String image;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="service_code")
	private String serviceCode;

	@Column(name="service_name")
	private String serviceName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	//bi-directional many-to-many association to ServicePackage
	@JsonIgnore
	@ManyToMany(mappedBy="services")
	private List<ServicePackage> servicePackages;

	//bi-directional many-to-one association to ServicePackageDetail
	@JsonIgnore
	@OneToMany(mappedBy="service")
	private List<ServicePackageDetail> servicePackageDetails;

	//bi-directional many-to-one association to ServicePrice
	@JsonIgnore
	@OneToMany(mappedBy="service")
	private List<ServicePrice> servicePrices;

	//bi-directional many-to-one association to ServiceTime
	@ManyToOne
	@JoinColumn(name="service_time_id")
	private ServiceTime serviceTime;

	public Service() {
	}

	public int getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

	public List<ServicePackage> getServicePackages() {
		return this.servicePackages;
	}

	public void setServicePackages(List<ServicePackage> servicePackages) {
		this.servicePackages = servicePackages;
	}

	public List<ServicePackageDetail> getServicePackageDetails() {
		return this.servicePackageDetails;
	}

	public void setServicePackageDetails(List<ServicePackageDetail> servicePackageDetails) {
		this.servicePackageDetails = servicePackageDetails;
	}

	public ServicePackageDetail addServicePackageDetail(ServicePackageDetail servicePackageDetail) {
		getServicePackageDetails().add(servicePackageDetail);
		servicePackageDetail.setService(this);

		return servicePackageDetail;
	}

	public ServicePackageDetail removeServicePackageDetail(ServicePackageDetail servicePackageDetail) {
		getServicePackageDetails().remove(servicePackageDetail);
		servicePackageDetail.setService(null);

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
		servicePrice.setService(this);

		return servicePrice;
	}

	public ServicePrice removeServicePrice(ServicePrice servicePrice) {
		getServicePrices().remove(servicePrice);
		servicePrice.setService(null);

		return servicePrice;
	}

	public ServiceTime getServiceTime() {
		return this.serviceTime;
	}

	public void setServiceTime(ServiceTime serviceTime) {
		this.serviceTime = serviceTime;
	}

}