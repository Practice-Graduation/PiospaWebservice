package com.baobang.piospa.controller.admin;

import java.security.Principal;
import java.util.ArrayList;
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
import com.baobang.piospa.entities.ServicePackage;
import com.baobang.piospa.entities.ServicePackageDetail;
import com.baobang.piospa.entities.Staff;
import com.baobang.piospa.repositories.ServicePackageDetailRepository;
import com.baobang.piospa.repositories.ServicePackageRepository;
import com.baobang.piospa.repositories.ServicePriceRepository;
import com.baobang.piospa.repositories.ServiceRepository;
import com.baobang.piospa.repositories.StaffRepository;

/**
 * @author BaoBang
 * @Created Jul 19, 2018
 * 
 */
@Controller
public class ServicePackageAdminController {

	@Autowired
	ServicePackageRepository mServicePackageRepository;

	@Autowired
	ServicePackageDetailRepository mServicePackageDetailRepository;

	@Autowired
	ServiceRepository mServiceRepository;

	@Autowired
	ServicePriceRepository mServicePriceRepository;
	@Autowired
	StaffRepository mStaffRepository;

	@RequestMapping(value = "admin/service-package", method = RequestMethod.GET)
	public String servicePackageList(Model model) {
		List<ServicePackage> liProducts = mServicePackageRepository.findAll();
		Collections.reverse(liProducts);
		
		model.addAttribute("result", liProducts);
		return "service-package";
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "admin/remove-service-package", method = RequestMethod.GET)
	public String removeProduct(@RequestParam(name = "product_id", required = true) int productId) {

		System.out.println("## AJAX reomve from csdl: " + productId);
		ServicePackage product = mServicePackageRepository.findById(productId).get();
		if (product != null) {
			try {
				mServicePackageRepository.deleteById(productId);
			} catch (Exception e) {
				return "false";
			}

		} else {
			return "false";
		}
		return "true";
	}

	@Transactional
	@RequestMapping(value = "admin/add-service-package")
	public String addProduct(Model model, HttpServletRequest request,Principal principal,
			@RequestParam(required = true, name = "productid", defaultValue = "0") int productId,
			@RequestParam(required = true, name = "productname", defaultValue = "") String productName,
			@RequestParam(required = true, name = "productimage", defaultValue = "") String productImage,
			@RequestParam(required = false, name = "var_ids") List<Integer> serviceIds,
			@RequestParam(required = true, name = "post_status", defaultValue = "1") int postStatus) {
		String message = "";
		model.addAttribute("title", "THÊM GÓI DỊCH VỤ");
		if (request.getParameter("submit") != null) {
			if (productImage.trim().length() == 0) {
				message = "Vui lòng chọn ảnh, và upload ảnh";
				loadAttribute(model, productId, productName, productImage, serviceIds, postStatus);
			} else {
				ServicePackage product;
				product = new ServicePackage();

				product.setServicePackageName(productName);
				product.setImage(productImage);
				product.setIsActive(postStatus);
				

				try {
					product = mServicePackageRepository.save(product);
					List<ServicePackageDetail> details = new ArrayList<>();
					// thời tổng thời gian của gói dịch vụ
					int time = 0;
					if (serviceIds != null && serviceIds.size() > 0) {

						for (int i = 0; i < serviceIds.size(); i++) {
							ServicePackageDetail detail = new ServicePackageDetail();
							Service service = mServiceRepository.findById(serviceIds.get(i)).get();
							detail.setService(service);
							detail.setServicePackage(product);

							detail = mServicePackageDetailRepository.save(detail);
							time += Integer.parseInt(service.getServiceTime().getTime());
							details.add(detail);
						}
						product.setTime(time);
						product.setServicePackageDetails(details);
						mServicePackageRepository.save(product);
					} else {
						message = "Vui lòng chọn dịch vụ";
						loadData(model);
						loadAttribute(model, productId, productName, productImage, serviceIds, postStatus);
						model.addAttribute("message", message);
						return "add-service-package";
					}

					model.addAttribute("result", "create");
					loadAttribute(model, 0, "", "", new ArrayList<>(), 1);
				} catch (Exception e) {
					message = e.getMessage();
					loadAttribute(model, productId, productName, productImage, serviceIds, postStatus);
				}

			}

		}
		loadData(model);
		model.addAttribute("message", message);
		return "add-service-package";
	}

