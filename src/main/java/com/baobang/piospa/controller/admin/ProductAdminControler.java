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

import com.baobang.piospa.entities.Product;
import com.baobang.piospa.entities.ProductGroup;
import com.baobang.piospa.entities.ProductLabel;
import com.baobang.piospa.entities.ProductOrigin;
import com.baobang.piospa.entities.Staff;
import com.baobang.piospa.repositories.ProductGroupRepository;
import com.baobang.piospa.repositories.ProductLabelRepository;
import com.baobang.piospa.repositories.ProductOriginRepository;
import com.baobang.piospa.repositories.ProductRepository;
import com.baobang.piospa.repositories.StaffRepository;
import com.baobang.piospa.utils.Utils;

/**
 * @author BaoBang
 * @Created Jul 11, 2018
 * 
 */
@Controller
public class ProductAdminControler {

	@Autowired
	ProductRepository mRepository;
	@Autowired
	StaffRepository mStaffRepository;

	@RequestMapping(value = "admin/product", method = RequestMethod.GET)
	public String productList(Model model) {
		List<Product> liProducts = mRepository.findAll();
		Collections.reverse(liProducts);
		model.addAttribute("result", liProducts);
		return "product";
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "admin/remove-product", method = RequestMethod.GET)
	public String removeProduct(@RequestParam(name = "product_id", required = true) int productId) {

		System.out.println("## AJAX reomve from csdl: " + productId);
		Product product = mRepository.findById(productId).get();
		if (product != null) {
			if (product.getQuantity() > 0) {
				return "false";
			}
			try {

				mRepository.deleteById(productId);
			} catch (Exception e) {
				return "false";
			}

		} else {
			return "false";
		}
		return "true";
	}

	@RequestMapping(value = "admin/edit-product/{id}")
	public String orderDetail(Model model, HttpServletRequest request, Principal principal, @PathVariable("id") int id,
			@RequestParam(required = true, name = "productid", defaultValue = "0") int productId,
			@RequestParam(required = true, name = "productname", defaultValue = "") String productName,
			@RequestParam(required = true, name = "productimage", defaultValue = "") String productImage,
			@RequestParam(required = true, name = "productgroup", defaultValue = "0") int productGroup,
			@RequestParam(required = true, name = "productbrand", defaultValue = "0") int productLable,
			@RequestParam(required = true, name = "productorigin", defaultValue = "0") int productOrigin,
			@RequestParam(required = true, name = "productquantity", defaultValue = "0") int productQuantity,
			@RequestParam(required = true, name = "productunit", defaultValue = "gram") String productUnit,
			@RequestParam(required = true, name = "productcostprice", defaultValue = "0") int productCostPrice,
			@RequestParam(required = true, name = "productprice", defaultValue = "0") int productPrice,
			@RequestParam(required = true, name = "productamount", defaultValue = "0") int productAmount,
			@RequestParam(required = true, name = "productdescription", defaultValue = "") String productDescription) {

		Product product = mRepository.findById(id).get();
		model.addAttribute("title", "CẬP NHẬT SẢN PHẨM");
		String message = "";
		if (request.getParameter("submit") != null) {

			ProductGroup group = mProductGroupRepository.findById(productGroup).get();
			ProductLabel lable = mProductLableRepository.findById(productLable).get();
			ProductOrigin origin = mProductOriginRepository.findById(productOrigin).get();

			product.setProductName(productName);
			product.setImage(productImage);
			product.setThumbnail(productImage);
			product.setProductGroup(group);
			product.setProductLabel(lable);
			product.setProductOrigin(origin);
			product.setCostPrice(productCostPrice);
			product.setPrice(productPrice);
			product.setQuantity(productQuantity);
			product.setQuantityValue(productUnit);
			product.setAmount(productAmount);
			product.setDescription(productDescription);
			product.setInfo(productDescription);
			product.setCreatedAt(new Date());
			product.setUpdatedAt(new Date());

			if (principal != null) {
				User loginedUser = (User) ((Authentication) principal).getPrincipal();

				Staff staff = mStaffRepository.findByUsername(loginedUser.getUsername());

				product.setUpdatedBy(staff.getStaffId());
				product.setCreatedBy(staff.getStaffId());
			}

			try {
				mRepository.save(product);

				model.addAttribute("result", "update");
			} catch (Exception e) {
				message = e.getMessage();
			}
		}

		loadAttribute(model, product.getProductId(), product.getProductName(), product.getImage(),
				product.getProductGroup().getProductGroupId(), product.getProductLabel().getProductLabelId(),
				product.getProductOrigin().getProductOriginId(), product.getQuantity(), product.getQuantityValue(),
				product.getCostPrice(), product.getPrice(), product.getDescription(), product.getAmount());
		loadData(model);
		model.addAttribute("message", message);
		return "add-product";
	}

