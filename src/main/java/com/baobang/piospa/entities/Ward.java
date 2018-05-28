package com.baobang.piospa.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the ward database table.
 * 
 */
@Entity
@NamedQuery(name = "Ward.findAll", query = "SELECT w FROM Ward w")
public class Ward implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wardid;

	private String location;

	private String name;

	private String type;

	// bi-directional many-to-one association to District
	@ManyToOne
	@JoinColumn(name = "districtid")
	private District district;

	// bi-directional many-to-one association to Customer
	@JsonIgnore
	@OneToMany(mappedBy = "ward")
	private List<Customer> customers;

	public Ward() {
	}

	public int getWardid() {
		return this.wardid;
	}

	public void setWardid(int wardid) {
		this.wardid = wardid;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	
	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setWard(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setWard(null);

		return customer;
	}

}