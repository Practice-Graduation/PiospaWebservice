package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.List;

/**
 * The persistent class for the province database table.
 * 
 */
@Entity
@NamedQuery(name = "Province.findAll", query = "SELECT p FROM Province p")
public class Province implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int provinceid;

	private String name;

	private String type;

	// bi-directional many-to-one association to District
	@JsonIgnore
	@OneToMany(mappedBy = "province")
	private List<District> districts;

	// bi-directional many-to-one association to Customer
	@JsonIgnore
	@OneToMany(mappedBy = "province")
	private List<Customer> customers;

	public Province() {
	}

	public int getProvinceid() {
		return this.provinceid;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
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

	public List<District> getDistricts() {
		return this.districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	public District addDistrict(District district) {
		getDistricts().add(district);
		district.setProvince(this);

		return district;
	}

	public District removeDistrict(District district) {
		getDistricts().remove(district);
		district.setProvince(null);

		return district;
	}
	
	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setProvince(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setProvince(null);

		return customer;
	}

}