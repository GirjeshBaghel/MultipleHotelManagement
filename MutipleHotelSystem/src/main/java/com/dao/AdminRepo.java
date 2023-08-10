package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Long> {

	Admin findByUserEmail(String email);
}
