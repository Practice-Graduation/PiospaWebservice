package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id")
	private int orderId;

	private String address;

	@Column(name="address_delivery")
	private String addressDelivery;

	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_delivery")
	private Date dateDelivery;

	@Column(name="delivery_code")
	private String deliveryCode;

	@Column(name="delivery_cost")
	private int deliveryCost;

	private int discount;

	private String email;

	@Column(name="full_name")
	private String fullName;

	private String note;

	private String phone;

	@Column(name="staff_id")
	private int staffId;

	@Column(name="sub_total")
	private int subTotal;

	@Column(name="tax_id")
	private int taxId;

	private int total;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	@Column(name="voucher_id")
	private int voucherId;

	//bi-directional many-to-one association to Booking
	@OneToOne
	@JoinColumn(name="order_id", referencedColumnName="order_id")
	private Booking booking;

	//bi-directional many-to-one association to OrderProduct
	@JsonIgnore
	@OneToMany(mappedBy="order" , cascade = CascadeType.ALL)
	private List<OrderProduct> orderProducts = new ArrayList<>();

	//bi-directional many-to-one association to CustomerSource
	@ManyToOne
	@JoinColumn(name="customer_source_id")
	private CustomerSource customerSource;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	//bi-directional many-to-one association to OrderDeliveryStatus
	@ManyToOne(cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
	@JoinColumn(name="order_delivery_status_id")
	private OrderDeliveryStatus orderDeliveryStatus;

	//bi-directional many-to-one association to OrderDeliveryType
	@ManyToOne(cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
	@JoinColumn(name="order_delivery_type_id")
	private OrderDeliveryType orderDeliveryType;

	//bi-directional many-to-one association to OrderPaymentType
	@ManyToOne(cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
	@JoinColumn(name="order_payment_type_id")
	private OrderPaymentType orderPaymentType;

	//bi-directional many-to-one association to OrderReasonCancel
	@ManyToOne
	@JoinColumn(name="order_reasion_cancel_id")
	private OrderReasonCancel orderReasonCancel;

	//bi-directional many-to-one association to OrderStatus
	@ManyToOne(cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
	@JoinColumn(name="order_status_id")
	private OrderStatus orderStatus;

	public Order() {
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressDelivery() {
		return this.addressDelivery;
	}

	public void setAddressDelivery(String addressDelivery) {
		this.addressDelivery = addressDelivery;
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

	public Date getDateDelivery() {
		return this.dateDelivery;
	}

	public void setDateDelivery(Date dateDelivery) {
		this.dateDelivery = dateDelivery;
	}

	public String getDeliveryCode() {
		return this.deliveryCode;
	}

	public void setDeliveryCode(String deliveryCode) {
		this.deliveryCode = deliveryCode;
	}

	public int getDeliveryCost() {
		return this.deliveryCost;
	}

	public void setDeliveryCost(int deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public int getDiscount() {
		return this.discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getStaffId() {
		return this.staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public int getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

	public int getTaxId() {
		return this.taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
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

	public int getVoucherId() {
		return this.voucherId;
	}

	public void setVoucherId(int voucherId) {
		this.voucherId = voucherId;
	}

	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public List<OrderProduct> getOrderProducts() {
		return this.orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public OrderProduct addOrderProduct(OrderProduct orderProduct) {
		getOrderProducts().add(orderProduct);
		orderProduct.setOrder(this);

		return orderProduct;
	}

	public OrderProduct removeOrderProduct(OrderProduct orderProduct) {
		getOrderProducts().remove(orderProduct);
		orderProduct.setOrder(null);

		return orderProduct;
	}

	public CustomerSource getCustomerSource() {
		return this.customerSource;
	}

	public void setCustomerSource(CustomerSource customerSource) {
		this.customerSource = customerSource;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderDeliveryStatus getOrderDeliveryStatus() {
		return this.orderDeliveryStatus;
	}

	public void setOrderDeliveryStatus(OrderDeliveryStatus orderDeliveryStatus) {
		this.orderDeliveryStatus = orderDeliveryStatus;
	}

	public OrderDeliveryType getOrderDeliveryType() {
		return this.orderDeliveryType;
	}

	public void setOrderDeliveryType(OrderDeliveryType orderDeliveryType) {
		this.orderDeliveryType = orderDeliveryType;
	}

	public OrderPaymentType getOrderPaymentType() {
		return this.orderPaymentType;
	}

	public void setOrderPaymentType(OrderPaymentType orderPaymentType) {
		this.orderPaymentType = orderPaymentType;
	}

	public OrderReasonCancel getOrderReasonCancel() {
		return this.orderReasonCancel;
	}

	public void setOrderReasonCancel(OrderReasonCancel orderReasonCancel) {
		this.orderReasonCancel = orderReasonCancel;
	}

	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public void caculate() {
		
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
