package com.product.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.CheckoutRequest;
import com.product.dto.EmailRequest;
import com.product.dto.OrderDTO;
import com.product.dto.OrderResponse;
import com.product.endpoints.OrderEndpoint;
import com.product.enums.OrderStatus;
import com.product.exception.ResourceNotFoundException;
import com.product.model.Order;
import com.product.service.EmailService;
import com.product.service.OrderService;
import com.product.util.CommonUtils;

import jakarta.mail.MessagingException;

@RestController
public class OrderController implements OrderEndpoint {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private EmailService emailService;


	public ResponseEntity<?> createOrder(@RequestBody CheckoutRequest checkoutRequest) throws ResourceNotFoundException, Exception {
		OrderDTO orderDTO = orderService.checkOut(checkoutRequest);
		
		if(ObjectUtils.isEmpty(orderDTO))
			return CommonUtils.createErrorResponseMessage("Failed to make order!", HttpStatus.INTERNAL_SERVER_ERROR);
		
//		Send placed mail:
		emailService.sendOrderPlacedEmail(orderDTO);
		
		return CommonUtils.createBuildResponse(orderDTO, HttpStatus.OK);
	}
	

	public ResponseEntity<?> getAllOrders() throws ResourceNotFoundException {
		List<OrderResponse> orders = orderService.getAllOrders();
		
		if(CollectionUtils.isEmpty(orders))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(orders, HttpStatus.OK);
	}
	

	public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) throws ResourceNotFoundException {
		Boolean cancelOrder = orderService.cancelOrder(orderId);
		
		if(cancelOrder)
			return CommonUtils.createBuildResponseMessage("Order cancelled successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to cancel order!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<?> changeOrderStatus(@RequestParam Long oid, @RequestParam OrderStatus orderStatus) throws ResourceNotFoundException, UnsupportedEncodingException, MessagingException {
		Boolean changeOrderStatus = orderService.changeOrderStatus(oid, orderStatus);
		
		if(changeOrderStatus) {
			emailService.sendOrderStatusUpdateEmail(oid, orderStatus);
			return CommonUtils.createBuildResponseMessage("Order Status Changed Successfully!", HttpStatus.OK);
		}
		
		return CommonUtils.createErrorResponseMessage("Failed to change order status!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
