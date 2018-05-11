package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the service_price database table.
 * 
 */
@Entity
@Table(name = "service_price")
@NamedQuery(name = "ServicePrice.findAll", query = "SELECT s FROM ServicePrice s")
public class ServicePrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_price_id")
	private int servicePriceId;

	@Column(name = "all_price")
	private int allPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "is_active")
	private byte isActive;

	@Column(name = "retail_price")
	private int retailPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "updated_by")
	private int updatedBy;

	// bi-directional many-to-one association to ServiceType
	@ManyToOne
	@JoinColumn(name = "service_type_id")
	private ServiceType serviceType;

	// bi-directional many-to-one association to BookingDetail
	@JsonIgnore
	@OneToMany(mappedBy = "servicePrice")
	private List<BookingDetail> bookingDetails;

	// bi-directional many-to-many association to Booking
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "booking_detail", joinColumns = { @JoinColumn(name = "service_price_id") }, inverseJoinColumns = {
			@JoinColumn(name = "booking_id") })
	private List<Booking> bookings1;

	// bi-directional many-to-one association to ServicePackage
	@ManyToOne
	@JoinColumn(name = "service_package_id")
	private ServicePackage servicePackage;

	// bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;

	// bi-directional many-to-many association to Booking
	@JsonIgnore
	@ManyToMany(mappedBy = "servicePrices2")
	private List<Booking> bookings2;

	public ServicePrice() {
	}

	public int getServicePriceId() {
		return this.servicePriceId;
	}

	public void setServicePriceId(int servicePriceId) {
		this.servicePriceId = servicePriceId;
	}

	public int getAllPrice() {
		return this.allPrice;
	}

	public void setAllPrice(int allPrice) {
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

	public int getRetailPrice() {
		return this.retailPrice;
	}

	public void setRetailPrice(int retailPrice) {
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

	public List<BookingDetail> getBookingDetails() {
		return this.bookingDetails;
	}

	public void setBookingDetails(List<BookingDetail> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	public BookingDetail addBookingDetail(BookingDetail bookingDetail) {
		getBookingDetails().add(bookingDetail);
		bookingDetail.setServicePrice(this);

		return bookingDetail;
	}

	public BookingDetail removeBookingDetail(BookingDetail bookingDetail) {
		getBookingDetails().remove(bookingDetail);
		bookingDetail.setServicePrice(null);

		return bookingDetail;
	}

	public List<Booking> getBookings1() {
		return this.bookings1;
	}

	public void setBookings1(List<Booking> bookings1) {
		this.bookings1 = bookings1;
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

	public List<Booking> getBookings2() {
		return this.bookings2;
	}

	public void setBookings2(List<Booking> bookings2) {
		this.bookings2 = bookings2;
	}
	
	public ServiceType getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

}