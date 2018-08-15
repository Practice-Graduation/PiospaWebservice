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

import com.baobang.piospa.entities.OrderProduct;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.OrderProductRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
 * @author BaoBang
 * @Created Apr 26, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.ORDER_PRODUCT_PATH)
public class OrderProductController {

	@Autowired
	OrderProductRepository mOrderProductRepository;

	/**
	 * @api {get} / Request OrderProduct information
	 * @apiName getAll
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the OrderProduct of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list OrderProducts
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all OrderProducts")
	public DataResult<List<OrderProduct>> getAll() {

		List<OrderProduct> OrderProducts = mOrderProductRepository.findAll();

		return new DataResult<List<OrderProduct>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, OrderProducts);
	}
	
	/**
	 * @api {get} /{orderId} Request OrderProduct information
	 * @apiName getOrderProductByOrderId
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the OrderProduct of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list OrderProducts
	 * 
	 */
	@RequestMapping(//
			value = "/{orderId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "get OrderProducts By Order Id")
	public DataResult<List<OrderProduct>> getOrderProductByOrderId(@PathVariable(value = "orderId") int orderId) {

		List<OrderProduct> OrderProducts = mOrderProductRepository.findOrderProductByOrderId(orderId);

		return new DataResult<List<OrderProduct>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, OrderProducts);
	}


	/**
	 * @api {get} /{OrderProductId} Request OrderProduct information
	 * @apiName getOrderProductById
	 * @apiGroup OrderProduct
	 * 
	 * @apiParam {OrderProductId} id OrderProduct DeliveryStatus unique ID.
	 * 
	 * @apiSuccess {Integer} the OrderProduct of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderProduct} the OrderProduct was got
	 * 
	 */
//	@RequestMapping(//
//			value = "/{orderProductId}", //
//			method = RequestMethod.GET, //
//			produces = { MediaType.APPLICATION_JSON_VALUE })
//	@ApiOperation(value = "Get OrderProduct by id")
//	public DataResult<OrderProduct> getOrderProductưById(@PathVariable(value = "orderProductId") int orderProductId) {
//		DataResult<OrderProduct> result = new DataResult<>();
//		OrderProduct OrderProduct = mOrderProductRepository.findById(orderProductId).get();
//		result.setMessage(MessageResponse.SUCCESSED);
//		result.setStatusCode(HttpStatus.OK.value());
//		result.setData(OrderProduct);
//		return result;
//	}

	/**
	 * @api {post} / Create a new OrderProduct
	 * @apiName createOrderProductApi
	 * @apiGroup OrderProduct
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the DeliveryStatus of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderProductDeliveryStatus} the new OrderProduct DeliveryStatus
	 *             was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new OrderProduct")
	public DataResult<OrderProduct> createOrderProductApi(@RequestBody OrderProduct orderProduct) {
		DataResult<OrderProduct> result = new DataResult<>();

		
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(orderProduct);
		return result;
	}
	public OrderProduct createOrderProduct(OrderProduct orderProduct) {
		Date date = new Date();
		orderProduct.setOrderProductId(0);
		orderProduct = mOrderProductRepository.save(orderProduct);
		return orderProduct;
	}
	/**
	 * @api {put}/{orderProductId} update OrderProduct by id
	 * @apiName updateOrderProduct
	 * @apiGroup OrderProduct
	 * 
	 * @apiParam {OrderProductId} id OrderProduct unique ID.
	 * @apiBody {OrderProduct} the info of OrderProduct need to update
	 * 
	 * @apiSuccess {Integer} the DeliveryStatus of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderProduct} the new OrderProduct was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{orderProductId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update OrderProduct by id")
	public DataResult<OrderProduct> updateOrderProduct(@PathVariable(value = "orderProductId") int orderProductId,
			@RequestBody OrderProduct orderProduct) {
		DataResult<OrderProduct> result;
		Optional<OrderProduct> option = mOrderProductRepository.findById(orderProductId);
		OrderProduct newOrderProduct = option.get();
		
		newOrderProduct.setOrder(orderProduct.getOrder());
		newOrderProduct.setProduct(orderProduct.getProduct());
		newOrderProduct.setPrice(orderProduct.getPrice());
		newOrderProduct.setNumber(orderProduct.getNumber());
		newOrderProduct.setDiscount(orderProduct.getDiscount());
		newOrderProduct.setTotal(orderProduct.getTotal());
		
		newOrderProduct = mOrderProductRepository.save(newOrderProduct);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newOrderProduct);

		return result;
	}

	/**
	 * @api {delete} /{orderProductId} delete Order information
	 * @apiName deleteOrderProduct
	 * @apiGroup Order
	 * 
	 * @apiParam {orderProductId} id Order unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Order} the order  was created
	 * 
	 */
	@RequestMapping(//
			value = "/{orderProductId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Order product by id")
	public DataResult<OrderProduct> deleteOrderProduct(
			@PathVariable(value = "orderProductId") int orderProductId) {

		DataResult<OrderProduct> result = new DataResult<>();
		OrderProduct orderProduct = mOrderProductRepository.findById(orderProductId).get();

		mOrderProductRepository.delete(orderProduct);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(orderProduct);
		return result;
	}

}
