package com.product.service;

import java.io.UnsupportedEncodingException;

import com.product.dto.OrderDTO;
import com.product.enums.OrderStatus;
import com.product.exception.ResourceNotFoundException;

import jakarta.mail.MessagingException;

public interface EmailService {

//	Create a method to send email for order placed notification:
	public void sendOrderPlacedEmail(OrderDTO orderDTO)
			throws MessagingException, UnsupportedEncodingException, ResourceNotFoundException;

//	Create a method to send email for every order status change:
	public void sendOrderStatusUpdateEmail(Long orderId, OrderStatus orderStatus)
			throws MessagingException, ResourceNotFoundException, UnsupportedEncodingException;

	public void sendVerificationEmail(String toEmail, String userName, String verificationLink)
	            throws MessagingException, UnsupportedEncodingException;
	
	 public void sendPasswordResetEmail(String toEmail, String userName, String resetLink)
	            throws MessagingException, UnsupportedEncodingException;
}
