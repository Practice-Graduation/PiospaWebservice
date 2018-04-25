package com.baobang.piospa.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baobang.piospa.entities.ProductAttribute;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ProductAttributeRepository;
import com.baobang.piospa.utils.MessageResponese;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created Apr 25, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.PRODUCT_ATTRIBUTE_PATH)
public class ProductAttributeController {
	@Autowired

	ProductAttributeRepository mProductAttributeRepository;

	/**
	 * @api {get} / Request Product Attribute information
	 * @apiName getAll
	 * @apiGroup Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {array} the list  Product Attribute of the responese
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Product Attributes")
	public DataResult<List<ProductAttribute>> getAll() {
		
		List<ProductAttribute> ProductAttributes = mProductAttributeRepository.findAll();

		return new DataResult<List<ProductAttribute>>(HttpStatus.OK.value(), MessageResponese.SUCCESSED, ProductAttributes);
	}

	/**
	 * @api {get} /{productAttributeId} Request Product ProductAttribute information
	 * @apiName getProductAttributeById
	 * @apiGroup Product
	 * 
	 * @apiParam {productAttributeId} id Product Attribute unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {User} the Product Attribute was got
	 * 
	 */
	@RequestMapping(//
			value = "/{productAttributeId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Product Attribute by id")
	public DataResult<ProductAttribute> getProductAttributeById(@PathVariable(value = "productAttributeId") int productAttributeId) {
		DataResult<ProductAttribute> result = new DataResult<>();
		Optional<ProductAttribute> option = mProductAttributeRepository.findById(productAttributeId);
		ProductAttribute productAttribute = option.get();
		result.setMessage(MessageResponese.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(productAttribute);
		return result;
	}

	/**
	 * @api {post} / Create a new Product Attribute
	 * @apiName createProductAttribute
	 * @apiGroup Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {ProductAttribute} the new ProductAttribute was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new ProductAttribute")
	public DataResult<ProductAttribute> CreateProductAttribute(@RequestBody ProductAttribute ProductAttribute) {
		DataResult<ProductAttribute> result = new DataResult<>();

		Date date = new Date();
		ProductAttribute.setProductAttributeId(0);
		ProductAttribute.setCreatedAt(date);
		ProductAttribute.setUpdatedAt(date);
		ProductAttribute = mProductAttributeRepository.save(ProductAttribute);
		result.setMessage(MessageResponese.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(ProductAttribute);
		return result;
	}

	/**
	 * @api {put}/{productAttributeId} update Product Attribute by id
	 * @apiName updateProductAttribute
	 * @apiGroup Product
	 * 
	 * @apiParam {ProductAttributeId} id Product ProductAttribute unique ID.
	 * @apiBody {ProductAttribute} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {ProductAttribute} the new Product Attribute was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{productAttributeId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update product ProductAttribute by id")
	public DataResult<ProductAttribute> updateProductAttribute(@PathVariable(value = "productAttributeId") int productAttributeId,
			@RequestBody ProductAttribute productAttribute) {
		DataResult<ProductAttribute> result;
		Optional<ProductAttribute> option = mProductAttributeRepository.findById(productAttributeId);

		ProductAttribute newProductAttribute = option.get();

		newProductAttribute.setProduct(productAttribute.getProduct());
		newProductAttribute.setAttributeValue(productAttribute.getAttributeValue());
		newProductAttribute.setIsActive(productAttribute.getIsActive());
		newProductAttribute.setAttributeValue(productAttribute.getAttributeValue());
		newProductAttribute.setUpdatedBy(productAttribute.getUpdatedBy());
		newProductAttribute.setUpdatedAt(new Date());

		newProductAttribute = mProductAttributeRepository.save(newProductAttribute);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponese.SUCCESSED, newProductAttribute);

		return result;
	}

	/**
	 * @api {delete}/{productAttributeId} delete Product Attribute by id
	 * @apiName deleteProductAttribute
	 * @apiGroup Product
	 * 
	 * @apiParam {productAttributeId} id Product Attribute unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {ProductAttribute} the Product Attribute was deleted
	 * 
	 * 
	 */
	@RequestMapping(//
			value = "/{productAttributeId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete product ProductAttribute by id")
	public DataResult<ProductAttribute> deleteProductAttribute(@PathVariable(value = "productAttributeId") int productAttributeId) {
		DataResult<ProductAttribute> dataResult = new DataResult<>();
		ProductAttribute productAttribute = mProductAttributeRepository.findById(productAttributeId).get();
		mProductAttributeRepository.deleteById(productAttributeId);
		dataResult.setMessage(MessageResponese.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(productAttribute);
		return dataResult;
	}

}
