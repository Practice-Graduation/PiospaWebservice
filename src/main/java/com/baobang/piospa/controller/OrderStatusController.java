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

import com.baobang.piospa.entities.OrderStatus;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.OrderStatusRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
 * @author BaoBang
 * @Created Apr 25, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.ORDER_STATUS_PATH)
public class OrderStatusController {

	@Autowired
	OrderStatusRepository mOrderStatusRepository;

	/**
	 * @api {get} / Request OrderStatus information
	 * @apiName getAll
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Order Status
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Order Status")
	public DataResult<List<OrderStatus>> getAll() {

		List<OrderStatus> OrderStatuss = mOrderStatusRepository.findAll();

		return new DataResult<List<OrderStatus>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, OrderStatuss);
	}

	/**
	 * @api {get} /{orderStatusId} Request Order Status information
	 * @apiName getOrderStatusById
	 * @apiGroup Order
	 * 
	 * @apiParam {orderStatusId} id Order Status unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderStatus} the Order Status was got
	 * 
	 */
	@RequestMapping(//
			value = "/{orderStatusId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get OrderStatus by id")
	public DataResult<OrderStatus> getOrderStatusById(@PathVariable(value = "orderStatusId") int orderStatusId) {
		DataResult<OrderStatus> result = new DataResult<>();
		OrderStatus OrderStatus = mOrderStatusRepository.findById(orderStatusId).get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(OrderStatus);
		return result;
	}

	/**
	 * @api {post} / Create a new Product OrderStatus
	 * @apiName createOrderStatus
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderStatus} the new Order Status was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new OrderStatus")
	public DataResult<OrderStatus> createOrderStatus(@RequestBody OrderStatus orderStatus) {
		DataResult<OrderStatus> result = new DataResult<>();

		Date date = new Date();
		orderStatus.setOrderStatusId(0);
		orderStatus.setCreatedAt(date);
		orderStatus.setUpdatedAt(date);
		orderStatus = mOrderStatusRepository.save(orderStatus);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(orderStatus);
		return result;
	}
	
	/**
	 * @api {put}/{orderStatusId} update Order Status by id
	 * @apiName updateOrderStatus
	 * @apiGroup Order
	 * 
	 * @apiParam {OrderStatusId} id Product OrderStatus unique ID.
	 * @apiBody {OrderStatus} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderStatus} the new Product OrderStatus was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{orderStatusId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Order Status by id")
	public DataResult<OrderStatus> updateOrderStatus(@PathVariable(value = "orderStatusId") int orderStatusId,
			@RequestBody OrderStatus orderStatus) {
		DataResult<OrderStatus> result;
		Optional<OrderStatus> option = mOrderStatusRepository.findById(orderStatusId);

		OrderStatus newOrderStatus = option.get();

		newOrderStatus.setIsActive(orderStatus.getIsActive());
		newOrderStatus.setOrderStatusName(orderStatus.getOrderStatusName());
		newOrderStatus.setCreatedBy(orderStatus.getCreatedBy());
		newOrderStatus.setOrderStatusDescription(orderStatus.getOrderStatusDescription());
		newOrderStatus.setUpdatedBy(orderStatus.getUpdatedBy());
		newOrderStatus.setUpdatedAt(new Date());

		newOrderStatus = mOrderStatusRepository.save(newOrderStatus);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newOrderStatus);

		return result;
	}

	/**
	 * @api {delete}/{orderStatusId} delete Order Status by id
	 * @apiName deleteOrderStatus
	 * @apiGroup Orders
	 * 
	 * @apiParam {orderStatusId} id Product Order Status unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderStatus} the Product Order Status was deleted
	 * 
	 * 
	 */
	@RequestMapping(//
			value = "/{orderStatusId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Order Status by id")
	public DataResult<OrderStatus> deleteOrderStatus(@PathVariable(value = "orderStatusId") int orderStatusId) {
		DataResult<OrderStatus> dataResult = new DataResult<>();
		OrderStatus orderStatus = mOrderStatusRepository.findById(orderStatusId).get();
		mOrderStatusRepository.deleteById(orderStatusId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(orderStatus);
		return dataResult;
	}


}
