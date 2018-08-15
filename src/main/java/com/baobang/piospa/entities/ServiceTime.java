package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the service_time database table.
 * 
 */
@Entity
@Table(name="service_time")
@NamedQuery(name="ServiceTime.findAll", query="SELECT s FROM ServiceTime s")
public class ServiceTime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="service_time_id")
	private int serviceTimeId;

	private String time;

	//bi-directional many-to-one association to Service
	@JsonIgnore
	@OneToMany(mappedBy="serviceTime")
	private List<Service> services;

	public ServiceTime() {
	}

	public int getServiceTimeId() {
		return this.serviceTimeId;
	}

	public void setServiceTimeId(int serviceTimeId) {
		this.serviceTimeId = serviceTimeId;
	}

	

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public List<Service> getServices() {
		return this.services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Service addService(Service service) {
		getServices().add(service);
		service.setServiceTime(this);

		return service;
	}

	public Service removeService(Service service) {
		getServices().remove(service);
		service.setServiceTime(null);

		return service;
	}

}