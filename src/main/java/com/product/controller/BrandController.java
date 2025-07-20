package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.BrandDTO;
import com.product.endpoints.BrandEndpoint;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;
import com.product.page.response.BrandPageResponse;
import com.product.service.BrandService;
import com.product.util.CommonUtils;

@RestController
public class BrandController implements BrandEndpoint {
	
	@Autowired
	private BrandService brandService;

	public ResponseEntity<?> saveBrand(@RequestBody BrandDTO brandDTO) throws ValidationException {
		Boolean saveBrand = brandService.saveBrand(brandDTO);
		
		if(saveBrand)
			return CommonUtils.createBuildResponseMessage("Brand Saved Successfully!", HttpStatus.CREATED);
		
		return CommonUtils.createErrorResponseMessage("Failed to save brand!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	public ResponseEntity<?> getAllBrands(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		BrandPageResponse brands = brandService.getAllBrands(pageNo, pageSize);
		
		if(CollectionUtils.isEmpty(brands.getBrandDTOs()))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(brands, HttpStatus.OK);
	}
	

	public ResponseEntity<?> getSingleBrand(@PathVariable Integer brandId) throws ResourceNotFoundException {
		BrandDTO singleBrand = brandService.getSingleBrand(brandId);
		
		if(ObjectUtils.isEmpty(singleBrand))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(singleBrand, HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> deleteSingleBrand(@PathVariable Integer brandId) throws ResourceNotFoundException {
		Boolean deleteSingleBrand = brandService.deleteSingleBrand(brandId);
		
		if(deleteSingleBrand)
			return CommonUtils.createBuildResponseMessage("Brand with id ["+brandId+"] deleted successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to delete brand", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	public ResponseEntity<?> getAllActiveBrands(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		BrandPageResponse allActiveBrands = brandService.getAllActiveBrands(pageNo, pageSize);
		
		if(CollectionUtils.isEmpty(allActiveBrands.getBrandDTOs()))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(allActiveBrands, HttpStatus.OK);
		
	}
	

	public ResponseEntity<?> getAllDeletedBrands(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		BrandPageResponse allDeletedBrands = brandService.getAllDeletedBrands(pageNo, pageSize);
		
		if(CollectionUtils.isEmpty(allDeletedBrands.getBrandDTOs()))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(allDeletedBrands, HttpStatus.OK);
	}
	

	public ResponseEntity<?> searchProducts(@RequestParam String brandName, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		BrandPageResponse brandPageResponse = brandService.searchBrandByName(brandName, pageNo, pageSize);
		
		if(CollectionUtils.isEmpty(brandPageResponse.getBrandDTOs()))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(brandPageResponse, HttpStatus.OK);
	}
	

	public ResponseEntity<?> saveMultipleBrand(@RequestBody List<BrandDTO> brandDTOs) throws ValidationException {
		Boolean saveMultipleBrand = brandService.saveMultipleBrand(brandDTOs);
		
		if(saveMultipleBrand)
			return CommonUtils.createBuildResponseMessage("Brands are saved successfully!", HttpStatus.CREATED);
		
		return CommonUtils.createErrorResponseMessage("Faile to save brands", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	

	public ResponseEntity<?> restoreSingleBrand(@PathVariable Integer brandId) {
		Boolean restoreSingleBrand = brandService.restoreSingleBrand(brandId);
		
		if(restoreSingleBrand)
			return CommonUtils.createBuildResponseMessage("Brand with id ["+brandId+"] restored successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to restore brand!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	public ResponseEntity<?> restoreMultipleBrands(@RequestBody List<Integer> brandIds) {
		Boolean restoreMultipleBrand = brandService.restoreMultipleBrand(brandIds);
		
		if(restoreMultipleBrand)
			return CommonUtils.createBuildResponseMessage("Brands are restored successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to restore brands!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	public ResponseEntity<?> deleteMultipleBrands(@RequestBody List<Integer> brandIds) {
		Boolean deleteMultipleBrand = brandService.deleteMultipleBrand(brandIds);
		
		if(deleteMultipleBrand)
			return CommonUtils.createBuildResponseMessage("Brands are deleted successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to delete brands!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	public ResponseEntity<?> updateBrands(@RequestBody BrandDTO brandDTO) throws ValidationException {
		Boolean updateBrand = brandService.updateBrand(brandDTO);
		
		if(updateBrand)
			return CommonUtils.createBuildResponseMessage("Brand is updated successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to update brand!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
