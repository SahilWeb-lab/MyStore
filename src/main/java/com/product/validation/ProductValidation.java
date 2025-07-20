package com.product.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.dto.ProductDTO;
import com.product.exception.ValidationException;
import com.product.repository.BrandRepository;
import com.product.repository.CategoryRepository;

@Component
public class ProductValidation {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;

//	Create a method for product validation logic:
	public void validate(ProductDTO productDTO) throws ValidationException {
		Map<String, Object> errors = new HashMap<>();
		
		if(productDTO.getName() == null || productDTO.getName().isEmpty() || productDTO.getName().isBlank())
			errors.put("name", "Product name is required!");
		
		if(productDTO.getImageUrl() == null || productDTO.getImageUrl().isEmpty() || productDTO.getImageUrl().isBlank())
			errors.put("imageUrl", "Product image url is required!");
		
		if(productDTO.getOriginalPrice() == null)
			errors.put("OriginalPrice", "Original price is required!");
		
		else if(productDTO.getOriginalPrice() < 0)
			errors.put("OriginalPrice", "Original price must be a positive value!");
		
		if(productDTO.getDiscountedPrice() == null)
			errors.put("DiscountedPrice", "Discounted price is required!");
		
		else if(productDTO.getDiscountedPrice() < 0)
			errors.put("OriginalPrice", "Discount price must be a positive value!");
		
		if(!(productDTO.getRating() <= 5 && productDTO.getRating() >= 1))
			errors.put("Rating", "Rating must be between 1 and 5 value!");
		
		if(productDTO.getStockStatus() == null) 
			errors.put("StockStatus", "Stock status is required!");
		
		if(productDTO.getCategory() == null || productDTO.getCategory().getId() == null)
			errors.put("Category Id", "Category id is required!");
		else {
			Boolean existsById = categoryRepository.existsById(productDTO.getCategory().getId());
			if(!existsById)
				errors.put("Category Id", "Category id ["+ productDTO.getCategory().getId() +"] is invalid!");
		}
		
		if(productDTO.getBrand() == null || productDTO.getBrand().getId() == null)
			errors.put("Brand Id", "Brand id is required!");
		else {
			Boolean existsById = brandRepository.existsById(productDTO.getBrand().getId());
			if(!existsById)
				errors.put("Brand Id", "Brand id ["+ productDTO.getBrand().getId() +"] is invalid!");
		}

		if(!errors.isEmpty())
			throw new ValidationException(errors);
	}
	
}
