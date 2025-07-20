package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.WishlistDTO;
import com.product.endpoints.WishlistEndpoint;
import com.product.exception.ResourceNotFoundException;
import com.product.service.WishlistService;
import com.product.util.CommonUtils;

@RestController
public class WishlistController implements WishlistEndpoint {
	
	@Autowired
	private WishlistService wishlistService;


	public ResponseEntity<?> addToWishlist(@PathVariable Integer pid) throws ResourceNotFoundException {
		Boolean wishlistStatus = wishlistService.addToWishlist(pid);
		
		if(wishlistStatus)
			return CommonUtils.createBuildResponseMessage("Product added to wishlist!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to add product to wishlist!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	public ResponseEntity<?> getAllWishlists() throws ResourceNotFoundException {
		List<WishlistDTO> wishlists = wishlistService.getAllWishlist();
		
		if(CollectionUtils.isEmpty(wishlists))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(wishlists, HttpStatus.OK);
	}
	

	public ResponseEntity<?> removeWishlistItem(@PathVariable Integer productId) throws ResourceNotFoundException {
		Boolean removeWishlist = wishlistService.removeWishlist(productId);
		
		if(removeWishlist)
			return CommonUtils.createBuildResponseMessage("Product removed form wishlist!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to remove product from wishlist!", HttpStatus.BAD_REQUEST);
	}
	
}
