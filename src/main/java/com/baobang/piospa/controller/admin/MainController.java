package com.baobang.piospa.controller.admin;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baobang.piospa.entities.Order;
import com.baobang.piospa.entities.Product;
import com.baobang.piospa.entities.Service;
import com.baobang.piospa.entities.Staff;
import com.baobang.piospa.repositories.OrderRepository;
import com.baobang.piospa.repositories.ProductRepository;
import com.baobang.piospa.repositories.ServiceRepository;
import com.baobang.piospa.repositories.StaffRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

/**
 * @author BaoBang
 * @Created Jul 11, 2018
 * 
 */
@Controller
public class MainController {

	@Autowired
	StaffRepository mStaffRepository;
	@Autowired
	ProductRepository mProductRepository;
	@Autowired
	OrderRepository mOrderRepository;
	@Autowired
	ServiceRepository mServiceRepository;

	@RequestMapping(value = { "", "/", "/admin" }, method = RequestMethod.GET)
	public String adminPage(Model model, Principal principal, HttpSession session) {

		if (principal == null) {
			return "login";
		}

		List<Product> products = mProductRepository.findAll();
		List<Order> orders = mOrderRepository.findAll();
		List<Staff> staffs = mStaffRepository.findAll();
		List<Service> services = mServiceRepository.findAll();

		model.addAttribute("totalProduct", products.size());
		model.addAttribute("totalOrder", orders.size());
		model.addAttribute("totalAccount", staffs.size());
		model.addAttribute("totalService", services.size());

		List<Product> newProducts = new ArrayList<>();
		List<Order> newOrders = new ArrayList<>();
		List<Staff> newStaffs = new ArrayList<>();
		List<Service> newServices = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			if (products.size() > 0) {
				newProducts.add(products.get(products.size() - 1));
				products.remove(products.size() - 1);
			}
			if (orders.size() > 0) {
				newOrders.add(orders.get(orders.size() - 1));
				orders.remove(orders.size() - 1);
			}
			if (staffs.size() > 0) {
				newStaffs.add(staffs.get(staffs.size() - 1));
				staffs.remove(staffs.size() - 1);
			}
			if (services.size() > 0) {
				newServices.add(services.get(services.size() - 1));
				services.remove(services.size() - 1);
			}
		}
		model.addAttribute("productList", newProducts);
		model.addAttribute("orderList", newOrders);
		model.addAttribute("accountList", newStaffs);
		model.addAttribute("serviceList", newServices);
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		Staff staff = mStaffRepository.findByUsername(loginedUser.getUsername());
		session.setAttribute("user", staff);

		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model, Principal principal, HttpSession session) {

		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			Staff staff = mStaffRepository.findByUsername(loginedUser.getUsername());
			session.setAttribute("user", staff);
			return "redirect:/admin";
		}

		return "login";
	}

	@RequestMapping(value = "admin/userInfo", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {

		// Sau khi user login thanh cong se co principal
		String userName = principal.getName();

		Staff staff = mStaffRepository.findByUsername(userName);

		model.addAttribute("user", staff);

		return "profile";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		String message = "";
		if (principal != null) {
			message = "Hi " + principal.getName() //
					+ "<br> You do not have permission to access this page!";

		}
		model.addAttribute("message", message);
		return "403";
	}

}
