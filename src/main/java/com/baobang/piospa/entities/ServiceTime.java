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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	private String time;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

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

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
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