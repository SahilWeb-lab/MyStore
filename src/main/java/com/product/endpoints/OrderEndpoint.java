package com.product.endpoints;

import java.io.UnsupportedEncodingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.dto.CheckoutRequest;
import com.product.enums.OrderStatus;
import com.product.exception.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;

@Tag(name = "Order APIs", description = "All order related APIs.")
@RequestMapping("/api/v1/order")
public interface OrderEndpoint {

//	Create a handler to create order:
	@Operation(summary = "Checkout", description = "This API is used to checkout. This API is accessible to logged in used only.")
	@PostMapping("/checkout")
	public ResponseEntity<?> createOrder(@RequestBody CheckoutRequest checkoutRequest) throws ResourceNotFoundException, Exception;
	
//	Create a handler to get all orders:
	@Operation(summary = "Get All Orders", description = "This API is used to get all order. This API is accessible to logged in user only.")
	@GetMapping("/")
	public ResponseEntity<?> getAllOrders() throws ResourceNotFoundException;
	
//	Create handler to cancel order:
	@Operation(summary = "Cancel Order", description = "This API is used to cancel the order. This API is accessible to logged in user only.")
	@PutMapping("/{orderId}")
	public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) throws ResourceNotFoundException;
	
	@Operation(summary = "Change Order Status", description = "This API is used to change order status. This API is only accessible to admin.")
	@PostMapping("/status")
	public ResponseEntity<?> changeOrderStatus(@RequestParam Long oid, @RequestParam OrderStatus orderStatus) throws ResourceNotFoundException, UnsupportedEncodingException, MessagingException; 

	@Operation(summary = "Get All Orders, For Admin", description = "This API is used to get all orders. This is only accessible to admin.")
	@GetMapping("/admin/orders")
	public ResponseEntity<?> getAllOrdersForAdmin();
}