	@Transactional
	@RequestMapping(value = "admin/edit-service-package/{id}")
	public String orderDetail(Model model, HttpServletRequest request,Principal principal, @PathVariable("id") int id,
			@RequestParam(required = true, name = "productname", defaultValue = "") String productName,
			@RequestParam(required = true, name = "productimage", defaultValue = "") String productImage,
			@RequestParam(required = false, name = "var_ids") List<Integer> serviceIds,
			@RequestParam(required = true, name = "post_status", defaultValue = "1") int postStatus) {

		ServicePackage product = mServicePackageRepository.findById(id).get();
		String message = "";
		model.addAttribute("title", "CẬP NHẬT GÓI DỊCH VỤ");
		if (request.getParameter("submit") != null) {
			try {
				product.setServicePackageName(productName);
				product.setImage(productImage);
				product.setIsActive(postStatus);
				int staffId = 0;
				if(principal != null) {
					User loginedUser = (User) ((Authentication) principal).getPrincipal();

					Staff staff = mStaffRepository.findByUsername(loginedUser.getUsername());
					staffId = staff.getStaffId();
				}

				if (serviceIds == null) {
					serviceIds = new ArrayList<>();
				}
				product = removeDetail(product, serviceIds);
				product = addDetail(product, serviceIds, staffId);

				return "redirect:/admin/service-package";
			} catch (Exception e) {
				message = e.getMessage();
			}
		}

		List<Integer> arrId = getSerivceIds(product);

		loadAttribute(model, product.getServicePackageId(), product.getServicePackageName(), product.getImage(), arrId, postStatus);

		loadData(model);
		model.addAttribute("message", message);
		return "add-service-package";
	}

	private ServicePackage addDetail(ServicePackage product, List<Integer> serviceIds, int staffId) {

		// danh sách dịch vụ hiện tại
		List<Integer> ids = getSerivceIds(product);

		// chi tiết
		List<ServicePackageDetail> details = new ArrayList<>();
		int time = 0;

		// duyệt list dịch vụ từ request
		// nếu dịch vụ chưa có trong danh sách dịch vụ thì thêm vào
		for (Integer i : serviceIds) {
			Service service = mServiceRepository.findById(i).get();
			time += Integer.parseInt(service.getServiceTime().getTime());
			if (!checkExit(ids, i)) {
				ServicePackageDetail detail = new ServicePackageDetail();
				detail.setService(service);
				detail.setServicePackage(product);
				
				detail = mServicePackageDetailRepository.save(detail);
				details.add(detail);
			}
		}

		product.setTime(time);
		product.setServicePackageDetails(details);
		product = mServicePackageRepository.save(product);
		return product;
	}

	private ServicePackage removeDetail(ServicePackage product, List<Integer> serviceIds) {

		for (ServicePackageDetail detail : product.getServicePackageDetails()) {
			if (!checkExit(serviceIds, detail.getService().getServiceId())) {
				mServicePackageDetailRepository.delete(detail);
			}
		}
		product = mServicePackageRepository.save(product);
		return product;

	}

	private boolean checkExit(List<Integer> serviceIds, int serviceId) {
		for (Integer i : serviceIds) {
			if (i == serviceId) {
				return true;
			}
		}
		return false;
	}

	private List<Integer> getSerivceIds(ServicePackage product) {
		List<Integer> ids = new ArrayList<>();
		if (product.getServicePackageDetails() != null && product.getServicePackageDetails().size() > 0) {
			for (ServicePackageDetail s : product.getServicePackageDetails()) {
				ids.add(s.getService().getServiceId());
			}
		}
		return ids;
	}

	private void loadData(Model model) {
		List<Service> services = mServiceRepository.findAll();
		model.addAttribute("services", services);
	}

	private void loadAttribute(Model model, int productId, String productName, String productImage,
			List<Integer> serviceId, int postStatus) {
		model.addAttribute("productid", productId);
		model.addAttribute("productname", productName);
		model.addAttribute("productimage", productImage);
		model.addAttribute("var_ids", serviceId);
		model.addAttribute("post_status", postStatus);
	}

}
