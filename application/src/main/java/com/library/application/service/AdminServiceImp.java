package com.library.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.application.module.Admin;
import com.library.application.repository.AdminRepository;

@Service
public class AdminServiceImp implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public void saveAdmin(Admin admin) {
		adminRepository.save(admin);
		
	}

	@Override
	public List<Admin> adminList() {
		List<Admin> findAll = adminRepository.findAll();
		return findAll;
	}

}
