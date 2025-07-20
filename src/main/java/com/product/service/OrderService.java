package com.product.service;

import java.util.List;

import com.product.dto.CheckoutRequest;
import com.product.dto.OrderDTO;
import com.product.dto.OrderResponse;
import com.product.enums.OrderStatus;
import com.product.exception.ResourceNotFoundException;

public interface OrderService {

//	Create a method to create order:
	public OrderDTO checkOut(CheckoutRequest checkoutRequest) throws Exception, ResourceNotFoundException;
	
//	Create a method to get all order:
	public List<OrderResponse> getAllOrders() throws ResourceNotFoundException;
	
//	Create a method to cancel the order: For User
	public Boolean cancelOrder(Long orderId) throws ResourceNotFoundException;
	
//	Create a method to change the status:
	public Boolean changeOrderStatus(Long orderId, OrderStatus orderStatus) throws ResourceNotFoundException;
}
