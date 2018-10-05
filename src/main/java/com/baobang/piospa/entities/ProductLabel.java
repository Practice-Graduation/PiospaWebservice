package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	@Column(name="is_active")
	private byte isActive;
	
	@Column(name="product_label_name")
	private String productLabelName;
	
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

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	
	public String getProductLabelName() {
		return this.productLabelName;
	}

	public void setProductLabelName(String productLabelName) {
		this.productLabelName = productLabelName;
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