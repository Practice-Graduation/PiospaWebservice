package com.baobang.piospa.controller.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.baobang.piospa.entities.Booking;
import com.baobang.piospa.entities.BookingDetail;
import com.baobang.piospa.entities.Order;
import com.baobang.piospa.entities.OrderProduct;
import com.baobang.piospa.entities.OrderStatus;
import com.baobang.piospa.entities.Product;
import com.baobang.piospa.entities.Room;
import com.baobang.piospa.entities.ServicePrice;
import com.baobang.piospa.model.BookingDetailObject;
import com.baobang.piospa.model.CancelOrderBody;
import com.baobang.piospa.model.CartItemProduct;
import com.baobang.piospa.model.CartItemService;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.model.OrderBodyRequest;
import com.baobang.piospa.model.OrderCustomerStatusBodyRequest;
import com.baobang.piospa.model.OrderObject;
import com.baobang.piospa.model.OrderProductObject;
import com.baobang.piospa.model.OrderResultResponse;
import com.baobang.piospa.repositories.BookingDetailRepository;
import com.baobang.piospa.repositories.BookingRepository;
import com.baobang.piospa.repositories.OrderProductRepository;
import com.baobang.piospa.repositories.OrderRepository;
import com.baobang.piospa.repositories.OrderStatusRepository;
import com.baobang.piospa.repositories.ProductRepository;
import com.baobang.piospa.repositories.RoomRepository;
import com.baobang.piospa.repositories.ServicePriceRepository;
import com.baobang.piospa.utils.AppConstants;
import com.baobang.piospa.utils.DateTimeUtils;
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
	@Autowired
	OrderStatusRepository mOrderStatusRepository;
	@Autowired
	RoomRepository mRoomRepository;

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
		Booking booking = order.getBooking();
		if (booking != null) {
			response.setBookingDetails(order.getBooking().getBookingDetails());
		} else {
			response.setBookingDetails(new ArrayList<>());
		}

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
		if (orderBodyRequester.getCartShopping().getCartItemServices().size() > 0) {
			Booking booking = new Booking();
			booking.setCustomer(temp.getCustomer());
			booking.setOrder(temp);

			booking = mBookingRepository.save(booking);
			int price = 0, totalNumber = 0;

			if (!checkCanBooking(orderBodyRequester.getCartShopping().getCartItemServices())) {
				result.setMessage("Không thể đặt phòng");
				result.setStatusCode(HttpStatus.NOT_FOUND.value());
				result.setData(null);
				return result;
			}

			ServicePrice servicePrice;
			for (CartItemService item : orderBodyRequester.getCartShopping().getCartItemServices()) {
				BookingDetail bookingDetail = new BookingDetail();
				totalNumber += item.getNumber();
				servicePrice = mServicePriceRepository.findById(item.getProductId()).get();

				price += item.getNumber() * servicePrice.getAllPrice();
				bookingDetail.setBooking(booking);
				bookingDetail.setNumber(item.getNumber());
				bookingDetail.setServicePrice(servicePrice);
				bookingDetail.setDateBooking(item.getDateBooking());

				bookingDetail.setTimeStart(item.getTimeBooking());

				bookingDetail.setRoom(item.getRoom());
				bookingDetail = mBookingDetailRepository.save(bookingDetail);
				booking.addBookingDetail(bookingDetail);
			}
			booking.setPrice(price);
			booking.setNumber(totalNumber);
			booking.setTotal(price - booking.getDiscount());
			temp.setBooking(booking);
		}
		temp.caculate();
		temp = mOrderRepository.save(temp);

		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(temp);
		return result;
	}

	private boolean checkCanBooking(List<CartItemService> cartItemServices) {

		Map<String, List<BookingDetail>> map = new HashMap<>();

		for (CartItemService itemService : cartItemServices) {
			List<BookingDetail> details = mBookingDetailRepository.findByDateAndRoom(itemService.getRoom().getRoomId(),
					itemService.getDateBooking());
			if (details == null) {
				details = new ArrayList<>();
			}
			map.put(itemService.getDateBooking(), details);

		}

		for (CartItemService itemService : cartItemServices) {
			if (!checkCanAddToBookingDetail(map, itemService)) {
				return false;
			} else {
				List<BookingDetail> details = map.get(itemService.getDateBooking());
				BookingDetail bd = new BookingDetail();
				bd.setDateBooking(itemService.getDateBooking());
				bd.setTimeStart(itemService.getTimeBooking());
				bd.setRoom(itemService.getRoom());
				bd.setNumber(itemService.getNumber());
				ServicePrice servicePrice = mServicePriceRepository.findById(itemService.getProductId()).get();

				bd.setServicePrice(servicePrice);
				details.add(bd);
				map.put(itemService.getDateBooking(), details);
				System.out.println(map.get(itemService.getDateBooking()).size() + "");
			}

		}

		// TODO Auto-generated method stub
		return true;
	}

	private boolean checkCanAddToBookingDetail(Map<String, List<BookingDetail>> map, CartItemService itemService) {
		List<BookingDetail> details = map.get(itemService.getDateBooking());
		int count = 0;
		Date dateBooking = DateTimeUtils.getDate(itemService.getDateBooking(), itemService.getTimeBooking());
		for (BookingDetail detail : details) {
			Date start = DateTimeUtils.getDate(detail.getDateBooking(), detail.getTimeStart());

			Date end = new Date(start.getTime());
			int time = getTime(detail.getServicePrice());
			end = DateTimeUtils.addMinute(end, time);
			if (isValidDate(dateBooking, start, end)) {
				count += detail.getNumber();
			}
		}
		
		if (itemService.getNumber() > itemService.getRoom().getRoomLimit()||count >= itemService.getRoom().getRoomLimit()) {

			return false;
		}
		return true;
	}

	private boolean isValidDate(Date dateBooking, Date start, Date end) {
		long timeBooking = dateBooking.getTime();
		long timeStart = start.getTime();
		long timeend = end.getTime();

		if (timeBooking >= timeStart && timeBooking <= timeend) {
			return true;
		}
		return false;

	}

	private int getTime(ServicePrice servicePrice) {
		int time = 0;

		if (servicePrice.getService() != null) {
			time = Integer.parseInt(servicePrice.getService().getServiceTime().getTime());
		} else {
			time = servicePrice.getServicePackage().getTime();
		}

		return time;
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
			List<BookingDetailObject> bookingDetailObjects = getBookingDetail(order);
			list.add(new OrderObject(order, orderProductObjects, bookingDetailObjects));
		}
		return new DataResult<List<OrderObject>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, list);
	}

	private List<BookingDetailObject> getBookingDetail(Order order) {
		// TODO Auto-generated method stub
		List<BookingDetailObject> bookingDetailObjects = new ArrayList<>();
		Booking booking = order.getBooking();
		if (booking != null) {
			for (BookingDetail detail : booking.getBookingDetails()) {
				bookingDetailObjects.add(new BookingDetailObject(detail));
			}
		}

		return bookingDetailObjects;
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
