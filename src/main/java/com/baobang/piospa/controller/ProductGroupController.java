
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

import com.baobang.piospa.entities.Product;
import com.baobang.piospa.entities.ProductGroup;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ProductGroupRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
 * @author BaoBang
 * @Created Apr 18, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.PRODUCT_GROUP_PATH)
public class ProductGroupController {
	@Autowired
	ProductGroupRepository mGroupRepository;

	/**
	 * @api {get} / Request Product Group information
	 * @apiName getAll
	 * @apiGroup Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list product group of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all product groups")
	public DataResult<List<ProductGroup>> getAll() {

		List<ProductGroup> productGroups = mGroupRepository.findAll();

		return new DataResult<List<ProductGroup>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, productGroups);
	}

	/**
	 * @api {get} /{productGroupId} Request Product Group information
	 * @apiName getProductGroupById
	 * @apiGroup Product
	 * 
	 * @apiParam {productGroupId} id Product Group unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductGroup} the Product Group was got
	 * 
	 */
	@RequestMapping(//
			value = "/{productGroupId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get product group by id")
	public DataResult<ProductGroup> getProductGroupById(@PathVariable(value = "productGroupId") int productGroupId) {
		DataResult<ProductGroup> result = new DataResult<>();
		Optional<ProductGroup> option = mGroupRepository.findById(productGroupId);
		ProductGroup group = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(group);
		return result;
	}
	
	/**
	 * @api {get} /{productGroupId}/products Request Product information
	 * @apiName getProductByGroupId
	 * @apiGroup Product
	 * 
	 * @apiParam {productGroupId} id Product Group unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array[Product]} the Products of Group was got
	 * 
	 */
	@RequestMapping(//
			value = "/{productGroupId}/products", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get products by group id")
	public DataResult<List<Product>> getProductByGroupId(@PathVariable(value = "productGroupId") int productGroupId) {
		DataResult<List<Product>> result = new DataResult<>();
		Optional<ProductGroup> option = mGroupRepository.findById(productGroupId);
		ProductGroup group = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(group.getProducts());
		return result;
	}

	/**
	 * @api {post} / Create a new Product Group
	 * @apiName createProductGroup
	 * @apiGroup Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductGroup} the new Product Group was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new product group")
	public DataResult<ProductGroup> CreateProductGroup(@RequestBody ProductGroup productGroup) {
		DataResult<ProductGroup> result = new DataResult<>();
		ProductGroup group = mGroupRepository.findByCode(productGroup.getProductGroupCode());

		if (group == null) {

			Date date = new Date();
			productGroup.setProductGroupId(0);
			productGroup.setCreatedAt(date);
			productGroup.setUpdatedAt(date);
			group = mGroupRepository.save(productGroup);

			result.setMessage(MessageResponse.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		} else {
			result.setMessage(MessageResponse.EXITS);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}

		result.setData(group);
		return result;
	}

	/**
	 * @api {put}/{productGroupId} update Product Group by id
	 * @apiName updateProductGroup
	 * @apiGroup Product
	 * 
	 * @apiParam {productGroupId} id Product Group unique ID.
	 * @apiBody {productGroup} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductGroup} the new Product Group was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{productGroupId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update product group by id")
	public DataResult<ProductGroup> updateProductGroup(@PathVariable(value = "productGroupId") int productGroupId,
			@RequestBody ProductGroup productGroup) {
		DataResult<ProductGroup> result;
		Optional<ProductGroup> option = mGroupRepository.findById(productGroupId);

		ProductGroup group = option.get();

		group.setProductGroupName(productGroup.getProductGroupName());
		group.setProductGroupDescription(productGroup.getProductGroupDescription());
		group.setIsActive(productGroup.getIsActive());
		group.setUpdatedAt(new Date());
		group.setUpdatedBy(productGroup.getUpdatedBy());
		group.setCreatedBy(productGroup.getCreatedBy());

		group = mGroupRepository.save(group);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, group);

		return result;
	}

	/**
	 * @api {delete}/{productGroupId} delete Product Group by id
	 * @apiName deleteProductGroup
	 * @apiGroup Product
	 * 
	 * @apiParam {productGroupId} id Product Group unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductGroup} the Product Group was deleted
	 * 
	 */
	@RequestMapping(//
			value = "/{productGroupId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete product group by id")
	public DataResult<ProductGroup> deleteProductGroup(@PathVariable(value = "productGroupId") int productGroupId) {
		DataResult<ProductGroup> dataResult = new DataResult<>();
		ProductGroup productGroup = mGroupRepository.findById(productGroupId).get();
		mGroupRepository.deleteById(productGroupId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(productGroup);
		return dataResult;
	}
}
