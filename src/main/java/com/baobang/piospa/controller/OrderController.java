package com.baobang.piospa.controller;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baobang.piospa.entities.Booking;
import com.baobang.piospa.entities.BookingDetail;
import com.baobang.piospa.entities.Order;
import com.baobang.piospa.entities.OrderProduct;
import com.baobang.piospa.entities.Product;
import com.baobang.piospa.entities.ServicePrice;
import com.baobang.piospa.model.CartItemProduct;
import com.baobang.piospa.model.CartItemService;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.model.OrderBodyRequest;
import com.baobang.piospa.model.OrderCustomerStatusBodyRequest;
import com.baobang.piospa.model.OrderResultResponse;
import com.baobang.piospa.repositories.BookingDetailRepository;
import com.baobang.piospa.repositories.BookingRepository;
import com.baobang.piospa.repositories.OrderProductRepository;
import com.baobang.piospa.repositories.OrderRepository;
import com.baobang.piospa.repositories.ProductRepository;
import com.baobang.piospa.repositories.ServicePriceRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;
import com.baobang.piospa.utils.Utils;

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
	BookingRepository mBookingRepository;
	@Autowired
	BookingDetailRepository mBookingDetailRepository;
	@Autowired
	ServicePriceRepository mServicePriceRepository;

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
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Orders")
	public DataResult<List<Order>> getOrderByStatus(@RequestBody OrderCustomerStatusBodyRequest orderBodyRequester) {

		List<Order> orders = mOrderRepository.findOrderByStatus(orderBodyRequester.getOrderStatusId(), orderBodyRequester.getCustomerId());

		return new DataResult<List<Order>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, orders);
	}


	/**
	 * @api {get} /{orderId} Request Order information
	 * @apiName getOrderById
	 * @apiGroup Order
	 * 	qaer0-po/
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
	 * @api {get} /{orderId}/order-product-service-price Request Order information
	 * @apiName getOrderProductAndServicePrice
	 * @apiGroup Order
	 * @apiParam {orderId} id Order DeliveryStatus unique ID.
	 * 
	 * @apiSuccess {Integer} the order of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array[OrderProduct], array[ServicePrice]} the OrderProducts and ServicePrices was got
	 * 
	 */
	@RequestMapping(//
			value = "/{orderId}/order-product-service-price", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "get Order Product And Service Price by order id")
	public DataResult<OrderResultResponse> getOrderProductAndServicePrice(@PathVariable(value = "orderId") int orderId) {
		DataResult<OrderResultResponse> result = new DataResult<>();
		Order order = mOrderRepository.findById(orderId).get();
		
		OrderResultResponse response = new OrderResultResponse();
		response.setOrderProducts(order.getOrderProducts());
		response.setBookingDetails(order.getBooking().getBookingDetails());
		
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(response);
		return result;
	}
	

	/**
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
	public DataResult<Order> createOrder(@RequestBody OrderBodyRequest orderBodyRequester) {
		DataResult<Order> result = new DataResult<>();

		Order temp = mOrderRepository.findByCode(orderBodyRequester.getOrder().getCode());
		String code = Utils.genarateCode();
		if(temp == null) {
			Date date = new Date();
			temp = orderBodyRequester.getOrder();
			temp.setOrderId(0);
			temp.setCode(code);
			temp.setCreatedAt(date);
			temp.setUpdatedAt(date);
			// insert order and get order was inserted
			temp = mOrderRepository.save(temp);
			Product product;
			for(CartItemProduct item : orderBodyRequester.getCartShopping().getCartItemProducts()) {
				OrderProduct orderProduct = new OrderProduct();
				product = mProductRepository.findById(item.getProductId()).get();
				
				orderProduct.setOrder(temp);
				orderProduct.setProduct(product);
				orderProduct.setNumber(item.getNumber());
				orderProduct.setPrice(product.getPrice());
				orderProduct.setTotal(item.getNumber() * product.getPrice());
				orderProduct.setIsDeleted((byte) 0);
				orderProduct.setCreatedBy(temp.getCreatedBy());
				orderProduct.setUpdatedBy(orderProduct.getUpdatedBy());
				orderProduct.setCreatedAt(date);
				orderProduct.setUpdatedAt(date);
				
				// insert order product and add to order
				orderProduct = mOrderProductRepository.save(orderProduct);
				temp.addOrderProduct(orderProduct);
			}
			if(orderBodyRequester.getCartShopping().getCartItemServices().size() > 0) {
				Booking booking = new Booking();
				booking.setOrder(temp);
				booking.setCustomer(temp.getCustomer());
				booking.setCode(code);
				booking.setCreatedAt(date);
				booking.setUpdatedAt(date);
				
				booking = mBookingRepository.save(booking);
				ServicePrice servicePrice;
				for(CartItemService item : orderBodyRequester.getCartShopping().getCartItemServices()) {
					BookingDetail bookingDetail = new  BookingDetail();
					
					servicePrice = mServicePriceRepository.findById(item.getProductId()).get();
					
					bookingDetail.setBooking(booking);
					bookingDetail.setServicePrice(servicePrice);
					bookingDetail.setDateBooking(item.getDateBooking());
					Time time = new Time(item.getDateBooking().getTime());
					bookingDetail.setTimeStart(time);
					bookingDetail.setCreatedAt(date);
					bookingDetail.setUpdatedAt(date);
					
					bookingDetail = mBookingDetailRepository.save(bookingDetail);
					booking.addBookingDetail(bookingDetail);
				}
				
				temp.setBooking(booking);
			}
			temp.caculate();
			temp = mOrderRepository.save(temp);
			
			result.setMessage(MessageResponse.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		}else {
			result.setMessage(MessageResponse.EXITS);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		result.setData(temp);
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
	public DataResult<Order> updateOrder(
			@PathVariable(value = "orderId") int orderId,
			@RequestBody Order order) {
		DataResult<Order> result;
		Optional<Order> option = mOrderRepository.findById(orderId);

		Order newOrder = option.get();
		
		newOrder.setCustomer(order.getCustomer());
		newOrder.setCustomerSource(order.getCustomerSource());
		newOrder.setAddress(order.getAddress());
		newOrder.setAddressDelivery(order.getAddressDelivery());
		newOrder.setEmail(order.getEmail());
		newOrder.setDateDelivery(order.getDateDelivery());
		newOrder.setOrderPaymentType(order.getOrderPaymentType());
		newOrder.setNote(order.getNote());
		newOrder.setOrderStatus(order.getOrderStatus());
		newOrder.setStaffId(order.getStaffId());
		newOrder.setOrderDeliveryType(order.getOrderDeliveryType());
		newOrder.setDeliveryCode(order.getDeliveryCode());
		newOrder.setDeliveryCost(order.getDeliveryCost());
		newOrder.setOrderDeliveryStatus(order.getOrderDeliveryStatus());
		newOrder.setOrderReasonCancel(order.getOrderReasonCancel());
		newOrder.setTotal(order.getTotal());
		newOrder.setDiscount(order.getDiscount());
		newOrder.setSubTotal(order.getSubTotal());
		newOrder.setUpdatedBy(order.getUpdatedBy());
		newOrder.setUpdatedAt(new Date());
		
		newOrder = mOrderRepository.save(newOrder);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newOrder);

		return result;
	}
	
	
}
