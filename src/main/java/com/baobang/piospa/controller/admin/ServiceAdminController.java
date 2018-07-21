package com.baobang.piospa.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobang.piospa.entities.Product;
import com.baobang.piospa.entities.ProductGroup;
import com.baobang.piospa.entities.ProductLabel;
import com.baobang.piospa.entities.ProductOrigin;
import com.baobang.piospa.entities.ProductUnit;
import com.baobang.piospa.entities.Service;
import com.baobang.piospa.entities.ServicePackage;
import com.baobang.piospa.entities.ServiceTime;
import com.baobang.piospa.repositories.ServicePackageRepository;
import com.baobang.piospa.repositories.ServiceRepository;
import com.baobang.piospa.repositories.ServiceTimeRepository;
import com.baobang.piospa.utils.Utils;

/**
 * @author BaoBang
 * @Created Jul 18, 2018
 * 
 */
@Controller
public class ServiceAdminController {

	@Autowired
	ServiceRepository mServiceRepository;
	@Autowired
	ServiceTimeRepository mServiceTimeRepository;


	@Transactional
	@ResponseBody
	@RequestMapping(value = "admin/remove-service", method = RequestMethod.GET)
	public String removeServicePackage(@RequestParam(name = "product_id", required = true) int productId) {

		System.out.println("## AJAX reomve from csdl: " + productId);
		Service product = mServiceRepository.findById(productId).get();
		if (product != null) {
			try {
				mServiceRepository.deleteById(productId);
			} catch (Exception e) {
				return "false";
			}

		} else {
			return "false";
		}
		return "true";
	}

	
	@RequestMapping(value = "admin/service", method = RequestMethod.GET)
	public String productList(Model model) {
		List<Service> liProducts = mServiceRepository.findAll();
		model.addAttribute("result", liProducts);
		return "service";
	}
	
	

	@RequestMapping(value = "admin/edit-service/{id}")
	public String orderDetail(Model model, HttpServletRequest request, 
			@PathVariable("id") int id,
			@RequestParam(required = true, name = "productname", defaultValue = "") String productName,
			@RequestParam(required = true, name = "productimage", defaultValue = "") String productImage,
			@RequestParam(required = true, name = "timeId", defaultValue = "1") int timeId,
			@RequestParam(required = true, name = "productdescription", defaultValue = "") String productDescription,
			@RequestParam(required = true, name = "post_status", defaultValue = "1") int post_status) {

		Service product = mServiceRepository.findById(id).get();

		String message = "";
		if (request.getParameter("submit") != null) {

			ServiceTime time = mServiceTimeRepository.findById(timeId).get();

			product.setServiceName(productName);
			product.setImage(productImage);
			product.setDescription(productDescription);
			product.setDetail(productDescription);
			product.setIsActive((byte) post_status);
			product.setServiceTime(time);
			product.setUpdatedAt(new Date());

			try {
				mServiceRepository.save(product);

				model.addAttribute("result", "update");
			} catch (Exception e) {
				message = e.getMessage();
			}
		}

		loadAttribute(model, product.getServiceId(), product.getServiceName(), product.getImage(), product.getServiceTime().getServiceTimeId(), product.getDescription(), product.getIsActive());
		loadData(model);
		model.addAttribute("message", message);
		return "add-service";
	}
	
	@RequestMapping(value = "admin/add-service")
	public String addProduct(Model model, HttpServletRequest request,
			@RequestParam(required = true, name = "productid", defaultValue = "0") int productId,
			@RequestParam(required = true, name = "productname", defaultValue = "") String productName,
			@RequestParam(required = true, name = "productimage", defaultValue = "") String productImage,
			@RequestParam(required = true, name = "timeId", defaultValue = "1") int timeId,
			@RequestParam(required = true, name = "productdescription", defaultValue = "") String productDescription,
			@RequestParam(required = true, name = "post_status", defaultValue = "1") int post_status) {
		String message = "";
		if (request.getParameter("submit") != null) {
			if(productImage.trim().length() == 0) {
				message  = "Vui lòng chọn ảnh, và upload ảnh";
				loadAttribute(model, productId, productName, productImage, timeId, productDescription, post_status);
			}else {
				Service product;
				product = new Service();

				ServiceTime time = mServiceTimeRepository.findById(timeId).get();

				product.setServiceCode(Utils.genarateCode());
				product.setServiceName(productName);
				product.setImage(productImage);
				product.setDescription(productDescription);
				product.setDetail(productDescription);
				product.setIsActive((byte) post_status);
				product.setServiceTime(time);
				product.setCreatedAt(new Date());
				product.setUpdatedAt(new Date());

				try {
					mServiceRepository.save(product);
					model.addAttribute("result", "create");
					loadAttribute(model, 0, "", "", 0, "", 0);
				} catch (Exception e) {
					message = e.getMessage();
					loadAttribute(model, productId, productName, productImage, timeId, productDescription, post_status);
				}

			}
			
		}

		loadData(model);
		model.addAttribute("message", message);
		return "add-service";
	}

	private void loadData(Model model) {
		List<ServiceTime> times = mServiceTimeRepository.findAll();
		model.addAttribute("times", times);
	}

	private void loadAttribute(Model model, int productId, String productName, String productImage, int timeId, String productDescription, int postStaus) {
		model.addAttribute("productid", productId);
		model.addAttribute("productname", productName);
		model.addAttribute("productimage", productImage);
		model.addAttribute("productdescription", productDescription);

		model.addAttribute("timeId", timeId);
		model.addAttribute("post_status", postStaus);
		
	}

}
