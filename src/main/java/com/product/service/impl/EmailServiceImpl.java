package com.product.service.impl;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.product.dto.EmailRequest;
import com.product.dto.OrderDTO;
import com.product.enums.OrderStatus;
import com.product.exception.ResourceNotFoundException;
import com.product.model.Order;
import com.product.model.User;
import com.product.repository.OrderRepository;
import com.product.service.EmailService;
import com.product.util.CommonMethods;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private CommonMethods commonMethods;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void sendOrderPlacedEmail(OrderDTO orderDTO) throws MessagingException, UnsupportedEncodingException, ResourceNotFoundException {
		MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);

//	    Get Logged in user info:
	    User loggedInUser = commonMethods.getLoggedInUser();
	    
	    helper.setFrom("${spring.mail.username}", "MYSTORE"); // Customize sender name
	    helper.setTo(loggedInUser.getEmail());
	    helper.setSubject("Order Confirmation - Order #" + orderDTO.getId());

	    String htmlContent = """
	        <html>
	        <body style="font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px;">
	            <div style="max-width: 600px; margin: auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
	                <h2 style="color: #4CAF50;">Thank you for your order, %s!</h2>
	                <p>Your order has been placed successfully.</p>
	                <p><strong>Order ID:</strong> #%d</p>
	                <p><strong>Total Amount:</strong> ₹%.2f</p>
	                <p>We are processing your order and will notify you once it's shipped.</p>
	                <hr />
	                <p style="font-size: 0.9em; color: #777;">This is an automated confirmation from MYSTORE.</p>
	            </div>
	        </body>
	        </html>
	        """.formatted(loggedInUser.getName(), orderDTO.getId(), orderDTO.getTotalAmount());

	    helper.setText(htmlContent, true); // true = HTML
		
		mailSender.send(message);;
		System.out.println("Email sent successfully to : " + loggedInUser.getEmail());
	}
	
	public void sendOrderStatusUpdateEmail(Long orderId, OrderStatus orderStatus) throws MessagingException, ResourceNotFoundException, UnsupportedEncodingException {
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);

	    User loggedInUser = commonMethods.getLoggedInUser();
	    
	    Order foundOrder = loggedInUser.getOrders().stream().filter(order -> order.getId().equals(orderId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Order not found with id : ["+ orderId +"]"));
	    
	    helper.setFrom("${spring.mail.username}", "MYSTORE"); // Customize sender name
	    helper.setTo(loggedInUser.getEmail());
	    helper.setSubject("Update on Your Order #" + orderId);

	    String statusMessage = switch (orderStatus) {
	        case PENDING -> "Your order is pending confirmation.";
	        case PROCESSING -> "Your order is currently being prepared.";
	        case SHIPPED -> "Great news! Your order has been shipped.";
	        case DELIVERED -> "Your order has been delivered. We hope you enjoy it!";
	        case CANCELLED -> "Your order has been cancelled. If this was a mistake, please contact support.";
	        default -> "Your order status has been updated.";
	    };

	    String htmlContent = """
	        <html>
	        <body style="font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px;">
	            <div style="max-width: 600px; margin: auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
	                <h2 style="color: #2196F3;">Hello, %s!</h2>
	                <p>%s</p>
	                <p><strong>Order ID:</strong> #%d</p>
	                <p><strong>Total Amount:</strong> ₹%.2f</p>
	                <p><strong>Current Status:</strong> %s</p>
	                <hr />
	                <p style="font-size: 0.9em; color: #777;">This is an automated status update from MYSTORE.</p>
	            </div>
	        </body>
	        </html>
	        """.formatted(loggedInUser.getName(), statusMessage, orderId, foundOrder.getTotalAmount(), orderStatus.name());

	    helper.setText(htmlContent, true);
	    mailSender.send(message);
	}

	

    public void sendVerificationEmail(String toEmail, String userName, String verificationLink)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("mandalsahil253@email.com", "MYSTORE");
        helper.setTo(toEmail);
        helper.setSubject("Account Verification - MYSTORE");

        String htmlContent = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Verify Your Email</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f4f4f4;
                        padding: 20px;
                    }
                    .container {
                        max-width: 600px;
                        margin: 0 auto;
                        background: #fff;
                        padding: 30px;
                        border-radius: 8px;
                        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                    }
                    .button {
                        background-color: #28a745;
                        color: white;
                        padding: 10px 20px;
                        text-decoration: none;
                        border-radius: 5px;
                        display: inline-block;
                        margin-top: 20px;
                    }
                    .footer {
                        font-size: 12px;
                        color: #777;
                        margin-top: 30px;
                        text-align: center;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2>Hello, %s!</h2>
                    <p>Thanks for registering with MYSTORE.</p>
                    <p>Please click the button below to verify your email address and activate your account:</p>
                    <a href="%s" class="button">Verify My Account</a>
                    <p>If the button doesn't work, copy and paste this link into your browser:</p>
                    <p><a href="%s">%s</a></p>
                    <div class="footer">
                        &copy; 2025 MYSTORE. All rights reserved.
                    </div>
                </div>
            </body>
            </html>
        """.formatted(userName, verificationLink, verificationLink, verificationLink);

        helper.setText(htmlContent, true);
        mailSender.send(message);
    }
    
    
    public void sendPasswordResetEmail(String toEmail, String userName, String resetLink)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("mandalsahil253@email.com", "MYSTORE");
        helper.setTo(toEmail);
        helper.setSubject("Password Reset Request - MYSTORE");

        String htmlContent = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Reset Your Password</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f4f4f4;
                        padding: 20px;
                    }
                    .container {
                        max-width: 600px;
                        margin: 0 auto;
                        background: #fff;
                        padding: 30px;
                        border-radius: 8px;
                        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                    }
                    .button {
                        background-color: #007bff;
                        color: white;
                        padding: 10px 20px;
                        text-decoration: none;
                        border-radius: 5px;
                        display: inline-block;
                        margin-top: 20px;
                    }
                    .footer {
                        font-size: 12px;
                        color: #777;
                        margin-top: 30px;
                        text-align: center;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2>Hello, %s!</h2>
                    <p>We received a request to reset your password for your MYSTORE account.</p>
                    <p>Click the button below to set a new password:</p>
                    <a href="%s" class="button">Reset My Password</a>
                    <p>If the button doesn't work, copy and paste this link into your browser:</p>
                    <p><a href="%s">%s</a></p>
                    <p>If you didn't request a password reset, please ignore this email.</p>
                    <div class="footer">
                        &copy; 2025 MYSTORE. All rights reserved.
                    </div>
                </div>
            </body>
            </html>
        """.formatted(userName, resetLink, resetLink, resetLink);

        helper.setText(htmlContent, true);
        mailSender.send(message);
    }

}
