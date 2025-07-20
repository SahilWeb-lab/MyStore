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

import jakarta.mail.MessagingException;

@RequestMapping("/api/v1/order")
public interface OrderEndpoint {

//	Create a handler to create order:
	@PostMapping("/checkout")
	public ResponseEntity<?> createOrder(@RequestBody CheckoutRequest checkoutRequest) throws ResourceNotFoundException, Exception;
	
//	Create a handler to get all orders:
	@GetMapping("/")
	public ResponseEntity<?> getAllOrders() throws ResourceNotFoundException;
	
//	Create handler to cancel order:
	@PutMapping("/{orderId}")
	public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) throws ResourceNotFoundException;
	
	@PostMapping("/status")
	public ResponseEntity<?> changeOrderStatus(@RequestParam Long oid, @RequestParam OrderStatus orderStatus) throws ResourceNotFoundException, UnsupportedEncodingException, MessagingException; 
}
