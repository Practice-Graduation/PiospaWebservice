package com.baobang.piospa.controller.admin;

import java.security.Principal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baobang.piospa.entities.Staff;
import com.baobang.piospa.repositories.StaffRepository;
import com.baobang.piospa.utils.Utils;

import java.util.Optional;
/**
  * @author BaoBang
  * @Created Jul 16, 2018
  * 
  */
@Controller
public class StaffAdminController {
	
	@Autowired
	StaffRepository mStaffRepository;
	
	@RequestMapping(value = "admin/members", method = RequestMethod.GET)
	public String productList(Model model) {
		List<Staff> staffs = mStaffRepository.findAll();
		Collections.reverse(staffs);
		
		model.addAttribute("result", staffs);
		return "member-list";
	}
	
	@RequestMapping(value = "admin/profile/{id}", method = RequestMethod.GET)
	public String productList(Model model, @PathVariable("id") int id) {
		
		Staff staff = mStaffRepository.findById(id).get();
		
		model.addAttribute("account", staff);
		return "profile";
	}
	
	@RequestMapping(value = "admin/profile/{id}", method = RequestMethod.POST)
	public String updateInfo(Model model, @PathVariable("id") int id,Principal principal,
			@RequestParam(required = true, name = "account_name") String fullName,
			@RequestParam(required = true, name = "account_phone") String phone,
			@RequestParam(required = false, name = "account_avatar", defaultValue = "") String avatar,
			@RequestParam(required = false, name = "account_role") int role) {
		
		Optional<Staff> staffOptional = mStaffRepository.findById(id);
		Staff staff;
		if(staffOptional == null) {
			return "redirect:/403";
		}else {
			staff = staffOptional.get();
			
			staff.setFullname(fullName);
			staff.setPhone(phone);
			staff.setIsAdmin((byte)role);
			if(avatar.length() > 0) {
				staff.setStaffAvatar(avatar);
			}
			
			try {
				mStaffRepository.save(staff);
				model.addAttribute("result", true);
			}catch (Exception e) {
				model.addAttribute("result", false);
			}
		}
		model.addAttribute("account", staff);
		return "profile";
	}
	
	@RequestMapping(value = "admin/profile/{id}/reset-password", method = RequestMethod.POST)
	public String resetPassword(Model model, @PathVariable("id") int id,Principal principal,
			@RequestParam(required = true, name = "current_password") String currentPassword,
			@RequestParam(required = true, name = "new_password") String newPassword,
			@RequestParam(required = true, name = "confirm_password") String confirmPassword) {
		Staff staff = mStaffRepository.findById(id).get();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (staff == null) {
			return "admin/403";
		} else {
			Map<String, String> errors_password = new HashMap<String, String>();
			if (currentPassword == null || currentPassword.length() <= 0)
				errors_password.put("current_password", "Mật khẩu không được bỏ trống!");
			if (newPassword == null || newPassword.length() <= 0)
				errors_password.put("new_password", "Mật khẩu mới không được bỏ trống!");
			if (confirmPassword == null || confirmPassword.length() <= 0)
				errors_password.put("confirm_password", "Xác nhận mật khẩu không được bỏ trống!");
			
			String passwordHash = encoder.encode(currentPassword);
			if (passwordHash.equals(staff.getPassword())) {
				errors_password.put("current_password", "Mật khẩu không chính xác!");
			} else if (!newPassword.equals(confirmPassword)) {
				errors_password.put("confirm_password", "Xác nhận mật khẩu không chính xác!");
			}

			if (!errors_password.isEmpty()) {
				model.addAttribute("result_reset_password", false);
			} else {
				String hashPassword = encoder.encode(newPassword);
				staff.setPassword(hashPassword);
				
				
				mStaffRepository.save(staff);
				model.addAttribute("result_reset_password", true);
			}
			model.addAttribute("message", "");
			model.addAttribute("account", staff);
		}
		return "profile";
	}
	
	@RequestMapping(value="admin/add-new-account", method=RequestMethod.GET)
	public String addNewAccount(Model model) {
		model.addAttribute("account_status", "1");
		return "add-new-account";
	}
	
	@RequestMapping(value="admin/add-new-account", method=RequestMethod.POST)
	public String doAddNewAccount(
			Model model,Principal principal,
			@RequestParam(required=true, name="account_user_name", defaultValue= "") String userName,
			@RequestParam(required=true, name="account_fullname", defaultValue= "") String fullname,
			@RequestParam(required=false, name="account_role") int role,
			@RequestParam(required=true, name="account_password", defaultValue= "") String password,
			@RequestParam(required=true, name="account_confirm_password", defaultValue= "") String confirmPassword,
			@RequestParam(required=false, name="post_status") int status,
			@RequestParam(required=false, name="account_avatar", defaultValue= "") String avatar) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String passwordHash = encoder.encode(password);
		Staff account = mStaffRepository.findByUsername(userName);
		if(account != null) {
			model.addAttribute("message", "Tài khoản đã tồn tại");
			model.addAttribute("account", account);
			return "add-new-account";
		}
		
		
		try {
			account = new Staff();
			account.setFullname(fullname);
			account.setAccount(userName);
			account.setStaffAvatar(avatar);
			account.setIsAdmin((byte)role);
			account.setPassword(passwordHash);
			
			
			if(!password.equals(confirmPassword)) {
				model.addAttribute("message", "Mật khẩu xác nhận không đúng");
				model.addAttribute("account", account);
				return "add-new-account";
			}
			
			
			mStaffRepository.save(account);

			return "redirect:/admin/members";
		}catch(Exception e){
		}
		
		model.addAttribute("account", account);
		
		return "add-new-account";
	}
	
	@RequestMapping(value = "admin/delete-account", method=RequestMethod.POST)
	public String deleteAccount(@RequestParam (required = true, name = "id_account") int idAccount) {
		Optional<Staff> account = mStaffRepository.findById(idAccount);
		if (account != null) {
			mStaffRepository.delete(account.get());
		}
		return "redirect:/admin/members";
	}

}
