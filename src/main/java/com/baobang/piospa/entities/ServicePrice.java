package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="service_price_id")
	private int servicePriceId;

	@Column(name="all_price")
	private String allPrice;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="retail_price")
	private String retailPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	//bi-directional many-to-one association to Booking
	@JsonIgnore
	@OneToMany(mappedBy="servicePrice")
	private List<Booking> bookings;

	//bi-directional many-to-one association to ServiceGroup
	@ManyToOne
	@JoinColumn(name="service_group_id")
	private ServiceGroup serviceGroup;

	//bi-directional many-to-one association to ServicePackage
	@ManyToOne
	@JoinColumn(name="service_package_id")
	private ServicePackage servicePackage;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="service_id")
	private Service service;

	//bi-directional many-to-one association to ServiceType
	@ManyToOne
	@JoinColumn(name="service_type_id")
	private ServiceType serviceType;

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

	public String getRetailPrice() {
		return this.retailPrice;
	}

	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
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

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setServicePrice(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setServicePrice(null);

		return booking;
	}

	public ServiceGroup getServiceGroup() {
		return this.serviceGroup;
	}

	public void setServiceGroup(ServiceGroup serviceGroup) {
		this.serviceGroup = serviceGroup;
	}

	public ServicePackage getServicePackage() {
		return this.servicePackage;
	}

	public void setServicePackage(ServicePackage servicePackage) {
		this.servicePackage = servicePackage;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public ServiceType getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

}