package com.library.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.application.module.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	
}
