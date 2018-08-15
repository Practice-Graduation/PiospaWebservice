package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the service_group database table.
 * 
 */
@Entity
@Table(name="service_group")
@NamedQuery(name="ServiceGroup.findAll", query="SELECT s FROM ServiceGroup s")
public class ServiceGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="service_group_id")
	private int serviceGroupId;

	@Column(name="service_group_name")
	private String serviceGroupName;

	
	//bi-directional many-to-one association to ServicePrice
	@JsonIgnore
	@OneToMany(mappedBy="serviceGroup", fetch = FetchType.EAGER)
	private List<ServicePrice> servicePrices;

	public ServiceGroup() {
	}

	public int getServiceGroupId() {
		return this.serviceGroupId;
	}

	public void setServiceGroupId(int serviceGroupId) {
		this.serviceGroupId = serviceGroupId;
	}

	
	public String getServiceGroupName() {
		return this.serviceGroupName;
	}

	public void setServiceGroupName(String serviceGroupName) {
		this.serviceGroupName = serviceGroupName;
	}

	public List<ServicePrice> getServicePrices() {
		return this.servicePrices;
	}

	public void setServicePrices(List<ServicePrice> servicePrices) {
		this.servicePrices = servicePrices;
	}

	public ServicePrice addServicePrice(ServicePrice servicePrice) {
		getServicePrices().add(servicePrice);
		servicePrice.setServiceGroup(this);

		return servicePrice;
	}

	public ServicePrice removeServicePrice(ServicePrice servicePrice) {
		getServicePrices().remove(servicePrice);
		servicePrice.setServiceGroup(null);

		return servicePrice;
	}

}