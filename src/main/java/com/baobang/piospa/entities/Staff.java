package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the staffs database table.
 * 
 */
@Entity
@Table(name="staffs")
@NamedQuery(name="Staff.findAll", query="SELECT s FROM Staff s")
public class Staff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="staff_id")
	private int staffId;

	private String account;

	private String fullname;

	@Column(name="is_admin")
	private byte isAdmin;

	private String password;

	private String phone = "";

	@Column(name="staff_avatar")
	private String staffAvatar = "https://i1.wp.com/www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png?resize=256%2C256&quality=100&ssl=1";

	public Staff() {
	}

	public int getStaffId() {
		return this.staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	

	public byte getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(byte isAdmin) {
		this.isAdmin = isAdmin;
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

	public String getStaffAvatar() {
		return this.staffAvatar;
	}

	public void setStaffAvatar(String staffAvatar) {
		this.staffAvatar = staffAvatar;
	}

}