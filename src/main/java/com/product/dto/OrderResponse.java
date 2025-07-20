package com.product.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.product.enums.OrderStatus;
import com.product.enums.PaymentMethod;
import com.product.enums.PaymentStatus;
import com.product.model.Address;
import com.product.model.OrderItem;
import com.product.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {

	private Long id;
	
	private List<OrderItemDTO> orderItem;
	
	private LocalDateTime orderDate;
	
	private Double totalAmount;
	
	private OrderStatus orderStatus;
	
}
