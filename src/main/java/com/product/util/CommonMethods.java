package com.product.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.product.exception.ResourceNotFoundException;
import com.product.model.Cart;
import com.product.model.User;
import com.product.model.Wishlist;
import com.product.repository.CartRepository;
import com.product.repository.UserRepository;
import com.product.repository.WishlistRepository;

@Component
public class CommonMethods {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private WishlistRepository wishlistRepository;

//	Create a method to get logged in user:
	public User getLoggedInUser() throws ResourceNotFoundException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication.isAuthenticated() && authentication != null) {
			
			User user = userRepository.findByEmail(authentication.getName());
			
			if(ObjectUtils.isEmpty(user))
				throw new ResourceNotFoundException("User Not Found!");
				
			return user;
		}
		
		return null;
	}
	
//	Create a method to get logged in user cart:
	public Cart getLoggedInUserCart() throws ResourceNotFoundException {
		User loggedInUser = getLoggedInUser();
		Cart cart = cartRepository.findByUser(loggedInUser);
		
		if(ObjectUtils.isEmpty(cart))
			throw new ResourceNotFoundException("Cart is Empty!");
		
		return cart;
	}
	
//	Create a method to get wishlist in user cart:
	public Wishlist getLoggedInUserWishlist() throws ResourceNotFoundException {
		User loggedInUser = getLoggedInUser();
		Wishlist wishlist = wishlistRepository.findByUserId(loggedInUser.getId());
		
		return wishlist;
	}	
	
}