	@RequestMapping(value = "admin/add-product")
	public String addProduct(Model model, HttpServletRequest request, Principal principal,
			@RequestParam(required = true, name = "productid", defaultValue = "0") int productId,
			@RequestParam(required = true, name = "productname", defaultValue = "") String productName,
			@RequestParam(required = true, name = "productimage", defaultValue = "") String productImage,
			@RequestParam(required = true, name = "productgroup", defaultValue = "0") int productGroup,
			@RequestParam(required = true, name = "productbrand", defaultValue = "0") int productLable,
			@RequestParam(required = true, name = "productorigin", defaultValue = "0") int productOrigin,
			@RequestParam(required = true, name = "productquantity", defaultValue = "0") int productQuantity,
			@RequestParam(required = true, name = "productunit", defaultValue = "gram") String productUnit,
			@RequestParam(required = true, name = "productcostprice", defaultValue = "0") int productCostPrice,
			@RequestParam(required = true, name = "productprice", defaultValue = "0") int productPrice,
			@RequestParam(required = true, name = "productamount", defaultValue = "0") int productAmount,
			@RequestParam(required = true, name = "productdescription", defaultValue = "") String productDescription) {

		if (principal == null) {
			return "login";
		}

		model.addAttribute("title", "THÊM SẢN PHẨM");
		String message = "";
		if (request.getParameter("submit") != null) {
			Product product;
			product = new Product();
			product.setProductCode(Utils.genarateCode());

			ProductGroup group = mProductGroupRepository.findById(productGroup).get();
			ProductLabel lable = mProductLableRepository.findById(productLable).get();
			ProductOrigin origin = mProductOriginRepository.findById(productOrigin).get();

			product.setProductName(productName);
			product.setImage(productImage);
			product.setThumbnail(productImage);
			product.setProductGroup(group);
			product.setProductLabel(lable);
			product.setProductOrigin(origin);
			product.setCostPrice(productCostPrice);
			product.setPrice(productPrice);
			product.setAmount(productAmount);
			product.setQuantityValue(productUnit);
			product.setQuantity(productQuantity);
			product.setDescription(productDescription);
			product.setInfo(productDescription);
			product.setCreatedAt(new Date());
			product.setUpdatedAt(new Date());

			if (principal != null) {
				User loginedUser = (User) ((Authentication) principal).getPrincipal();

				Staff staff = mStaffRepository.findByUsername(loginedUser.getUsername());
				product.setUpdatedBy(staff.getStaffId());
				product.setCreatedBy(staff.getStaffId());
			}

			try {
				mRepository.save(product);

				model.addAttribute("result", "create");
				loadAttribute(model, 0, "", "", 0, 0, 0, 0, "gram", 0, 0, "", 0);
			} catch (Exception e) {
				message = e.getMessage();
				loadAttribute(model, productId, productName, productImage, productGroup, productLable, productOrigin,
						productQuantity, productUnit, productCostPrice, productPrice, productDescription,
						productAmount);
			}

		}

		loadData(model);
		model.addAttribute("message", message);
		return "add-product";
	}

	private void loadAttribute(Model model, int productId, String productName, String productImage, int productGroup,
			int productLable, int productOrigin, int productQuantity, String quantityValue, int productCostPrice,
			int productPrice, String productDescription, int productAmount) {

		model.addAttribute("productid", productId);
		model.addAttribute("productname", productName);
		model.addAttribute("productimage", productImage);
		model.addAttribute("productgroup", productGroup);
		model.addAttribute("productbrand", productLable);
		model.addAttribute("productorigin", productOrigin);
		model.addAttribute("productquantity", productQuantity);
		model.addAttribute("productunit", quantityValue);
		model.addAttribute("productcostprice", productCostPrice);
		model.addAttribute("productprice", productPrice);
		model.addAttribute("productdescription", productDescription);
		model.addAttribute("productamount", productAmount);
	}

	@Autowired
	ProductGroupRepository mProductGroupRepository;
	@Autowired
	ProductLabelRepository mProductLableRepository;
	@Autowired
	ProductOriginRepository mProductOriginRepository;

	private void loadData(Model model) {
		List<ProductGroup> groups = mProductGroupRepository.findAll();
		List<ProductLabel> labels = mProductLableRepository.findAll();
		List<ProductOrigin> origins = mProductOriginRepository.findAll();
		model.addAttribute("groups", groups);
		model.addAttribute("labels", labels);
		model.addAttribute("origins", origins);
	}
}
