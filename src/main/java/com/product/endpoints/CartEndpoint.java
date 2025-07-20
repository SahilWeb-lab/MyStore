package com.product.endpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.dto.CartDTO;
import com.product.exception.ResourceNotFoundException;

@RequestMapping("/api/v1/cart")
public interface CartEndpoint {

	@PostMapping("/")
	public ResponseEntity<?> addToCart(@RequestBody CartDTO cartDTO) throws ResourceNotFoundException;
	
	@GetMapping("/")
	public ResponseEntity<?> getCartItems() throws ResourceNotFoundException;
	
//	Create a handler to remove product/cart item form cart:
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<?>removeCartItem(@PathVariable Long cartItemId) throws ResourceNotFoundException;
	
//	Create a handler to update quantity:
	@PutMapping("/update-quantity")
	public ResponseEntity<?> updateQuantity(@RequestParam Long itemId, @RequestParam Integer quantity) throws ResourceNotFoundException;
	
}
