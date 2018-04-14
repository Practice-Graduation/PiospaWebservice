package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


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
	@Column(name="order_id")
	private int orderId;

	private String address;

	@Column(name="address_delivery")
	private String addressDelivery;

	private String code;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="customer_id")
	private int customerId;

	@Column(name="customer_source_id")
	private int customerSourceId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_delivery")
	private Date dateDelivery;

	@Column(name="delivery_code")
	private String deliveryCode;

	@Column(name="delivery_cost")
	private int deliveryCost;

	private int discount;

	private String email;

	private String note;

	@Column(name="order_delivery_status_id")
	private int orderDeliveryStatusId;

	@Column(name="order_delivery_type_id")
	private int orderDeliveryTypeId;

	@Column(name="order_payment_type_id")
	private int orderPaymentTypeId;

	@Column(name="order_reasion_cancel_id")
	private int orderReasionCancelId;

	@Column(name="order_status_id")
	private int orderStatusId;

	@Column(name="staff_id")
	private int staffId;

	@Column(name="sub_total")
	private int subTotal;

	@Column(name="tax_id")
	private int taxId;

	private int total;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	@Column(name="voucher_id")
	private int voucherId;

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

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getCustomerSourceId() {
		return this.customerSourceId;
	}

	public void setCustomerSourceId(int customerSourceId) {
		this.customerSourceId = customerSourceId;
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

	public int getOrderDeliveryStatusId() {
		return this.orderDeliveryStatusId;
	}

	public void setOrderDeliveryStatusId(int orderDeliveryStatusId) {
		this.orderDeliveryStatusId = orderDeliveryStatusId;
	}

	public int getOrderDeliveryTypeId() {
		return this.orderDeliveryTypeId;
	}

	public void setOrderDeliveryTypeId(int orderDeliveryTypeId) {
		this.orderDeliveryTypeId = orderDeliveryTypeId;
	}

	public int getOrderPaymentTypeId() {
		return this.orderPaymentTypeId;
	}

	public void setOrderPaymentTypeId(int orderPaymentTypeId) {
		this.orderPaymentTypeId = orderPaymentTypeId;
	}

	public int getOrderReasionCancelId() {
		return this.orderReasionCancelId;
	}

	public void setOrderReasionCancelId(int orderReasionCancelId) {
		this.orderReasionCancelId = orderReasionCancelId;
	}

	public int getOrderStatusId() {
		return this.orderStatusId;
	}

	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
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

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
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

}