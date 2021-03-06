package com.baobang.piospa.controller.admin;

import java.security.Principal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobang.piospa.entities.Service;
import com.baobang.piospa.entities.ServiceGroup;
import com.baobang.piospa.entities.ServicePackage;
import com.baobang.piospa.entities.ServicePrice;
import com.baobang.piospa.entities.ServiceType;
import com.baobang.piospa.entities.Staff;
import com.baobang.piospa.repositories.ServiceGroupRepository;
import com.baobang.piospa.repositories.ServicePackageRepository;
import com.baobang.piospa.repositories.ServicePriceRepository;
import com.baobang.piospa.repositories.ServiceRepository;
import com.baobang.piospa.repositories.ServiceTypeRepository;
import com.baobang.piospa.repositories.StaffRepository;

/**
 * @author BaoBang
 * @Created Jul 19, 2018
 * 
 */
@Controller
public class ServicePriceAdminController {

	@Autowired
	ServicePriceRepository mServicePriceRepository;
	@Autowired
	ServiceGroupRepository mServiceGroupRepository;
	@Autowired
	ServiceTypeRepository mServiceTypeRepository;
	@Autowired
	ServiceRepository mServiceRepository;
	@Autowired
	ServicePackageRepository mServicePackageRepository;
	@Autowired
	StaffRepository mStaffRepository;

	@RequestMapping(value = "admin/service-price", method = RequestMethod.GET)
	public String productList(Model model) {
		List<ServicePrice> liProducts = mServicePriceRepository.findAll();
		Collections.reverse(liProducts);
		model.addAttribute("result", liProducts);
		return "service-price";
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "admin/remove-service-price", method = RequestMethod.GET)
	public String removeProduct(@RequestParam(name = "product_id", required = true) int productId) {

		System.out.println("## AJAX reomve from csdl: " + productId);
		ServicePrice product = mServicePriceRepository.findById(productId).get();
		if (product != null) {
			try {
				mServicePriceRepository.deleteById(productId);
			} catch (Exception e) {
				return "false";
			}

		} else {
			return "false";
		}
		return "true";
	}

	@RequestMapping(value = "admin/edit-service-prie/{id}")
	public String editProduct(Model model, HttpServletRequest request, Principal principal, @PathVariable("id") int id,
			@RequestParam(required = true, name = "servicepriceid", defaultValue = "0") int servicePriceId,
			@RequestParam(required = true, name = "servicegroup", defaultValue = "1") int serviceGroup,
			@RequestParam(required = true, name = "servicetype", defaultValue = "1") int serviceType,
			@RequestParam(required = true, name = "service", defaultValue = "0") int service,
			@RequestParam(required = true, name = "servicepakage", defaultValue = "0") int servicepPkage,
			@RequestParam(required = true, name = "serviceprice", defaultValue = "0") int servicePrice,
			@RequestParam(required = true, name = "post_status", defaultValue = "1") int postStatus) {
		String message = "";
		model.addAttribute("title", "CẬP NHẬT GIÁ DỊCH VỤ");
		ServicePrice product = mServicePriceRepository.findById(id).get();
		if (request.getParameter("submit") != null) {
			if (service == 0 && servicepPkage == 0) {
				message = "Vui lòng chọn dịch vụ hoặc gói dịch vụ";
				loadAttribute(model, servicePriceId, serviceGroup, serviceType, service, servicepPkage, servicePrice, postStatus);
			} else {

				ServiceGroup sg = mServiceGroupRepository.findById(serviceGroup).get();
				ServiceType st = mServiceTypeRepository.findById(serviceType).get();

				product.setServiceGroup(sg);
				product.setServiceType(st);

				if (serviceType == 1) {
					if (service != 0) {
						Service s = mServiceRepository.findById(service).get();
						product.setService(s);
					} else {
						message = "Vui lòng chọn dịch vụ";
						loadData(model);
						loadAttribute(model, servicePriceId, serviceGroup, serviceType, service, servicepPkage,
								servicePrice, postStatus);
						model.addAttribute("message", message);
						return "add-service-price";
					}
				} else if (serviceType == 2) {
					if (servicepPkage != 0) {
						ServicePackage sp = mServicePackageRepository.findById(servicepPkage).get();
						product.setServicePackage(sp);
					} else {
						message = "Vui lòng chọn gói dịch vụ";
						loadData(model);
						loadAttribute(model, servicePriceId, serviceGroup, serviceType, service, servicepPkage,
								servicePrice, postStatus);
						model.addAttribute("message", message);
						return "add-service-price";
					}
				}

				product.setAllPrice(servicePrice);
				product.setIsActive(postStatus);
				

				try {
					mServicePriceRepository.save(product);
					model.addAttribute("result", "create");
					loadAttribute(model, 0, 0, 0, 0, 0, 0, 1);
				} catch (Exception e) {
					message = e.getMessage();
					loadAttribute(model, servicePriceId, serviceGroup, serviceType, service, servicepPkage,
							servicePrice, postStatus);
				}

			}

		}

		loadData(model);
		loadAttribute(model, product.getServicePriceId(), product.getServiceGroup().getServiceGroupId(),
				product.getServiceType().getServiceTypeId(),
				product.getService() == null ? 0 : product.getService().getServiceId(),
				product.getServicePackage() == null ? 0 : product.getServicePackage().getServicePackageId(),
				product.getAllPrice(),
				product.getIsActive());
		model.addAttribute("message", message);
		return "add-service-price";
	}

