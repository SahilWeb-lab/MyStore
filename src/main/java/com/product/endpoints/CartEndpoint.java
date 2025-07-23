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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cart APIs", description = "All cart related APIs.")
@RequestMapping("/api/v1/cart")
public interface CartEndpoint {

	@Operation(summary = "Add To Cart", description = "This API is used to add product to cart. This API is only accessible to logged in user.")
	@PostMapping("/")
	public ResponseEntity<?> addToCart(@RequestBody CartDTO cartDTO) throws ResourceNotFoundException;
	
	@Operation(summary = "Get Cart Items", description = "This API is used to get cart products/items. This API is only accessible to logged in user.")
	@GetMapping("/")
	public ResponseEntity<?> getCartItems() throws ResourceNotFoundException;
	
//	Create a handler to remove product/cart item form cart:
	@Operation(summary = "Remove Cart Product/Item", description = "This API is used to remove cart product/item. This API is only accessible to logged in user.")
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<?>removeCartItem(@PathVariable Long cartItemId) throws ResourceNotFoundException;
	
//	Create a handler to update quantity:
	@Operation(summary = "Update Cart Product/Item Quantity", description = "This API is used to update cart product/item quantity. This API is only accessible to logged in user.")
	@PutMapping("/update-quantity")
	public ResponseEntity<?> updateQuantity(@RequestParam Long itemId, @RequestParam Integer quantity) throws ResourceNotFoundException;
	
}
