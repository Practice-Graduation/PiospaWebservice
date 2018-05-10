package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customers database table.
 * 
 */
@Entity
@Table(name="customers")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int customerId;

	private String account;

	private String address;

	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;

	private String cmnd;

	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="cusomer_refer_id")
	private int cusomerReferId;

	@Column(name="customer_avatar")
	private String customerAvatar;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_last_login")
	private Date dateLastLogin;

	@Column(name="district_id")
	private int districtId;

	private String email;

	private String facebook;

	@Column(name="facebook_id")
	private int facebookId;

	private String fullname;

	private String gender;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="is_member")
	private byte isMember;

	@Column(name="member_code")
	private String memberCode;

	private String password;

	private String phone;

	private int point;

	@Column(name="point_used")
	private int pointUsed;

	@Column(name="provinces_id")
	private int provincesId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	@Column(name="ward_id")
	private int wardId;

	private String zalo;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="customer")
	private List<Booking> bookings;

	//bi-directional many-to-one association to CustomerSource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_source_id")
	private CustomerSource customerSource;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="customer")
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

	public String getCmnd() {
		return this.cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public int getCusomerReferId() {
		return this.cusomerReferId;
	}

	public void setCusomerReferId(int cusomerReferId) {
		this.cusomerReferId = cusomerReferId;
	}

	public String getCustomerAvatar() {
		return this.customerAvatar;
	}

	public void setCustomerAvatar(String customerAvatar) {
		this.customerAvatar = customerAvatar;
	}

	public Date getDateLastLogin() {
		return this.dateLastLogin;
	}

	public void setDateLastLogin(Date dateLastLogin) {
		this.dateLastLogin = dateLastLogin;
	}

	public int getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return this.facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public int getFacebookId() {
		return this.facebookId;
	}

	public void setFacebookId(int facebookId) {
		this.facebookId = facebookId;
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

	public byte getIsMember() {
		return this.isMember;
	}

	public void setIsMember(byte isMember) {
		this.isMember = isMember;
	}

	public String getMemberCode() {
		return this.memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
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

	public int getPoint() {
		return this.point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getPointUsed() {
		return this.pointUsed;
	}

	public void setPointUsed(int pointUsed) {
		this.pointUsed = pointUsed;
	}

	public int getProvincesId() {
		return this.provincesId;
	}

	public void setProvincesId(int provincesId) {
		this.provincesId = provincesId;
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

	public int getWardId() {
		return this.wardId;
	}

	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	public String getZalo() {
		return this.zalo;
	}

	public void setZalo(String zalo) {
		this.zalo = zalo;
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

	public CustomerSource getCustomerSource() {
		return this.customerSource;
	}

	public void setCustomerSource(CustomerSource customerSource) {
		this.customerSource = customerSource;
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