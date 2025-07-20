package com.product.validation;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.dto.CartDTO;
import com.product.exception.AlreadyExistsException;
import com.product.exception.ResourceNotFoundException;
import com.product.model.Cart;
import com.product.model.Product;
import com.product.repository.CartItemRepository;
import com.product.repository.ProductRepository;
import com.product.util.CommonMethods;

@Component
public class CartValidation {
	
	@Autowired
	private CommonMethods commonMethods;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public void validate(CartDTO cartDTO) throws ResourceNotFoundException {
		
		Map<String, Object> errors = new LinkedHashMap<>();
		
//		Check item already exists in the cart or not:
		Cart loggedInUserCart = commonMethods.getLoggedInUserCart();
//		Get Product:
		Product product = productRepository.findById(cartDTO.getProductId()).get();
		
		Boolean existsByCartAndProduct = cartItemRepository.existsByCartAndProduct(loggedInUserCart, product);
		
		if(existsByCartAndProduct) 
			throw new AlreadyExistsException("Product already exists in the cart!");
	}
	
}
