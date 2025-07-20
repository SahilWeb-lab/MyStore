package com.product.dto;

import java.util.List;

import com.product.model.Product;
import com.product.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WishlistDTO {

	private Product product;

}
