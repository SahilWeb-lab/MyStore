package com.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckoutResponse {

	private Long id;
	
	private Double totalAmount;
	
	private String status;
	
	private String estimatedDelivery;
	
}
