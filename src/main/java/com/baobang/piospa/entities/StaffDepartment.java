package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the staff_department database table.
 * 
 */
@Entity
@Table(name="staff_department")
@NamedQuery(name="StaffDepartment.findAll", query="SELECT s FROM StaffDepartment s")
public class StaffDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="staff_department_id")
	private int staffDepartmentId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="staff_department_code")
	private String staffDepartmentCode;

	@Lob
	@Column(name="staff_department_description")
	private String staffDepartmentDescription;

	@Column(name="staff_department_name")
	private String staffDepartmentName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	public StaffDepartment() {
	}

	public int getStaffDepartmentId() {
		return this.staffDepartmentId;
	}

	public void setStaffDepartmentId(int staffDepartmentId) {
		this.staffDepartmentId = staffDepartmentId;
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

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public String getStaffDepartmentCode() {
		return this.staffDepartmentCode;
	}

	public void setStaffDepartmentCode(String staffDepartmentCode) {
		this.staffDepartmentCode = staffDepartmentCode;
	}

	public String getStaffDepartmentDescription() {
		return this.staffDepartmentDescription;
	}

	public void setStaffDepartmentDescription(String staffDepartmentDescription) {
		this.staffDepartmentDescription = staffDepartmentDescription;
	}

	public String getStaffDepartmentName() {
		return this.staffDepartmentName;
	}

	public void setStaffDepartmentName(String staffDepartmentName) {
		this.staffDepartmentName = staffDepartmentName;
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

}