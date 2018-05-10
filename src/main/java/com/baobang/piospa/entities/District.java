package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the district database table.
 * 
 */
@Entity
@NamedQuery(name="District.findAll", query="SELECT d FROM District d")
public class District implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int districtid;

	private String location;

	private String name;

	private String type;

	//bi-directional many-to-one association to Province
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="provinceid")
	private Province province;

	//bi-directional many-to-one association to Ward
	@OneToMany(mappedBy="district")
	private List<Ward> wards;

	public District() {
	}

	public int getDistrictid() {
		return this.districtid;
	}

	public void setDistrictid(int districtid) {
		this.districtid = districtid;
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

	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<Ward> getWards() {
		return this.wards;
	}

	public void setWards(List<Ward> wards) {
		this.wards = wards;
	}

	public Ward addWard(Ward ward) {
		getWards().add(ward);
		ward.setDistrict(this);

		return ward;
	}

	public Ward removeWard(Ward ward) {
		getWards().remove(ward);
		ward.setDistrict(null);

		return ward;
	}

}