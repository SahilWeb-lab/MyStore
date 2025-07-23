package com.product.endpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.product.exception.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Wishlist APIs", description = "All wishlist related APIs.")
@RequestMapping("/api/v1/wishlist")
public interface WishlistEndpoint {

//	Create a handler to add product to wishlist:
	@Operation(summary = "Add Product to Wishlist", description = "This API is used to add product to wishlist. This API is only accessible to logged in user.")
	@PostMapping("/{pid}")
	public ResponseEntity<?> addToWishlist(@PathVariable Integer pid) throws ResourceNotFoundException;
	
//	Create a handler to get all wishlists:
	@Operation(summary = "Get All Wishlist Product", description = "This API is used to get all wishlist product. This API is only accessible to logged in user.")
	@GetMapping("/")
	public ResponseEntity<?> getAllWishlists() throws ResourceNotFoundException;
	
//	Create a handler to remove product from wishlist:
	@Operation(summary = "Remove Product From Wishlist", description = "This API is used to remove product from wishlist. This API is only accessible to logged in user.")
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> removeWishlistItem(@PathVariable Integer productId) throws ResourceNotFoundException;
	
}
