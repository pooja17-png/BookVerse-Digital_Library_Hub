package com.library.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.library.application.module.Admin;
import com.library.application.module.AdminLogin;
import com.library.application.service.AdminService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AdminLoginController {

    @Autowired
	private AdminService adminService;
	
	
	@GetMapping("/registeradmin")
	public String showAdminRegisterForm(Model model) {
		model.addAttribute("admin", new Admin());
		return "admin_registration";
	}
	
	@PostMapping("/registerAdminStatus")
	public String registerAdmin(@ModelAttribute("admin")Admin admin) {
		adminService.saveAdmin(admin);
		return "redirect:/adminlogin";
		
	}
	
	@GetMapping("/adminlogin")
	public String showadminLoginForm() {
		return "admin_login";	
	}
	
	@PostMapping("/adminloginStatus")
	public String loginAdmin(@Valid @ModelAttribute("adminLogin")AdminLogin adminLogin,BindingResult br,HttpSession session, Model model) {
		if(br.hasErrors()) {
			return "admin_login";
		}
		List<Admin>dbAdmin = adminService.adminList();
		boolean found = false;
		Long adminId = null;
		
		for(Admin ad: dbAdmin) {
			if(ad.getUsername().equals(adminLogin.getUsername())&& ad.getPassword().equals(adminLogin.getPassword())) {
				found = true;
				adminId = ad.getAdminId();
				break;
			}
		}
		if(found) {
			session.setAttribute("adminId", adminId);
			return "redirect:/admindashboard";
		}else {
			return "admin_login";

		}
	
	}

	@GetMapping("/admindashboard")
	public String showAdminDashboard(HttpSession session, Model model) {
		Long adminId = (Long) session.getAttribute("adminId");
		if(adminId==null) {
			return"redirect:/adminlogin";
		}
		return "admindashboard";
		
	}
}
