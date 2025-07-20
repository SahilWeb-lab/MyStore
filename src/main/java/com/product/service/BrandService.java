package com.product.service;

import java.util.List;

import com.product.dto.BrandDTO;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;
import com.product.page.response.BrandPageResponse;

public interface BrandService {

//	Create a save brand
	public Boolean saveBrand(BrandDTO brandDTO) throws ValidationException;
	
//	Create a method to get all brands:
	public BrandPageResponse getAllBrands(Integer pageNo, Integer pageSize);
	
//	Create a method to get single brand:
	public BrandDTO getSingleBrand(Integer brandId) throws ResourceNotFoundException;
	
//	Create a method to delete the brand:
	public Boolean deleteSingleBrand(Integer brandId) throws ResourceNotFoundException;
	
//	Create a method to get all active brands:
	public BrandPageResponse getAllActiveBrands(Integer pageNo, Integer pageSize);
	
//	Create a method to get all deleted brands:
	public BrandPageResponse getAllDeletedBrands(Integer pageNo, Integer pageSize);
	
//	Create a method to search brand by name:
	public BrandPageResponse searchBrandByName(String brandName, Integer pageNo, Integer pageSize);
	
//	Create a method to save multiple brand:
	public Boolean saveMultipleBrand(List<BrandDTO> brandDTOs) throws ValidationException;
	
//	Create a method to restore single brand:
	public Boolean restoreSingleBrand(Integer brandId);
	
//	Create a method to restore multiple brand:
	public Boolean restoreMultipleBrand(List<Integer> brandIds);
	
//	Create a method to delete multiple brand:
	public Boolean deleteMultipleBrand(List<Integer> brandIds);
	
//	Create a method to update brand:
	public Boolean updateBrand(BrandDTO brandDTO) throws ValidationException;
}
