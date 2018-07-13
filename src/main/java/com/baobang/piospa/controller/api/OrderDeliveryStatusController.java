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

import com.baobang.piospa.entities.OrderDeliveryStatus;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.OrderDeliveryStatusRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
 * @author BaoBang
 * @Created Apr 25, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.ORDER_DELIVERY_STATUS_PATH)
public class OrderDeliveryStatusController {
	@Autowired
	OrderDeliveryStatusRepository mOrderDeliveryStatusRepository;

	/**
	 * @api {get} / Request Order DeliveryStatus information
	 * @apiName getAll
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the DeliveryStatus of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Order Delivery Statuss
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Order DeliveryStatus")
	public DataResult<List<OrderDeliveryStatus>> getAll() {

		List<OrderDeliveryStatus> OrderDeliveryStatuss = mOrderDeliveryStatusRepository.findAll();

		return new DataResult<List<OrderDeliveryStatus>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED,
				OrderDeliveryStatuss);
	}

	/**
	 * @api {get} /{orderDeliveryStatusId} Request Order DeliveryStatus information
	 * @apiName getOrderDeliveryStatusById
	 * @apiGroup Order
	 * 
	 * @apiParam {orderDeliveryStatusId} id Order DeliveryStatus unique ID.
	 * 
	 * @apiSuccess {Integer} the DeliveryStatus of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderDeliveryStatus} the Order DeliveryStatus was got
	 * 
	 */
	@RequestMapping(//
			value = "/{orderDeliveryStatusId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get OrderDeliveryStatus by id")
	public DataResult<OrderDeliveryStatus> getOrderDeliveryStatusById(
			@PathVariable(value = "orderDeliveryStatusId") int orderDeliveryStatusId) {
		DataResult<OrderDeliveryStatus> result = new DataResult<>();
		OrderDeliveryStatus OrderDeliveryStatus = mOrderDeliveryStatusRepository.findById(orderDeliveryStatusId).get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(OrderDeliveryStatus);
		return result;
	}

	/**
	 * @api {post} / Create a new Product OrderDeliveryStatus
	 * @apiName createOrderDeliveryStatus
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the DeliveryStatus of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderDeliveryStatus} the new Order DeliveryStatus was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new OrderDeliveryStatus")
	public DataResult<OrderDeliveryStatus> createOrderDeliveryStatus(
			@RequestBody OrderDeliveryStatus orderDeliveryStatus) {
		DataResult<OrderDeliveryStatus> result = new DataResult<>();

		Date date = new Date();
		orderDeliveryStatus.setOrderDeliveryStatusId(0);
		orderDeliveryStatus.setCreatedAt(date);
		orderDeliveryStatus.setUpdatedAt(date);
		orderDeliveryStatus = mOrderDeliveryStatusRepository.save(orderDeliveryStatus);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(orderDeliveryStatus);
		return result;
	}

	/**
	 * @api {put}/{orderDeliveryStatusId} update Order DeliveryStatus by id
	 * @apiName updateOrderDeliveryStatus
	 * @apiGroup Order
	 * 
	 * @apiParam {OrderDeliveryStatusId} id Product OrderDeliveryStatus unique ID.
	 * @apiBody {OrderDeliveryStatus} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the DeliveryStatus of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderDeliveryStatus} the new Product OrderDeliveryStatus was
	 *             updated
	 * 
	 */
	@RequestMapping(//
			value = "/{orderDeliveryStatusId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Order DeliveryStatus by id")
	public DataResult<OrderDeliveryStatus> updateOrderDeliveryStatus(
			@PathVariable(value = "orderDeliveryStatusId") int orderDeliveryStatusId,
			@RequestBody OrderDeliveryStatus orderDeliveryStatus) {
		DataResult<OrderDeliveryStatus> result;
		Optional<OrderDeliveryStatus> option = mOrderDeliveryStatusRepository.findById(orderDeliveryStatusId);

		OrderDeliveryStatus newOrderDeliveryStatus = option.get();

		newOrderDeliveryStatus.setOrderDeliveryStatusName(orderDeliveryStatus.getOrderDeliveryStatusName());
		newOrderDeliveryStatus.setOrderDeliveryStatusDescription(orderDeliveryStatus.getOrderDeliveryStatusDescription());
		newOrderDeliveryStatus.setIsActive(orderDeliveryStatus.getIsActive());
		newOrderDeliveryStatus.setCreatedBy(orderDeliveryStatus.getCreatedBy());
		newOrderDeliveryStatus.setUpdatedBy(orderDeliveryStatus.getUpdatedBy());
		newOrderDeliveryStatus.setUpdatedAt(new Date());
		newOrderDeliveryStatus.setIsDelete(orderDeliveryStatus.getIsDelete());

		newOrderDeliveryStatus = mOrderDeliveryStatusRepository.save(newOrderDeliveryStatus);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newOrderDeliveryStatus);

		return result;
	}

	/**
	 * @api {delete}/{orderDeliveryStatusId} delete Order DeliveryStatus by id
	 * @apiName deleteOrderDeliveryStatus
	 * @apiGroup Orders
	 * 
	 * @apiParam {orderDeliveryStatusId} id Product Order DeliveryStatus unique ID.
	 * 
	 * @apiSuccess {Integer} the DeliveryStatus of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderDeliveryStatus} the Product Order DeliveryStatus was
	 *             deleted
	 * 
	 * 
	 */
	@RequestMapping(//
			value = "/{orderDeliveryStatusId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Order DeliveryStatus by id")
	public DataResult<OrderDeliveryStatus> deleteOrderDeliveryStatus(
			@PathVariable(value = "orderDeliveryStatusId") int orderDeliveryStatusId) {
		DataResult<OrderDeliveryStatus> dataResult = new DataResult<>();
		OrderDeliveryStatus orderDeliveryStatus = mOrderDeliveryStatusRepository.findById(orderDeliveryStatusId).get();
		mOrderDeliveryStatusRepository.deleteById(orderDeliveryStatusId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(orderDeliveryStatus);
		return dataResult;
	}
}
