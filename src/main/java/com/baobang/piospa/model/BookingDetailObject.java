package com.baobang.piospa.model;


/**
  * @author BaoBang
  * @Created Jul 28, 2018
  * 
  */
public class BookingDetailObject {

	private String serviceName;
	private String serviceImage;
	private String dateBooking;
	private String timeBooking;
	private int numberCustomer;
	private int price;
	

	public BookingDetailObject(String serviceName, String serviceImage, String dateBooking, String timeBooking,
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
	public String getDateBooking() {
		return dateBooking;
	}
	public void setDateBooking(String dateBooking) {
		this.dateBooking = dateBooking;
	}
	public String getTimeBooking() {
		return timeBooking;
	}
	public void setTimeBooking(String timeBooking) {
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