	@RequestMapping(value = "admin/add-service-price")
	public String addProduct(Model model, HttpServletRequest request, Principal principal,
			@RequestParam(required = true, name = "servicepriceid", defaultValue = "0") int servicePriceId,
			@RequestParam(required = true, name = "servicegroup", defaultValue = "1") int serviceGroup,
			@RequestParam(required = true, name = "servicetype", defaultValue = "1") int serviceType,
			@RequestParam(required = true, name = "service", defaultValue = "0") int service,
			@RequestParam(required = true, name = "servicepakage", defaultValue = "0") int servicepPkage,
			@RequestParam(required = true, name = "serviceprice", defaultValue = "0") int servicePrice,
			@RequestParam(required = true, name = "post_status", defaultValue = "1") int postStatus) {
		String message = "";
		model.addAttribute("title", "THÊM GIÁ DỊCH VỤ");
		if (request.getParameter("submit") != null) {
			if (service == 0 && servicepPkage == 0) {
				message = "Vui lòng chọn dịch vụ hoặc gói dịch vụ";
				loadAttribute(model, servicePriceId, serviceGroup, serviceType, service, servicepPkage, servicePrice, postStatus);
			} else {
				ServicePrice product;
				product = new ServicePrice();

				ServiceGroup sg = mServiceGroupRepository.findById(serviceGroup).get();
				ServiceType st = mServiceTypeRepository.findById(serviceType).get();

				product.setServiceGroup(sg);
				product.setServiceType(st);

				if (serviceType == 1) {
					if (service != 0) {
						Service s = mServiceRepository.findById(service).get();
						product.setService(s);
					} else {
						message = "Vui lòng chọn dịch vụ";
						loadData(model);
						loadAttribute(model, servicePriceId, serviceGroup, serviceType, service, servicepPkage,
								servicePrice, postStatus);
						model.addAttribute("message", message);
						return "add-service-price";
					}
				} else if (serviceType == 2) {
					if (servicepPkage != 0) {
						ServicePackage sp = mServicePackageRepository.findById(servicepPkage).get();
						product.setServicePackage(sp);
					} else {
						message = "Vui lòng chọn gói dịch vụ";
						loadData(model);
						loadAttribute(model, servicePriceId, serviceGroup, serviceType, service, servicepPkage,
								servicePrice, postStatus);
						model.addAttribute("message", message);
						return "add-service-price";
					}
				}

				product.setAllPrice(servicePrice);
				product.setIsActive(postStatus);
				

				try {
					mServicePriceRepository.save(product);
					model.addAttribute("result", "create");
					loadAttribute(model, 0, 0, 0, 0, 0, 0, 1);
				} catch (Exception e) {
					message = e.getMessage();
					loadAttribute(model, servicePriceId, serviceGroup, serviceType, service, servicepPkage,
							servicePrice, postStatus);
				}

			}

		}

		loadData(model);
		loadAttribute(model, servicePriceId, serviceGroup, serviceType, service, servicepPkage, servicePrice,postStatus);
		model.addAttribute("message", message);
		return "add-service-price";
	}

	private void loadAttribute(Model model, int servicePriceId, int serviceGroup, int serviceType, int service,
			int servicepPkage, int servicePrice, int postStatus) {
		model.addAttribute("servicepriceid", servicePriceId);
		model.addAttribute("servicegroup", serviceGroup);
		model.addAttribute("servicetype", serviceType);
		model.addAttribute("service", service);
		model.addAttribute("servicepakage", servicepPkage);
		model.addAttribute("serviceprice", servicePrice);
		model.addAttribute("post_status", postStatus);

	}

	private void loadData(Model model) {
		List<ServiceGroup> groups = mServiceGroupRepository.findAll();
		List<ServiceType> types = mServiceTypeRepository.findAll();
		List<Service> services = mServiceRepository.findAll();
		List<ServicePackage> servicePakages = mServicePackageRepository.findAll();

		model.addAttribute("groups", groups);
		model.addAttribute("types", types);
		model.addAttribute("services", services);
		model.addAttribute("servicepakages", servicePakages);
	}
}
