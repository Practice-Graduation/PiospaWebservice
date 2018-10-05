package com.baobang.piospa.controller.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baobang.piospa.entities.Order;
import com.baobang.piospa.entities.OrderProduct;
import com.baobang.piospa.entities.OrderStatus;
import com.baobang.piospa.entities.Product;
import com.baobang.piospa.model.CancelOrderBody;
import com.baobang.piospa.model.CartItemProduct;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.model.OrderBodyRequest;
import com.baobang.piospa.model.OrderCustomerStatusBodyRequest;
import com.baobang.piospa.model.OrderObject;
import com.baobang.piospa.model.OrderProductObject;
import com.baobang.piospa.model.OrderResultResponse;
import com.baobang.piospa.repositories.OrderProductRepository;
import com.baobang.piospa.repositories.OrderRepository;
import com.baobang.piospa.repositories.OrderStatusRepository;
import com.baobang.piospa.repositories.ProductRepository;
import com.baobang.piospa.utils.AppConstants;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
 * @author BaoBang
 * @Created Apr 26, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.ORDER_PATH)
public class OrderController {

	@Autowired
	OrderRepository mOrderRepository;
	@Autowired
	ProductRepository mProductRepository;
	@Autowired
	OrderProductRepository mOrderProductRepository;
	@Autowired
	OrderStatusRepository mOrderStatusRepository;

	/**
	 * @api {get} / Request Order information
	 * @apiName getAll
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the Order of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Orders
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Orders")
	public DataResult<List<Order>> getAll() {

		List<Order> orders = mOrderRepository.findAll();

		return new DataResult<List<Order>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, orders);
	}

	/**
	 * @api {get} / Request Order information
	 * @apiName getOrderByStatus
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * @apiBody {customerId, ordetStatusId}
	 * 
	 * @apiSuccess {Integer} the Order of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Orders
	 * 
	 */
	@RequestMapping(//
			value = "/customer-order-status", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Orders")
	public DataResult<List<Order>> getOrderByStatus(@RequestBody OrderCustomerStatusBodyRequest orderBodyRequester) {

		List<Order> orders = mOrderRepository.findOrderByStatus(orderBodyRequester.getOrderStatusId(),
				orderBodyRequester.getCustomerId());
		if (orders == null)
			orders = new ArrayList<>();
		return new DataResult<List<Order>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, orders);
	}

	/**
	 * @api {get} /{orderId} Request Order information
	 * @apiName getOrderById
	 * @apiGroup Order qaer0-po/
	 * @apiParam {orderId} id Order DeliveryStatus unique ID.
	 * 
	 * @apiSuccess {Integer} the order of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Order} the Order was got
	 * 
	 */
	@RequestMapping(//
			value = "/{orderId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Order by id")
	public DataResult<Order> getOrderById(@PathVariable(value = "orderId") int orderId) {
		DataResult<Order> result = new DataResult<>();
		Order order = mOrderRepository.findById(orderId).get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(order);
		return result;
	}

	/**
	 * @api {get} /code/{orderCode} Request Order information
	 * @apiName getOrderByCode
	 * @apiGroup Order
	 * @apiParam {orderCode} id Order DeliveryStatus unique code.
	 * 
	 * @apiSuccess {Integer} the order of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Order} the Order was got
	 * 
	 */
	// @Transactional
	@RequestMapping(//
			value = "/code/{orderId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Order by id")
	public DataResult<Order> getOrderByCode(@PathVariable(value = "orderId") int orderId) {
		DataResult<Order> result = new DataResult<>();
		Order order = mOrderRepository.findById(orderId).get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(order);
		return result;
	}

	/**
	 * @api {get} /{orderId}/order-product-service-price Request Order information
	 * @apiName getOrderProductAndServicePrice
	 * @apiGroup Order
	 * @apiParam {orderId} id Order DeliveryStatus unique ID.
	 * 
	 * @apiSuccess {Integer} the order of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array[OrderProduct], array[ServicePrice]} the OrderProducts and
	 *             ServicePrices was got
	 * 
	 */
	@RequestMapping(//
			value = "/{orderId}/order-product-service-price", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "get Order Product And Service Price by order id")
	public DataResult<OrderResultResponse> getOrderProductAndServicePrice(
			@PathVariable(value = "orderId") int orderId) {
		DataResult<OrderResultResponse> result = new DataResult<>();
		Order order = mOrderRepository.findById(orderId).get();

		OrderResultResponse response = new OrderResultResponse();
		response.setOrderProducts(order.getOrderProducts());
		

		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(response);
		return result;
	}

