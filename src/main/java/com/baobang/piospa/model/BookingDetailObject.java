package com.baobang.piospa.model;

import java.sql.Time;
import java.util.Date;

import com.baobang.piospa.entities.BookingDetail;

/**
  * @author BaoBang
  * @Created Jul 28, 2018
  * 
  */
public class BookingDetailObject {

	private String serviceName;
	private String serviceImage;
	private Date dateBooking;
	private Time timeBooking;
	private int numberCustomer;
	private int price;
	

	public BookingDetailObject(String serviceName, String serviceImage, Date dateBooking, Time timeBooking,
			int numberCustomer, int price) {
		super();
		this.serviceName = serviceName;
		this.serviceImage = serviceImage;
		this.dateBooking = dateBooking;
		this.timeBooking = timeBooking;
		this.numberCustomer = numberCustomer;
		this.price = price;
	}
	public BookingDetailObject() {
		super();
	}
	public BookingDetailObject(BookingDetail detail) {
		// TODO Auto-generated constructor stub
		if(detail.getServicePrice().getService() != null) {
			serviceName = detail.getServicePrice().getService().getServiceName();
			serviceImage = detail.getServicePrice().getService().getImage();
		}else if (detail.getServicePrice().getServicePackage() != null) {
			serviceName = detail.getServicePrice().getServicePackage().getServicePackageName();
			serviceImage = detail.getServicePrice().getServicePackage().getImage();
		}
		dateBooking = detail.getDateBooking();
		timeBooking = detail.getTimeStart();
		
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceImage() {
		return serviceImage;
	}
	public void setServiceImage(String serviceImage) {
		this.serviceImage = serviceImage;
	}
	public Date getDateBooking() {
		return dateBooking;
	}
	public void setDateBooking(Date dateBooking) {
		this.dateBooking = dateBooking;
	}
	public Time getTimeBooking() {
		return timeBooking;
	}
	public void setTimeBooking(Time timeBooking) {
		this.timeBooking = timeBooking;
	}
	public int getNumberCustomer() {
		return numberCustomer;
	}
	public void setNumberCustomer(int numberCustomer) {
		this.numberCustomer = numberCustomer;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
