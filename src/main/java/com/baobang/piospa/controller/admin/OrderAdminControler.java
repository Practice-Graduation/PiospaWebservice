package com.baobang.piospa.controller.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobang.piospa.entities.Booking;
import com.baobang.piospa.entities.BookingDetail;
import com.baobang.piospa.entities.Order;
import com.baobang.piospa.entities.OrderProduct;
import com.baobang.piospa.entities.OrderStatus;
import com.baobang.piospa.entities.Product;
import com.baobang.piospa.repositories.BookingDetailRepository;
import com.baobang.piospa.repositories.OrderRepository;
import com.baobang.piospa.repositories.OrderStatusRepository;
import com.baobang.piospa.repositories.ProductRepository;
import com.baobang.piospa.utils.AppConstants;
import com.baobang.piospa.utils.FCM;

/**
 * @author BaoBang
 * @Created Jul 11, 2018
 * 
 */
@Controller
public class OrderAdminControler {

	@Autowired
	OrderRepository mOrderRepository;
	@Autowired
	OrderStatusRepository mOrderStatusRepository;
	@Autowired
	ProductRepository mProductRepository;
	
	@Autowired
	BookingDetailRepository mBookingDetailRepository;

	@RequestMapping(value = "admin/order-list", method = RequestMethod.GET)
	public String orderList(Model model) {
		List<Order> orderList = mOrderRepository.findAll();
		Collections.reverse(orderList);
		model.addAttribute("result", orderList);
		return "order-list";
	}

	@RequestMapping(value = "admin/order-detail/{id}", method = RequestMethod.GET)
	public String orderDetail(Model model, @PathVariable("id") int id) {

		Order order = mOrderRepository.findById(id).get();
		List<OrderProduct> products = order.getOrderProducts();
		List<BookingDetail> bookingDetails;
		Booking booking = order.getBooking();
		if (booking != null) {
			bookingDetails = order.getBooking().getBookingDetails();
		} else {
			bookingDetails = new ArrayList<>();
		}
		model.addAttribute("order", order);
		model.addAttribute("products", products);
		model.addAttribute("bookingDetails", bookingDetails);
		return "order-detail";
	}
	
	@RequestMapping(value = "admin/order-detail/{id}", method = RequestMethod.POST)
	public String changeStatusOrder(Model model, @PathVariable("id") int id,
			@RequestParam(required = true, name = "order_status", defaultValue = "0") int status) {

		Order order = mOrderRepository.findById(id).get();
		OrderStatus orderStatus = mOrderStatusRepository.findById(status).get();
		String message = "";
		boolean isNotify = false;
		// DANH SACH SAN PHAM
		List<OrderProduct> products = order.getOrderProducts();
		// DANH SACH DICH VU
		List<BookingDetail> bookingDetails;
		Booking booking = order.getBooking();
		if (booking != null) {
			bookingDetails = order.getBooking().getBookingDetails();
		} else {
			bookingDetails = new ArrayList<>();
		}
		// KIEM TRA TRANG THAI DON HANG
		if (order.getOrderStatus().getOrderStatusId() != orderStatus.getOrderStatusId()) {
			// nếu đơn hàng hiện tại đã thanh toán
			// cập nhật lại hủy hoặc đợi duyệt
			if (order.getOrderStatus().getOrderStatusId() == AppConstants.ORDER_PAYMENT) {
				// tăng số lượng sản phẩm
				List<OrderProduct> list = order.getOrderProducts();
				for (OrderProduct op : list) {
					Product product = op.getProduct();
					product.setAmount(product.getAmount() + op.getNumber());
					
					mProductRepository.save(product);
				}

			} else {
				// nếu là đơn hàng hiện tại đang chờ duyệt hoặc hủy
				if (orderStatus.getOrderStatusId() == AppConstants.ORDER_PAYMENT) {
					// giảm số lượng
					List<OrderProduct> list = order.getOrderProducts();
					for (OrderProduct op : list) {
						Product product = op.getProduct();
						if (product.getAmount() < op.getNumber()) {
							message = product.getProductName() + " chỉ còn " + product.getAmount();
							model.addAttribute("message", message);
							model.addAttribute("order", order);
							model.addAttribute("products", products);
							model.addAttribute("bookingDetails", bookingDetails);
							return "order-detail";
						}
						product.setAmount(product.getAmount() - op.getNumber());
						mProductRepository.save(product);
					}
				}
			}
			isNotify = true;

		}

		order.setOrderStatus(orderStatus);
		order = mOrderRepository.save(order);
		if(isNotify) {
			FCM.send_FCM_Notification(order.getCustomer().getAccount(), order.getOrderId()+"");
		}
		model.addAttribute("order", order);
		model.addAttribute("products", products);
		model.addAttribute("bookingDetails", bookingDetails);
		return "order-detail";
	}
	
	@ResponseBody
	@RequestMapping(value = "admin/update-booking-detail/{id}", method = RequestMethod.GET)
	public String updateBookingDetail(Model model, @PathVariable("id") int id,
			@RequestParam(required = true, name = "served_status", defaultValue = "0") int status) {
		
		BookingDetail bookingDetail = mBookingDetailRepository.findById(id).get();
		
		bookingDetail.setServedStatus(status);
		
		try {
			mBookingDetailRepository.save(bookingDetail);
			return "true";
		}catch(Exception e) {
			
		}
		return "false";

	}
}
