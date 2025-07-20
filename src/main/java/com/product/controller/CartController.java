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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.CartDTO;
import com.product.dto.CartItemDTO;
import com.product.endpoints.CartEndpoint;
import com.product.exception.ResourceNotFoundException;
import com.product.service.CartService;
import com.product.util.CommonUtils;

@RestController
public class CartController implements CartEndpoint {

	@Autowired
	private CartService cartService;
	
	public ResponseEntity<?> addToCart(@RequestBody CartDTO cartDTO) throws ResourceNotFoundException {
		Boolean addToCart = cartService.addToCart(cartDTO);
		
		if(addToCart)
			return CommonUtils.createBuildResponseMessage("Product Added To Cart!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to Add to Cart!", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> getCartItems() throws ResourceNotFoundException {
		List<CartItemDTO> allCartItem = cartService.getAllCartItem();
		
		if(CollectionUtils.isEmpty(allCartItem))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(allCartItem, HttpStatus.OK);
	}
	

	public ResponseEntity<?>removeCartItem(@PathVariable Long cartItemId) throws ResourceNotFoundException {
		Boolean removeCartItem = cartService.removeCartItem(cartItemId);
		
		if(removeCartItem)
			return CommonUtils.createBuildResponseMessage("Cart item removed successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to remove cart item!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	public ResponseEntity<?> updateQuantity(@RequestParam Long itemId, @RequestParam Integer quantity) throws ResourceNotFoundException {
		
		Boolean updateQuantity = cartService.updateQuantity(itemId, quantity);
		
		if(updateQuantity)
			return CommonUtils.createBuildResponseMessage("Quantity updated successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to update quantity!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
