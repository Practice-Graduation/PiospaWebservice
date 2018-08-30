package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the service_package_detail database table.
 * 
 */
@Entity
@Table(name="service_package_detail")
@NamedQuery(name="ServicePackageDetail.findAll", query="SELECT s FROM ServicePackageDetail s")
public class ServicePackageDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="service_package_detail_id")
	private int servicePackageDetailId;

	//bi-directional many-to-one association to ServicePackage
	@ManyToOne
	@JoinColumn(name="service_package_id")
	private ServicePackage servicePackage;
	
	

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="service_id")
	private Service service;

	public ServicePackageDetail() {
	}

	public int getServicePackageDetailId() {
		return this.servicePackageDetailId;
	}

	public void setServicePackageDetailId(int servicePackageDetailId) {
		this.servicePackageDetailId = servicePackageDetailId;
	}
	
	public ServicePackage getServicePackage() {
		return this.servicePackage;
	}

	public void setServicePackage(ServicePackage servicePackage) {
		this.servicePackage = servicePackage;
	}


	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}