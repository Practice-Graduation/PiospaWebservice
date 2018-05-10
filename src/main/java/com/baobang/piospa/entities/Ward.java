package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ward database table.
 * 
 */
@Entity
@NamedQuery(name="Ward.findAll", query="SELECT w FROM Ward w")
public class Ward implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int wardid;

	private String location;

	private String name;

	private String type;

	//bi-directional many-to-one association to District
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="districtid")
	private District district;

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

}