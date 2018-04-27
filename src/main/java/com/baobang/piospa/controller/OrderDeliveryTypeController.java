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

import com.baobang.piospa.entities.OrderDeliveryType;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.OrderDeliveryTypeRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created Apr 25, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.ORDER_DELIVERY_TYPE_PATH)
public class OrderDeliveryTypeController {
	@Autowired
	OrderDeliveryTypeRepository mOrderDeliveryTypeRepository;

	/**
	 * @api {get} / Request Order DeliveryType information
	 * @apiName getAll
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the DeliveryType of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Order Delivery Types
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Order DeliveryType")
	public DataResult<List<OrderDeliveryType>> getAll() {

		List<OrderDeliveryType> OrderDeliveryTypes = mOrderDeliveryTypeRepository.findAll();

		return new DataResult<List<OrderDeliveryType>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, OrderDeliveryTypes);
	}

	/**
	 * @api {get} /{orderDeliveryTypeId} Request Order DeliveryType information
	 * @apiName getOrderDeliveryTypeById
	 * @apiGroup Order
	 * 
	 * @apiParam {orderDeliveryTypeId} id Order DeliveryType unique ID.
	 * 
	 * @apiSuccess {Integer} the DeliveryType of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderDeliveryType} the Order DeliveryType was got
	 * 
	 */
	@RequestMapping(//
			value = "/{orderDeliveryTypeId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get OrderDeliveryType by id")
	public DataResult<OrderDeliveryType> getOrderDeliveryTypeById(@PathVariable(value = "orderDeliveryTypeId") int orderDeliveryTypeId) {
		DataResult<OrderDeliveryType> result = new DataResult<>();
		OrderDeliveryType OrderDeliveryType = mOrderDeliveryTypeRepository.findById(orderDeliveryTypeId).get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(OrderDeliveryType);
		return result;
	}

	/**
	 * @api {post} / Create a new Product OrderDeliveryType
	 * @apiName createOrderDeliveryType
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the DeliveryType of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderDeliveryType} the new Order DeliveryType was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new OrderDeliveryType")
	public DataResult<OrderDeliveryType> createOrderDeliveryType(@RequestBody OrderDeliveryType orderDeliveryType) {
		DataResult<OrderDeliveryType> result = new DataResult<>();

		OrderDeliveryType temp = mOrderDeliveryTypeRepository.findByCode(orderDeliveryType.getOrderDeliveryTypeCode());
		
		if(temp == null) {
			Date date = new Date();
			orderDeliveryType.setOrderDeliveryTypeId(0);
			orderDeliveryType.setCreatedAt(date);
			orderDeliveryType.setUpdatedAt(date);
			orderDeliveryType = mOrderDeliveryTypeRepository.save(orderDeliveryType);
			result.setMessage(MessageResponse.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		}else {
			result.setMessage(MessageResponse.EXITS);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		
		
		result.setData(orderDeliveryType);
		return result;
	}
	
	/**
	 * @api {put}/{orderDeliveryTypeId} update Order DeliveryType by id
	 * @apiName updateOrderDeliveryType
	 * @apiGroup Order
	 * 
	 * @apiParam {OrderDeliveryTypeId} id Product OrderDeliveryType unique ID.
	 * @apiBody {OrderDeliveryType} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the DeliveryType of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderDeliveryType} the new Product OrderDeliveryType was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{orderDeliveryTypeId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Order DeliveryType by id")
	public DataResult<OrderDeliveryType> updateOrderDeliveryType(@PathVariable(value = "orderDeliveryTypeId") int orderDeliveryTypeId,
			@RequestBody OrderDeliveryType orderDeliveryType) {
		DataResult<OrderDeliveryType> result;
		Optional<OrderDeliveryType> option = mOrderDeliveryTypeRepository.findById(orderDeliveryTypeId);

		OrderDeliveryType newOrderDeliveryType = option.get();

		newOrderDeliveryType.setOrderDeliveryTypeName(orderDeliveryType.getOrderDeliveryTypeName());
		newOrderDeliveryType.setIsActive(orderDeliveryType.getIsActive());
		newOrderDeliveryType.setCreatedBy(orderDeliveryType.getCreatedBy());
		newOrderDeliveryType.setUpdatedBy(orderDeliveryType.getUpdatedBy());
		newOrderDeliveryType.setUpdatedAt(new Date());

		newOrderDeliveryType = mOrderDeliveryTypeRepository.save(newOrderDeliveryType);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newOrderDeliveryType);

		return result;
	}

	/**
	 * @api {delete}/{orderDeliveryTypeId} delete Order DeliveryType by id
	 * @apiName deleteOrderDeliveryType
	 * @apiGroup Orders
	 * 
	 * @apiParam {orderDeliveryTypeId} id Product Order DeliveryType unique ID.
	 * 
	 * @apiSuccess {Integer} the DeliveryType of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderDeliveryType} the Product Order DeliveryType was deleted
	 * 
	 * 
	 */
	@RequestMapping(//
			value = "/{orderDeliveryTypeId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Order DeliveryType by id")
	public DataResult<OrderDeliveryType> deleteOrderDeliveryType(@PathVariable(value = "orderDeliveryTypeId") int orderDeliveryTypeId) {
		DataResult<OrderDeliveryType> dataResult = new DataResult<>();
		OrderDeliveryType orderDeliveryType = mOrderDeliveryTypeRepository.findById(orderDeliveryTypeId).get();
		mOrderDeliveryTypeRepository.deleteById(orderDeliveryTypeId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(orderDeliveryType);
		return dataResult;
	}
}
