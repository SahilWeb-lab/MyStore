package com.product.dto;

import com.product.model.Product;

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
public class CartItemDTO {
	
	private Long id;

	private Product product;
	
	private Integer quantity;
	
	private Double totalPrice;

}