	/**
	 * @throws Exception
	 * @api {post} / Create a new Order
	 * @apiName createOrder
	 * @apiGroup Order
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the DeliveryStatus of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {OrderDeliveryStatus} the new Order DeliveryStatus was created
	 * 
	 */
	@Transactional
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new Order")
	public DataResult<Order> createOrder(@RequestBody OrderBodyRequest orderBodyRequester) throws Exception {
		DataResult<Order> result = new DataResult<>();

		OrderStatus orderStatus = mOrderStatusRepository.findById(AppConstants.ORDER_STATUS).get();
		Order temp = new Order();
		Date date = new Date();
		temp = orderBodyRequester.getOrder();
		temp.setOrderId(0);
		temp.getTax().setTaxId(AppConstants.TAX_ID);
		temp.getOrderDeliveryStatus().setOrderDeliveryStatusId(AppConstants.ORDER_DELIVERY_STATUS);
		temp.setOrderStatus(orderStatus);
		temp.setCreatedAt(date);
		temp.setUpdatedAt(date);

		// insert order and get order was inserted
		temp = mOrderRepository.save(temp);
		Product product;
		for (CartItemProduct item : orderBodyRequester.getCartShopping().getCartItemProducts()) {
			OrderProduct orderProduct = new OrderProduct();
			product = mProductRepository.findById(item.getProductId()).get();

			orderProduct.setOrder(temp);
			orderProduct.setProduct(product);
			orderProduct.setNumber(item.getNumber());
			orderProduct.setPrice(product.getPrice());
			orderProduct.setTotal(item.getNumber() * product.getPrice());

			// insert order product and add to order
			orderProduct = mOrderProductRepository.save(orderProduct);
			temp.addOrderProduct(orderProduct);
		}
		temp.caculate();
		temp = mOrderRepository.save(temp);

		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(temp);
		return result;
	}

	@RequestMapping(//
			value = "/cancel", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Cancel Order by id")
	public DataResult<Order> cancelOrder(@RequestBody CancelOrderBody body) {
		DataResult<Order> result = new DataResult<>();

		Order order = mOrderRepository.findById(body.getOrderId()).get();
		if (order.getOrderStatus().getOrderStatusId() == AppConstants.ORDER_PAYMENT) {

			result.setMessage("Đơn hàng đã thanh toán, không thể hủy");
		} else {
			OrderStatus orderStatus = mOrderStatusRepository.findById(AppConstants.ORDER_CANCEL).get();
			order.setOrderStatus(orderStatus);
			order = mOrderRepository.save(order);

			result.setMessage(MessageResponse.SUCCESSED);

		}

		result.setStatusCode(HttpStatus.OK.value());
		result.setData(order);
		return result;
	}

	/**
	 * @api {put}/{orderId} update Order by id
	 * @apiName updateOrder
	 * @apiGroup Order
	 * 
	 * @apiParam {orderId} id Order unique ID.
	 * @apiBody {Order} the info of order need to update
	 * 
	 * @apiSuccess {Integer} the DeliveryStatus of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Order} the new Order was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{orderId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Order by id")
	public DataResult<Order> updateOrder(@PathVariable(value = "orderId") int orderId, @RequestBody Order order) {
		DataResult<Order> result;
		Optional<Order> option = mOrderRepository.findById(orderId);

		Order newOrder = option.get();

		newOrder.setCustomer(order.getCustomer());
		newOrder.setAddress(order.getAddress());
		newOrder.setAddressDelivery(order.getAddressDelivery());
		newOrder.setEmail(order.getEmail());
		newOrder.setOrderPaymentType(order.getOrderPaymentType());

		if (order.getOrderStatus() != null) {
			OrderStatus orderStatus = mOrderStatusRepository.findById(order.getOrderStatus().getOrderStatusId()).get();
			newOrder.setOrderStatus(orderStatus);

		}

		newOrder.setOrderDeliveryType(order.getOrderDeliveryType());
		newOrder.setDeliveryCost(order.getDeliveryCost());
		newOrder.setOrderDeliveryStatus(order.getOrderDeliveryStatus());

		newOrder.setTotal(order.getTotal());
		newOrder.setDiscount(order.getDiscount());
		newOrder.setSubTotal(order.getSubTotal());
		newOrder.setUpdatedAt(new Date());

		newOrder = mOrderRepository.save(newOrder);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newOrder);

		return result;
	}

	/**
	 * @api {get} / Request Order information
	 * @apiName getOrderByCustomerId
	 * @apiGroup Order
	 * 
	 * @apiParam {customerId} the unique id of customer
	 * 
	 * @apiSuccess {Integer} the Order of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Orders
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			value = "/customer/{customerId}", //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Orders")
	public DataResult<List<OrderObject>> getCustomerId(@PathVariable(value = "customerId") int customerId) {

		List<Order> orders = mOrderRepository.findOrderByCustomerId(customerId);
		List<OrderObject> list = new ArrayList<>();
		for (Order order : orders) {
			List<OrderProductObject> orderProductObjects = getOrderProduct(order);
			list.add(new OrderObject(order, orderProductObjects, new ArrayList<>()));
		}
		return new DataResult<List<OrderObject>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, list);
	}
	private List<OrderProductObject> getOrderProduct(Order order) {
		// TODO Auto-generated method stub
		List<OrderProductObject> orders = new ArrayList<>();
		for (OrderProduct orderProduct : order.getOrderProducts()) {
			orders.add(new OrderProductObject(orderProduct));
		}
		return orders;
	}

}
