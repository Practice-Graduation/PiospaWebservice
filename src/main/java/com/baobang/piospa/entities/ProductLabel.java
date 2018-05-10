package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the product_label database table.
 * 
 */
@Entity
@Table(name="product_label")
@NamedQuery(name="ProductLabel.findAll", query="SELECT p FROM ProductLabel p")
public class ProductLabel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_label_id")
	private int productLabelId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="product_label_code")
	private String productLabelCode;

	@Column(name="product_label_description")
	private String productLabelDescription;

	@Column(name="product_label_name")
	private String productLabelName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	//bi-directional many-to-one association to Product
	@JsonIgnore
	@OneToMany(mappedBy="productLabel")
	private List<Product> products;

	public ProductLabel() {
	}

	public int getProductLabelId() {
		return this.productLabelId;
	}

	public void setProductLabelId(int productLabelId) {
		this.productLabelId = productLabelId;
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

	public String getProductLabelCode() {
		return this.productLabelCode;
	}

	public void setProductLabelCode(String productLabelCode) {
		this.productLabelCode = productLabelCode;
	}

	public String getProductLabelDescription() {
		return this.productLabelDescription;
	}

	public void setProductLabelDescription(String productLabelDescription) {
		this.productLabelDescription = productLabelDescription;
	}

	public String getProductLabelName() {
		return this.productLabelName;
	}

	public void setProductLabelName(String productLabelName) {
		this.productLabelName = productLabelName;
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
		product.setProductLabel(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductLabel(null);

		return product;
	}

}