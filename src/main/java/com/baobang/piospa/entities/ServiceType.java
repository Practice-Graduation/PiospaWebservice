package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the service_type database table.
 * 
 */
@Entity
@Table(name="service_type")
@NamedQuery(name="ServiceType.findAll", query="SELECT s FROM ServiceType s")
public class ServiceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="service_type_id")
	private int serviceTypeId;


	@Column(name="service_type_name")
	private String serviceTypeName;

	
	//bi-directional many-to-one association to ServicePrice
	@JsonIgnore
	@OneToMany(mappedBy="serviceType")
	private List<ServicePrice> servicePrices;

	public ServiceType() {
	}

	public int getServiceTypeId() {
		return this.serviceTypeId;
	}

	public void setServiceTypeId(int serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	
	public String getServiceTypeName() {
		return this.serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}


	public List<ServicePrice> getServicePrices() {
		return this.servicePrices;
	}

	public void setServicePrices(List<ServicePrice> servicePrices) {
		this.servicePrices = servicePrices;
	}

	public ServicePrice addServicePrice(ServicePrice servicePrice) {
		getServicePrices().add(servicePrice);
		servicePrice.setServiceType(this);

		return servicePrice;
	}

	public ServicePrice removeServicePrice(ServicePrice servicePrice) {
		getServicePrices().remove(servicePrice);
		servicePrice.setServiceType(null);

		return servicePrice;
	}

}