package com.product.dto;

import com.product.enums.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CheckoutRequest {

	private Long id;
	
	private Long shippingAddressId;
	
	private PaymentMethod paymentMethod;
	
}
