package com.baobang.piospa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the attribute_value database table.
 * 
 */
@Entity
@Table(name="attribute_value")
@NamedQuery(name="AttributeValue.findAll", query="SELECT a FROM AttributeValue a")
public class AttributeValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="attribute_value_id")
	private int attributeValueId;

	@Column(name="attribute_value_name")
	private String attributeValueName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="is_active")
	private byte isActive;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="updated_by")
	private int updatedBy;

	//bi-directional many-to-one association to Attribute
	@ManyToOne
	@JoinColumn(name="attribute_id")
	private Attribute attribute;

	//bi-directional many-to-one association to ProductAttribute
	@JsonIgnore
	@OneToMany(mappedBy="attributeValue")
	private List<ProductAttribute> productAttributes;

	public AttributeValue() {
	}

	public int getAttributeValueId() {
		return this.attributeValueId;
	}

	public void setAttributeValueId(int attributeValueId) {
		this.attributeValueId = attributeValueId;
	}

	public String getAttributeValueName() {
		return this.attributeValueName;
	}

	public void setAttributeValueName(String attributeValueName) {
		this.attributeValueName = attributeValueName;
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

	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public List<ProductAttribute> getProductAttributes() {
		return this.productAttributes;
	}

	public void setProductAttributes(List<ProductAttribute> productAttributes) {
		this.productAttributes = productAttributes;
	}

	public ProductAttribute addProductAttribute(ProductAttribute productAttribute) {
		getProductAttributes().add(productAttribute);
		productAttribute.setAttributeValue(this);

		return productAttribute;
	}

	public ProductAttribute removeProductAttribute(ProductAttribute productAttribute) {
		getProductAttributes().remove(productAttribute);
		productAttribute.setAttributeValue(null);

		return productAttribute;
	}

}