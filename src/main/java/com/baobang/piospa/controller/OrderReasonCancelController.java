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

import com.baobang.piospa.entities.OrderReasonCancel;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.OrderReasonCancelRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created Apr 26, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.ORDER_REASON_CANCEL_PATH)
public class OrderReasonCancelController {
	@Autowired
	OrderReasonCancelRepository mOrderReasonCancelRepository;

	/**
	 * @api {get} / Request Order Reason Cancel information
	 * @apiName getAll
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the Reason Cancel of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Order reason cancel
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Order ReasonCancel")
	public DataResult<List<OrderReasonCancel>> getAll() {

		List<OrderReasonCancel> orderReasonCancels = mOrderReasonCancelRepository.findAll();

		return new DataResult<List<OrderReasonCancel>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED,
				orderReasonCancels);
	}

	/**
	 * @api {get} /{orderReasonCancelId} Request Order ReasonCancel information
	 * @apiName getOrderReasonCancelById
	 * @apiGroup Order
	 * 
	 * @apiParam {orderReasonCancelId} id Order ReasonCancel unique ID.
	 * 
	 * @apiSuccess {Integer} the ReasonCancel of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderReasonCancel} the Order ReasonCancel was got
	 * 
	 */
	@RequestMapping(//
			value = "/{orderReasonCancelId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get OrderReasonCancel by id")
	public DataResult<OrderReasonCancel> getOrderReasonCancelById(
			@PathVariable(value = "orderReasonCancelId") int orderReasonCancelId) {
		DataResult<OrderReasonCancel> result = new DataResult<>();
		OrderReasonCancel OrderReasonCancel = mOrderReasonCancelRepository.findById(orderReasonCancelId).get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(OrderReasonCancel);
		return result;
	}

	/**
	 * @api {post} / Create a new Product OrderReasonCancel
	 * @apiName createOrderReasonCancel
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the ReasonCancel of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderReasonCancel} the new Order ReasonCancel was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new OrderReasonCancel")
	public DataResult<OrderReasonCancel> createOrderReasonCancel(
			@RequestBody OrderReasonCancel orderReasonCancel) {
		DataResult<OrderReasonCancel> result = new DataResult<>();

		Date date = new Date();
		orderReasonCancel.setOrderReasonCancelId(0);
		orderReasonCancel.setCreatedAt(date);
		orderReasonCancel.setUpdatedAt(date);
		orderReasonCancel = mOrderReasonCancelRepository.save(orderReasonCancel);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(orderReasonCancel);
		return result;
	}

	/**
	 * @api {put}/{orderReasonCancelId} update Order ReasonCancel by id
	 * @apiName updateOrderReasonCancel
	 * @apiGroup Order
	 * 
	 * @apiParam {OrderReasonCancelId} id Product OrderReasonCancel unique ID.
	 * @apiBody {OrderReasonCancel} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the ReasonCancel of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderReasonCancel} the new Product OrderReasonCancel was
	 *             updated
	 * 
	 */
	@RequestMapping(//
			value = "/{orderReasonCancelId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Order ReasonCancel by id")
	public DataResult<OrderReasonCancel> updateOrderReasonCancel(
			@PathVariable(value = "orderReasonCancelId") int orderReasonCancelId,
			@RequestBody OrderReasonCancel orderReasonCancel) {
		DataResult<OrderReasonCancel> result;
		Optional<OrderReasonCancel> option = mOrderReasonCancelRepository.findById(orderReasonCancelId);

		OrderReasonCancel newOrderReasonCancel = option.get();

		newOrderReasonCancel.setOrderReasonCancelName(orderReasonCancel.getOrderReasonCancelName());
		newOrderReasonCancel.setOrderReasonCancelDescripton(orderReasonCancel.getOrderReasonCancelDescripton());
		newOrderReasonCancel.setIsActive(orderReasonCancel.getIsActive());
		newOrderReasonCancel.setCreatedBy(orderReasonCancel.getCreatedBy());
		newOrderReasonCancel.setUpdatedBy(orderReasonCancel.getUpdatedBy());
		newOrderReasonCancel.setUpdatedAt(new Date());

		newOrderReasonCancel = mOrderReasonCancelRepository.save(newOrderReasonCancel);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newOrderReasonCancel);

		return result;
	}

	/**
	 * @api {delete}/{orderReasonCancelId} delete Order ReasonCancel by id
	 * @apiName deleteOrderReasonCancel
	 * @apiGroup Orders
	 * 
	 * @apiParam {orderReasonCancelId} id Product Order ReasonCancel unique ID.
	 * 
	 * @apiSuccess {Integer} the ReasonCancel of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderReasonCancel} the Product Order ReasonCancel was
	 *             deleted
	 * 
	 * 
	 */
	@RequestMapping(//
			value = "/{orderReasonCancelId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Order ReasonCancel by id")
	public DataResult<OrderReasonCancel> deleteOrderReasonCancel(
			@PathVariable(value = "orderReasonCancelId") int orderReasonCancelId) {
		DataResult<OrderReasonCancel> dataResult = new DataResult<>();
		OrderReasonCancel orderReasonCancel = mOrderReasonCancelRepository.findById(orderReasonCancelId).get();
		mOrderReasonCancelRepository.deleteById(orderReasonCancelId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(orderReasonCancel);
		return dataResult;
	}
}
