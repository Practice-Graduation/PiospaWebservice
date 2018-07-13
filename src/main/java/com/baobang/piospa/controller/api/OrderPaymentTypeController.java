package com.baobang.piospa.controller.api;

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

import com.baobang.piospa.entities.OrderPaymentType;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.OrderPaymentTypeRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created Apr 25, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.ORDER_PAYMENT_TYPE_PATH)
public class OrderPaymentTypeController {
	@Autowired
	OrderPaymentTypeRepository mOrderPaymentTypeRepository;

	/**
	 * @api {get} / Request Order PaymentType information
	 * @apiName getAll
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the PaymentType of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Order Payment Types
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Order PaymentType")
	public DataResult<List<OrderPaymentType>> getAll() {

		List<OrderPaymentType> OrderPaymentTypes = mOrderPaymentTypeRepository.findAll();

		return new DataResult<List<OrderPaymentType>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, OrderPaymentTypes);
	}

	/**
	 * @api {get} /{orderPaymentTypeId} Request Order PaymentType information
	 * @apiName getOrderPaymentTypeById
	 * @apiGroup Order
	 * 
	 * @apiParam {orderPaymentTypeId} id Order PaymentType unique ID.
	 * 
	 * @apiSuccess {Integer} the PaymentType of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderPaymentType} the Order PaymentType was got
	 * 
	 */
	@RequestMapping(//
			value = "/{orderPaymentTypeId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get OrderPaymentType by id")
	public DataResult<OrderPaymentType> getOrderPaymentTypeById(@PathVariable(value = "orderPaymentTypeId") int orderPaymentTypeId) {
		DataResult<OrderPaymentType> result = new DataResult<>();
		OrderPaymentType OrderPaymentType = mOrderPaymentTypeRepository.findById(orderPaymentTypeId).get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(OrderPaymentType);
		return result;
	}

	/**
	 * @api {post} / Create a new Product OrderPaymentType
	 * @apiName createOrderPaymentType
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the PaymentType of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderPaymentType} the new Order PaymentType was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new OrderPaymentType")
	public DataResult<OrderPaymentType> createOrderPaymentType(@RequestBody OrderPaymentType orderPaymentType) {
		DataResult<OrderPaymentType> result = new DataResult<>();

		Date date = new Date();
		orderPaymentType.setOrderPaymentTypeId(0);
		orderPaymentType.setCreatedAt(date);
		orderPaymentType.setUpdatedAt(date);
		orderPaymentType = mOrderPaymentTypeRepository.save(orderPaymentType);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(orderPaymentType);
		return result;
	}
	
	/**
	 * @api {put}/{orderPaymentTypeId} update Order PaymentType by id
	 * @apiName updateOrderPaymentType
	 * @apiGroup Order
	 * 
	 * @apiParam {OrderPaymentTypeId} id Product OrderPaymentType unique ID.
	 * @apiBody {OrderPaymentType} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the PaymentType of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderPaymentType} the new Product OrderPaymentType was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{orderPaymentTypeId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Order PaymentType by id")
	public DataResult<OrderPaymentType> updateOrderPaymentType(@PathVariable(value = "orderPaymentTypeId") int orderPaymentTypeId,
			@RequestBody OrderPaymentType orderPaymentType) {
		DataResult<OrderPaymentType> result;
		Optional<OrderPaymentType> option = mOrderPaymentTypeRepository.findById(orderPaymentTypeId);

		OrderPaymentType newOrderPaymentType = option.get();

		newOrderPaymentType.setIsActive(orderPaymentType.getIsActive());
		newOrderPaymentType.setOrderPaymentTypeName(orderPaymentType.getOrderPaymentTypeName());
		newOrderPaymentType.setCreatedBy(orderPaymentType.getCreatedBy());
		newOrderPaymentType.setOrderPaymentTypeDescription(orderPaymentType.getOrderPaymentTypeDescription());
		newOrderPaymentType.setUpdatedBy(orderPaymentType.getUpdatedBy());
		newOrderPaymentType.setUpdatedAt(new Date());

		newOrderPaymentType = mOrderPaymentTypeRepository.save(newOrderPaymentType);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newOrderPaymentType);

		return result;
	}

	/**
	 * @api {delete}/{orderPaymentTypeId} delete Order PaymentType by id
	 * @apiName deleteOrderPaymentType
	 * @apiGroup Orders
	 * 
	 * @apiParam {orderPaymentTypeId} id Product Order PaymentType unique ID.
	 * 
	 * @apiSuccess {Integer} the PaymentType of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderPaymentType} the Product Order PaymentType was deleted
	 * 
	 * 
	 */
	@RequestMapping(//
			value = "/{orderPaymentTypeId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Order PaymentType by id")
	public DataResult<OrderPaymentType> deleteOrderPaymentType(@PathVariable(value = "orderPaymentTypeId") int orderPaymentTypeId) {
		DataResult<OrderPaymentType> dataResult = new DataResult<>();
		OrderPaymentType orderPaymentType = mOrderPaymentTypeRepository.findById(orderPaymentTypeId).get();
		mOrderPaymentTypeRepository.deleteById(orderPaymentTypeId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(orderPaymentType);
		return dataResult;
	}

}
