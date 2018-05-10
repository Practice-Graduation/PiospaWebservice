package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the product_group database table.
 * 
 */
@Entity
@Table(name="product_group")
@NamedQuery(name="ProductGroup.findAll", query="SELECT p FROM ProductGroup p")
public class ProductGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_group_id")
	private int productGroupId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="product_group_code")
	private String productGroupCode;

	@Column(name="product_group_description")
	private String productGroupDescription;

	@Column(name="product_group_name")
	private String productGroupName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	//bi-directional many-to-one association to Product
	@JsonIgnore
	@OneToMany(mappedBy="productGroup")
	private List<Product> products;

	public ProductGroup() {
	}

	public int getProductGroupId() {
		return this.productGroupId;
	}

	public void setProductGroupId(int productGroupId) {
		this.productGroupId = productGroupId;
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

	public String getProductGroupCode() {
		return this.productGroupCode;
	}

	public void setProductGroupCode(String productGroupCode) {
		this.productGroupCode = productGroupCode;
	}

	public String getProductGroupDescription() {
		return this.productGroupDescription;
	}

	public void setProductGroupDescription(String productGroupDescription) {
		this.productGroupDescription = productGroupDescription;
	}

	public String getProductGroupName() {
		return this.productGroupName;
	}

	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
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
		product.setProductGroup(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductGroup(null);

		return product;
	}

}