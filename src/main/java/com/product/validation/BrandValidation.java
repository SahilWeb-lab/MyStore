package com.product.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.product.dto.BrandDTO;
import com.product.exception.ValidationException;
import com.product.repository.BrandRepository;

@Component
public class BrandValidation {

	@Autowired
	private BrandRepository brandRepository;


//	Create a method to validate brand:
	public void validate(BrandDTO brandDTO) throws ValidationException {
		Map<String, Object> errors = new HashMap<>();
		
//		Id validation:
		if(brandDTO.getId() != null) {
			boolean existsById = brandRepository.existsById(brandDTO.getId());
			
			if(!existsById)
				errors.put("ID", "Invalid Id : " + brandDTO.getId());
			
			Boolean existsByBrandName = brandRepository.existsByNameIgnoreCaseAndIdNot(brandDTO.getName(), brandDTO.getId());
			
			if(existsByBrandName)
				errors.put("name","Brand with name ["+ brandDTO.getName() +"] already exists!");
			
		} else {
			Boolean existsByName = brandRepository.existsByNameIgnoreCase(brandDTO.getName());
//			Check brand is already exists or not:
			if (existsByName)
				errors.put("name", "Brand name [" + brandDTO.getName() + "] already exists!");
		}

//		Name validation:
		if (brandDTO.getName() == null || brandDTO.getName().isBlank() || brandDTO.getName().isEmpty())
			errors.put("name", "Brand name is required!");

//		logoUrl validation:
		if (brandDTO.getLogoUrl() == null || brandDTO.getLogoUrl().isEmpty() || brandDTO.getLogoUrl().isBlank())
			errors.put("logoUrl", "Brand logo url is required!");

		if (!ObjectUtils.isEmpty(errors))
			throw new ValidationException(errors);
	}

}
