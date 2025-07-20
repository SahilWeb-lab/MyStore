package com.product.endpoints;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.dto.BrandDTO;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;

@RequestMapping("/api/v1/brand")
public interface BrandEndpoint {

//	Create a handler to save the brand:
	@PostMapping("/save")
	public ResponseEntity<?> saveBrand(@RequestBody BrandDTO brandDTO) throws ValidationException;
	
//	Create a handler to get all the brands:
	@GetMapping("/")
	public ResponseEntity<?> getAllBrands(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
	
//	Create a handler to get the single brand;
	@GetMapping("/public/{brandId}")
	public ResponseEntity<?> getSingleBrand(@PathVariable Integer brandId) throws ResourceNotFoundException;
	
	
//	Create a handler to delete the brand:
	@DeleteMapping("/single-delete/{brandId}")
	public ResponseEntity<?> deleteSingleBrand(@PathVariable Integer brandId) throws ResourceNotFoundException;
	
//	Create a handler to get all active brands:
	@GetMapping("/public/all-active")
	public ResponseEntity<?> getAllActiveBrands(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a method to get deleted brands:
	@GetMapping("/all-deleted")
	public ResponseEntity<?> getAllDeletedBrands(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a method to search products:
	@GetMapping("/public/search")
	public ResponseEntity<?> searchProducts(@RequestParam String brandName, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
	@PostMapping("/save-all")
	public ResponseEntity<?> saveMultipleBrand(@RequestBody List<BrandDTO> brandDTOs) throws ValidationException;
	
//	Create a handler to restore the brand:
	@PostMapping("/restore-single/{brandId}")
	public ResponseEntity<?> restoreSingleBrand(@PathVariable Integer brandId);
	
//	Create a handler to restore multiple brands:
	@PostMapping("/restore-multiple")
	public ResponseEntity<?> restoreMultipleBrands(@RequestBody List<Integer> brandIds);
	
//	Create a handler to delete multiple brands:
	@DeleteMapping("/delete-multiple")
	public ResponseEntity<?> deleteMultipleBrands(@RequestBody List<Integer> brandIds);
	
//	Create a method to update brand:
	@PutMapping("/update")
	public ResponseEntity<?> updateBrands(@RequestBody BrandDTO brandDTO) throws ValidationException;
	
}
