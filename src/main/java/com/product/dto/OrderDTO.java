package com.product.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.product.enums.OrderStatus;
import com.product.enums.PaymentStatus;
import com.product.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
	
	private Long id;
	
	private Double totalAmount;
	
	private OrderStatus status;
	
	private String estimatedDelivery;
}
