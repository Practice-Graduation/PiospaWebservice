package com.baobang.piospa.controller.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baobang.piospa.entities.CustomerGroup;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.CustomerGroupRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;
import com.baobang.piospa.utils.Utils;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created Apr 24, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.CUSTOMER_GROUP_PATH)
public class CustomerGroupController {
	
	@Autowired
	CustomerGroupRepository mCustomerGroupRepository;

	/**
	 * @api {get} / Request CustomerGroup information
	 * @apiName getAll
	 * @apiGroup CustomerGroup
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list CustomerGroup
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all CustomerGroups")
	public DataResult<List<CustomerGroup>> getAll() {

		List<CustomerGroup> customerGroups = mCustomerGroupRepository.findAll();

		return new DataResult<List<CustomerGroup>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, customerGroups);
	}

	/**
	 * @api {get} /{CustomerGroupId} Request CustomerGroup information
	 * @apiName getCustomerGroupById
	 * @apiOrigin CustomerGroup
	 * 
	 * @apiParam {CustomerGroupId} id CustomerGroup unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductOrigin} the CustomerGroup was got
	 * 
	 */
	@RequestMapping(//
			value = "/{customerGroupId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get CustomerGroup by id")
	public DataResult<CustomerGroup> getCustomerGroupById(
			@PathVariable(value = "customerGroupId") int customerGroupId) {
		DataResult<CustomerGroup> result = new DataResult<>();
		CustomerGroup customerGroup = mCustomerGroupRepository.findById(customerGroupId).get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(customerGroup);
		return result;
	}

	/**
	 * @api {post} / Create a new CustomerGroup information
	 * @apiName createCustomerGroup
	 * @apiGroup CustomerGroup
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {CustomerGroup} the customer Group was created
	 * 
	 */
	@RequestMapping(//
			value = {"", "/"}, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create new CustomerGroup")
	public DataResult<CustomerGroup> createCustomerGroup(@RequestBody CustomerGroup customerGroup) {
		DataResult<CustomerGroup> result = new DataResult<>();
		CustomerGroup group = mCustomerGroupRepository.findByCode(customerGroup.getCustomerGroupCode());

		if (group == null) {
			customerGroup.setCustomerGroupCode(Utils.genarateCode());
			group = mCustomerGroupRepository.save(customerGroup);
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
	 * @api {put} /{customerGroupId} update CustomerGroup information
	 * @apiName updateCustomerGroup
	 * @apiGroup CustomerGroup
	 * 
	 * @apiParam {CustomerGroupId} id CustomerGroup unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {CustomerGroup} the customer Group was created
	 * 
	 */
	@RequestMapping(//
			value = "/{customerGroupId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update CustomerGroup by id")
	public DataResult<CustomerGroup> updateCustomerGroup(
			@PathVariable(value = "customerGroupId") int customerGroupId,
			@RequestBody CustomerGroup customerGroup) {

		DataResult<CustomerGroup> result = new DataResult<>();
		CustomerGroup group = mCustomerGroupRepository.findById(customerGroupId).get();

		group.setCustomerGroupName(customerGroup.getCustomerGroupName());
		group.setCustomerGroupDescription(customerGroup.getCustomerGroupDescription());
		group.setCustomerGroupImage(customerGroup.getCustomerGroupImage());
		group.setIsActive(customerGroup.getIsActive());
		group.setUpdatedBy(customerGroup.getUpdatedBy());
		group.setUpdatedAt(new Date());
		
		group = mCustomerGroupRepository.save(group);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(group);
		return result;
	}
	
	/**
	 * @api {delete} /{customerGroupId} delete CustomerGroup information
	 * @apiName deleteCustomerGroup
	 * @apiGroup CustomerGroup
	 * 
	 * @apiParam {CustomerGroupId} id CustomerGroup unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {CustomerGroup} the customer Group was created
	 * 
	 */
	@RequestMapping(//
			value = "/{customerGroupId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete CustomerGroup by id")
	public DataResult<CustomerGroup> deleteCustomerGroup(
			@PathVariable(value = "customerGroupId") int customerGroupId) {

		DataResult<CustomerGroup> result = new DataResult<>();
		CustomerGroup group = mCustomerGroupRepository.findById(customerGroupId).get();

		mCustomerGroupRepository.delete(group);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(group);
		return result;
	}
}
