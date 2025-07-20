package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.Cart;
import com.product.model.User;

public interface CartRepository extends JpaRepository<Cart, Long> {

//	Create a method to find the user cart:
	public Cart findByUser(User user);
	
}
