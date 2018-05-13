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

import com.baobang.piospa.entities.ServiceGroup;
import com.baobang.piospa.entities.ServicePrice;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ServiceGroupRepository;
import com.baobang.piospa.repositories.ServicePriceRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
 * @author BaoBang
 * @Created May 4, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.SERVICE_PRICE_PATH)
public class ServicePriceController {
	@Autowired
	ServicePriceRepository mServicePriceRepository;
	@Autowired
	ServiceGroupRepository mServiceGroupRepository;

	/**
	 * @api {get} / Request Service Price information
	 * @apiName getAll
	 * @apiPrice Service
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Service Price of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Service Prices")
	public DataResult<List<ServicePrice>> getAll() {

		List<ServicePrice> servicePrices = mServicePriceRepository.findAll();

		return new DataResult<List<ServicePrice>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, servicePrices);
	}

	/**
	 * @api {get} /group/{groupId} Request Service Price information
	 * @apiName getServicePriceByGroupId
	 * @apiPrice Service
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Service Price of the response
	 * 
	 */
	@RequestMapping(//
			value = "/group/{groupId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Get Service Prices By Group Id")
	public DataResult<List<ServicePrice>> getServicePriceByGroupId(@PathVariable(value = "groupId") int groupId) {

		
		ServiceGroup group = mServiceGroupRepository.findById(groupId).get();
		
		return new DataResult<List<ServicePrice>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, group.getServicePrices());
	}
	

	/**
	 * 
	 * @api {get} /{servicePriceId} Request Service Price information
	 * @apiName getServicePriceById
	 * @apiPrice Service
	 * 
	 * @param {ServicePriceId}
	 *            id Service Price unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServicePrice} the Service Price was got
	 * 
	 */
	@RequestMapping(//
			value = "/{servicePriceId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Service Price by id")
	public DataResult<ServicePrice> getServicePriceById(@PathVariable(value = "servicePriceId") int servicePriceId) {
		DataResult<ServicePrice> result = new DataResult<>();
		Optional<ServicePrice> option = mServicePriceRepository.findById(servicePriceId);
		ServicePrice servicePrice = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(servicePrice);
		return result;
	}

	/**
	 * @api {post} / Create a new Service Price
	 * @apiName createServicePrice
	 * @apiPrice Service
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServicePrice} the new Service Price was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new Service Price")
	public DataResult<ServicePrice> createServicePrice(@RequestBody ServicePrice servicePrice) {
		DataResult<ServicePrice> result = new DataResult<>();

		Date date = new Date();
		servicePrice.setCreatedAt(date);
		servicePrice.setUpdatedAt(date);
		servicePrice = mServicePriceRepository.save(servicePrice);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(servicePrice);
		return result;
	}

	/**
	 * @api {put}/{servicePriceId} update Service Price by id
	 * @apiName updateServicePrice
	 * @apiPrice Service
	 * 
	 * @apiParam {ServicePriceId} id Service Price unique ID.
	 * @apiBody {ServicePrice} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServicePrice} the new Service Price was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{servicePriceId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Service Price by id")
	public DataResult<ServicePrice> updateServicePrice(@PathVariable(value = "servicePriceId") int servicePriceId,
			@RequestBody ServicePrice servicePrice) {
		DataResult<ServicePrice> result;
		Optional<ServicePrice> option = mServicePriceRepository.findById(servicePriceId);

		ServicePrice price = option.get();

		price.setService(servicePrice.getService());
		price.setServiceGroup(servicePrice.getServiceGroup());
		price.setServicePackage(servicePrice.getServicePackage());
		price.setRetailPrice(servicePrice.getRetailPrice());
		price.setAllPrice(servicePrice.getAllPrice());
		price.setIsActive(servicePrice.getIsActive());
		price.setUpdatedAt(new Date());
		price.setUpdatedBy(servicePrice.getUpdatedBy());
		price.setCreatedBy(servicePrice.getCreatedBy());

		price = mServicePriceRepository.save(price);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, price);

		return result;
	}

	/**
	 * @api {delete}/{servicePriceId} delete Service Price by id
	 * @apiName deleteServicePrice
	 * @apiPrice Service
	 * 
	 * @apiParam {servicePriceId} id Service Price unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServicePrice} the Service Price was deleted
	 * 
	 */
	@RequestMapping(//
			value = "/{servicePriceId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Service Price by id")
	public DataResult<ServicePrice> deleteServicePrice(@PathVariable(value = "servicePriceId") int servicePriceId) {
		DataResult<ServicePrice> dataResult = new DataResult<>();
		ServicePrice servicePrice = mServicePriceRepository.findById(servicePriceId).get();
		mServicePriceRepository.deleteById(servicePriceId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(servicePrice);
		return dataResult;
	}
}
