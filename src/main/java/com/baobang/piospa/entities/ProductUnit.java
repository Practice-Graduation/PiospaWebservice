package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the product_unit database table.
 * 
 */
@Entity
@Table(name="product_unit")
@NamedQuery(name="ProductUnit.findAll", query="SELECT p FROM ProductUnit p")
public class ProductUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_unit_id")
	private int productUnitId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="product_unit_description")
	private String productUnitDescription;

	@Column(name="product_unit_name")
	private String productUnitName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productUnit")
	private List<Product> products;

	public ProductUnit() {
	}

	public int getProductUnitId() {
		return this.productUnitId;
	}

	public void setProductUnitId(int productUnitId) {
		this.productUnitId = productUnitId;
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

	public String getProductUnitDescription() {
		return this.productUnitDescription;
	}

	public void setProductUnitDescription(String productUnitDescription) {
		this.productUnitDescription = productUnitDescription;
	}

	public String getProductUnitName() {
		return this.productUnitName;
	}

	public void setProductUnitName(String productUnitName) {
		this.productUnitName = productUnitName;
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

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductUnit(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductUnit(null);

		return product;
	}

}