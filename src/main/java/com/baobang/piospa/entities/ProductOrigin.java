package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;


/**
 * The persistent class for the product_origin database table.
 * 
 */
@Entity
@Table(name="product_origin")
@NamedQuery(name="ProductOrigin.findAll", query="SELECT p FROM ProductOrigin p")
public class ProductOrigin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_origin_id")
	private int productOriginId;
	
	@Column(name="is_active")
	private byte isActive;

	@Column(name="product_origin_name")
	private String productOriginName;

	//bi-directional many-to-one association to Product
	@JsonIgnore
	@OneToMany(mappedBy="productOrigin")
	private List<Product> products;

	public ProductOrigin() {
	}

	public int getProductOriginId() {
		return this.productOriginId;
	}

	public void setProductOriginId(int productOriginId) {
		this.productOriginId = productOriginId;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	
	public String getProductOriginName() {
		return this.productOriginName;
	}

	public void setProductOriginName(String productOriginName) {
		this.productOriginName = productOriginName;
	}
	
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductOrigin(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductOrigin(null);

		return product;
	}

}