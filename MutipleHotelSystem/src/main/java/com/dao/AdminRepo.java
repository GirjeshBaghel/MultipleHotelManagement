package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepo extends JpaRepository<Admin, Long> {

	Admin findByUserEmail(String email);
	Admin findByUserName(String  name);
	Admin findByUserPassword(String password);
	@Query("SELECT a FROM Admin a WHERE a.role = :role")
	List<Admin> findByRole(int role);

	/*@Query("SELECT a.role FROM Admin a WHERE a.userId = :userId")
	String getRoleAsString(@Param("userId") long userId);*/

}
