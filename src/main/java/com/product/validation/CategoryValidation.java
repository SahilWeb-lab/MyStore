package com.product.validation;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.product.dto.CategoryDTO;
import com.product.exception.ValidationException;
import com.product.repository.CategoryRepository;

@Component
public class CategoryValidation {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public void validate(CategoryDTO categoryDTO) throws ValidationException {
		
		Map<String, Object> errors = new LinkedHashMap<>();
			
//		Validate category name:
		if(categoryDTO.getName() == null || categoryDTO.getName().isEmpty() || categoryDTO.getName().isBlank()) {
			errors.put("name", "Category name is required!");
		} 
		
		if(categoryDTO.getId() == null) {			
			Boolean existsByName = categoryRepository.existsByName(categoryDTO.getName());
			
			if(existsByName)
				errors.put("name", "Category already exists with name : ["+ categoryDTO.getName() +"]");
		} else {
			boolean existsById = categoryRepository.existsById(categoryDTO.getId());
			
			if(!existsById)
				errors.put("ID", "Invalid Id : ["+ categoryDTO.getId() +"]");
				
			boolean existsByNameAndIdNot = categoryRepository.existsByNameIgnoreCaseAndIdNot(categoryDTO.getName(), categoryDTO.getId());
			if(existsByNameAndIdNot) 
				errors.put("name","Category with name ["+ categoryDTO.getName() +"] already exists!");
		}
		

		if(categoryDTO.getImageUrl() == null || categoryDTO.getImageUrl().isEmpty() || categoryDTO.getImageUrl().isBlank()) 
			errors.put("imageUrl", "Category image url is required!");
			
		if(!errors.isEmpty()) {
			throw new ValidationException(errors);
		}
			
		
	}
	
}
