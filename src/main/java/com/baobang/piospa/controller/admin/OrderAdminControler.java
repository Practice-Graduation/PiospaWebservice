package com.baobang.piospa.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baobang.piospa.entities.Booking;
import com.baobang.piospa.entities.BookingDetail;
import com.baobang.piospa.entities.Order;
import com.baobang.piospa.entities.OrderProduct;
import com.baobang.piospa.entities.OrderStatus;
import com.baobang.piospa.repositories.OrderRepository;
import com.baobang.piospa.repositories.OrderStatusRepository;

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

	
	@RequestMapping(value = "admin/order-list", method = RequestMethod.GET)
	public String orderList(Model model) {
		List<Order> orderList = mOrderRepository.findAll();
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
		order.setOrderStatus(orderStatus);
		order = mOrderRepository.save(order);
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
}
