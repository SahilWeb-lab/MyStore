package com.product.service;

import java.util.List;

import com.product.dto.CartDTO;
import com.product.dto.CartItemDTO;
import com.product.exception.ResourceNotFoundException;

public interface CartService {

	public Boolean addToCart(CartDTO cartDTO) throws ResourceNotFoundException;
	
	public List<CartItemDTO> getAllCartItem() throws ResourceNotFoundException;
	
	public  Boolean removeCartItem(Long cartItemId) throws ResourceNotFoundException;
	
	public Boolean updateQuantity(Long cartItemId, Integer quantity) throws ResourceNotFoundException;
	
}
