package com.product.dto;

import com.product.model.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDTO {

	private Long id;

	private Long productId;

	private Integer quantity;

//	Unit price at a time of order
	private Double unitPrice;

//	total price = quantity * unitPrice
	private Double totalPrice;

//	private Order order;

}
