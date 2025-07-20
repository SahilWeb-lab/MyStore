package com.product.service;

import java.util.List;

import com.product.dto.WishlistDTO;
import com.product.exception.ResourceNotFoundException;

public interface WishlistService {
	
//	Create a method to add product to wishlist:
	public  Boolean addToWishlist(Integer productId) throws ResourceNotFoundException;
	
	public List<WishlistDTO> getAllWishlist() throws ResourceNotFoundException;
	
//	Create a method to remove wishlist:
	public Boolean removeWishlist(Integer productId) throws ResourceNotFoundException;
	
}
