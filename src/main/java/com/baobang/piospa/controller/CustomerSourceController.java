package com.baobang.piospa.controller;

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

import com.baobang.piospa.entities.CustomerSource;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.CustomerSourceRepository;
import com.baobang.piospa.utils.MessageResponese;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
 * @author BaoBang
 * @Created Apr 20, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.CUSTOMER_SOURCE_PATH)
public class CustomerSourceController {
	@Autowired
	CustomerSourceRepository mCustomerSourceRepository;

	/**
	 * @api {get} / Request CustomerSource information
	 * @apiName getAll
	 * @apiGroup CustomerSource
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {array} the list CustomerSource
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all CustomerSources")
	public DataResult<List<CustomerSource>> getAll() {

		List<CustomerSource> CustomerSources = mCustomerSourceRepository.findAll();

		return new DataResult<List<CustomerSource>>(HttpStatus.OK.value(), MessageResponese.SUCCESSED, CustomerSources);
	}

	/**
	 * @api {get} /{CustomerSourceId} Request CustomerSource information
	 * @apiName getCustomerSourceById
	 * @apiOrigin CustomerSource
	 * 
	 * @apiParam {CustomerSourceId} id CustomerSource unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {ProductOrigin} the CustomerSource was got
	 * 
	 */
	@RequestMapping(//
			value = "/{customerSourceId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get CustomerSource by id")
	public DataResult<CustomerSource> getCustomerSourceById(
			@PathVariable(value = "customerSourceId") int customerSourceId) {
		DataResult<CustomerSource> result = new DataResult<>();
		CustomerSource CustomerSource = mCustomerSourceRepository.findById(customerSourceId).get();
		result.setMessage(MessageResponese.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(CustomerSource);
		return result;
	}

	/**
	 * @api {post} / Create a new CustomerSource information
	 * @apiName createCustomerSource
	 * @apiGroup CustomerSource
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {CustomerSource} the customer source was created
	 * 
	 */
	@RequestMapping(//
			value = {"", "/"}, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create new CustomerSource")
	public DataResult<CustomerSource> createCustomerSource(@RequestBody CustomerSource customerSource) {
		DataResult<CustomerSource> result = new DataResult<>();
		CustomerSource source = mCustomerSourceRepository.findByCode(customerSource.getCustomerSourceCode());

		if (source == null) {
			source = mCustomerSourceRepository.save(customerSource);
			result.setMessage(MessageResponese.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		} else {
			result.setMessage(MessageResponese.EXITS);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		result.setData(source);
		return result;
	}

	/**
	 * @api {put} /{customerSourceId} update CustomerSource information
	 * @apiName updateCustomerSource
	 * @apiGroup CustomerSource
	 * 
	 * @apiParam {CustomerSourceId} id CustomerSource unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {CustomerSource} the customer source was created
	 * 
	 */
	@RequestMapping(//
			value = "/{customerSourceId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update CustomerSource by id")
	public DataResult<CustomerSource> updateCustomerSource(
			@PathVariable(value = "customerSourceId") int customerSourceId,
			@RequestBody CustomerSource customerSource) {

		DataResult<CustomerSource> result = new DataResult<>();
		CustomerSource source = mCustomerSourceRepository.findById(customerSourceId).get();

		source.setCustomerSourceName(customerSource.getCustomerSourceName());
		source.setCustomerSourceDescription(customerSource.getCustomerSourceDescription());
		source.setIsActive(customerSource.getIsActive());
		source.setUpdatedBy(customerSource.getUpdatedBy());
		source.setUpdatedAt(new Date());
		
		source = mCustomerSourceRepository.save(source);
		result.setMessage(MessageResponese.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(source);
		return result;
	}
	
	/**
	 * @api {delete} /{customerSourceId} delete CustomerSource information
	 * @apiName deleteCustomerSource
	 * @apiGroup CustomerSource
	 * 
	 * @apiParam {CustomerSourceId} id CustomerSource unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {CustomerSource} the customer source was created
	 * 
	 */
	@RequestMapping(//
			value = "/{customerSourceId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete CustomerSource by id")
	public DataResult<CustomerSource> deleteCustomerSource(
			@PathVariable(value = "customerSourceId") int customerSourceId) {

		DataResult<CustomerSource> result = new DataResult<>();
		CustomerSource source = mCustomerSourceRepository.findById(customerSourceId).get();

		mCustomerSourceRepository.delete(source);
		result.setMessage(MessageResponese.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(source);
		return result;
	}
}
