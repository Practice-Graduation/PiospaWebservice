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
	private String wardid;

	private String districtid;

	private String location;

	private String name;

	private String type;

	public Ward() {
	}

	public String getWardid() {
		return this.wardid;
	}

	public void setWardid(String wardid) {
		this.wardid = wardid;
	}

	public String getDistrictid() {
		return this.districtid;
	}

	public void setDistrictid(String districtid) {
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

}