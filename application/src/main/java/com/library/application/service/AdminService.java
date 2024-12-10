package com.library.application.service;

import java.util.List;

import com.library.application.module.Admin;

public interface AdminService {

	public void saveAdmin(Admin admin);
	public List<Admin>adminList();
}
