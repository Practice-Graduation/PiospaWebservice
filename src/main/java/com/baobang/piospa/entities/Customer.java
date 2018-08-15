package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Date;
import java.util.List;

/**
 * The persistent class for the customers database table.
 * 
 */
@Entity
@Table(name = "customers")
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;

	private String account;

	private String address;

	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;


	@Column(name = "customer_avatar")
	private String customerAvatar;


	// bi-directional many-to-one association to District
	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district;
	private String email;
	
	private String fullname;

	private String gender;

	@Column(name = "is_active")
	private byte isActive;


	private String password;

	private String phone;

	// bi-directional many-to-one association to Province
	@ManyToOne
	@JoinColumn(name = "provinces_id")
	private Province province;

	// bi-directional many-to-one association to Ward
	@ManyToOne
	@JoinColumn(name = "ward_id")
	private Ward ward;

	// bi-directional many-to-one association to Booking
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Booking> bookings;


	// bi-directional many-to-one association to Order
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;

	public Customer() {
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	
	public String getCustomerAvatar() {
		return this.customerAvatar;
	}

	public void setCustomerAvatar(String customerAvatar) {
		this.customerAvatar = customerAvatar;
	}

	
	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}
	
	public Ward getWard() {
		return this.ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setCustomer(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setCustomer(null);

		return booking;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setCustomer(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setCustomer(null);

		return order;
	}

}