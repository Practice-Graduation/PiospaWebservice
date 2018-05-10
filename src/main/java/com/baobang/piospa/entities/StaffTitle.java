package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the staff_title database table.
 * 
 */
@Entity
@Table(name="staff_title")
@NamedQuery(name="StaffTitle.findAll", query="SELECT s FROM StaffTitle s")
public class StaffTitle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="staff_title_id")
	private int staffTitleId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="staff_title_code")
	private String staffTitleCode;

	@Lob
	@Column(name="staff_title_description")
	private String staffTitleDescription;

	@Column(name="staff_title_name")
	private String staffTitleName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	//bi-directional many-to-one association to Staff
	@OneToMany(mappedBy="staffTitle")
	private List<Staff> staffs;

	public StaffTitle() {
	}

	public int getStaffTitleId() {
		return this.staffTitleId;
	}

	public void setStaffTitleId(int staffTitleId) {
		this.staffTitleId = staffTitleId;
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

	public String getStaffTitleCode() {
		return this.staffTitleCode;
	}

	public void setStaffTitleCode(String staffTitleCode) {
		this.staffTitleCode = staffTitleCode;
	}

	public String getStaffTitleDescription() {
		return this.staffTitleDescription;
	}

	public void setStaffTitleDescription(String staffTitleDescription) {
		this.staffTitleDescription = staffTitleDescription;
	}

	public String getStaffTitleName() {
		return this.staffTitleName;
	}

	public void setStaffTitleName(String staffTitleName) {
		this.staffTitleName = staffTitleName;
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

	public List<Staff> getStaffs() {
		return this.staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}

	public Staff addStaff(Staff staff) {
		getStaffs().add(staff);
		staff.setStaffTitle(this);

		return staff;
	}

	public Staff removeStaff(Staff staff) {
		getStaffs().remove(staff);
		staff.setStaffTitle(null);

		return staff;
	}

}