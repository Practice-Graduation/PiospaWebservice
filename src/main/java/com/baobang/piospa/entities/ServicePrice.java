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
@Table(name="service_price")
@NamedQuery(name="ServicePrice.findAll", query="SELECT s FROM ServicePrice s")
public class ServicePrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="service_price_id")
	private int servicePriceId;

	@Column(name="all_price")
	private int allPrice;
	
	@Column(name="is_active")
	private int isActive;

	
	//bi-directional many-to-one association to BookingDetail
	@JsonIgnore
	@OneToMany(mappedBy="servicePrice")
	private List<BookingDetail> bookingDetails;


	//bi-directional many-to-one association to ServiceGroup
	@ManyToOne
	@JoinColumn(name="service_group_id")
	private ServiceGroup serviceGroup;

	//bi-directional many-to-one association to ServicePackage
	@ManyToOne
	@JoinColumn(name="service_package_id")
	private ServicePackage servicePackage;

	//bi-directional many-to-one association to ServiceType
	@ManyToOne
	@JoinColumn(name="service_type_id")
	private ServiceType serviceType;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="service_id")
	private Service service;

	public ServicePrice() {
	}
	
	

	public int getIsActive() {
		return isActive;
	}



	public void setIsActive(int isActive) {
		this.isActive = isActive;
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

	public ServiceType getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}