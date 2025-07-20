package com.product.dto;

import com.product.model.Brand;
import com.product.model.Category;

import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

	private Integer id;
	
	private String name;
	
	private String imageUrl;
	
	private Float originalPrice;
	
	private Float discountedPrice;
	
	private Integer rating;
	
	private String badges;
	
	private String colors;
	
	private String sizes;
	
	private Integer stockStatus;
	
	private Category category;
	
	private Brand brand;
	
	private Boolean isDeleted = false;
	
}
