package com.product.endpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.product.exception.ResourceNotFoundException;

@RequestMapping("/api/v1/wishlist")
public interface WishlistEndpoint {

//	Create a handler to add product to wishlist:
	@PostMapping("/{pid}")
	public ResponseEntity<?> addToWishlist(@PathVariable Integer pid) throws ResourceNotFoundException;
	
//	Create a handler to get all wishlists:
	@GetMapping("/")
	public ResponseEntity<?> getAllWishlists() throws ResourceNotFoundException;
	
//	Create a handler to remove product from wishlist:
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> removeWishlistItem(@PathVariable Integer productId) throws ResourceNotFoundException;
	
}
