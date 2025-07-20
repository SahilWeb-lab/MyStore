package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.AccountStatus;
import com.product.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
//	Create a method to check email already exists or not:
	public Boolean existsByEmail(String email);

//	Create a method to find user by email:
	public User findByEmail(String email);
	
	public User findByAccountStatus(AccountStatus status);
}
