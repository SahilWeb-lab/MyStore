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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Brand APIs")
@RequestMapping("/api/v1/brand")
public interface BrandEndpoint {

//	Create a handler to save the brand:
	@Operation(summary = "Save Brand", description = "This API is used to save/create brand. This API is only accessible to admin.")
	@PostMapping("/save")
	public ResponseEntity<?> saveBrand(@RequestBody BrandDTO brandDTO) throws ValidationException;
	
//	Create a handler to get all the brands:
	@GetMapping("/")
	@Operation(summary = "Get All Brand", description = "This API is used to get all brand. This API is only accessible to admin.")
	public ResponseEntity<?> getAllBrands(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
	
//	Create a handler to get the single brand;
	@GetMapping("/public/{brandId}")
	@Operation(summary = "Get Brand By Id", description = "This API is used to get brand by Id. This API is accessible to anyone.")
	public ResponseEntity<?> getSingleBrand(@PathVariable Integer brandId) throws ResourceNotFoundException;
	
	
//	Create a handler to delete the brand:
	@DeleteMapping("/single-delete/{brandId}")
	@Operation(summary = "Delete Brand By Id", description = "This API is used to get delete brand by id. This API is only accessible to admin.")
	public ResponseEntity<?> deleteSingleBrand(@PathVariable Integer brandId) throws ResourceNotFoundException;
	
//	Create a handler to get all active brands:
	@Operation(summary = "Get All Active Brand", description = "This API is used to get all active brand. This API is accessible to anyone.")
	@GetMapping("/public/all-active")
	public ResponseEntity<?> getAllActiveBrands(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a method to get deleted brands:
	@GetMapping("/all-deleted")
	@Operation(summary = "Delete All Brand", description = "This API is used to delete all the brand. This API is only accessible to admin.")
	public ResponseEntity<?> getAllDeletedBrands(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a method to search products:
	@GetMapping("/public/search")
	@Operation(summary = "Search Brand", description = "This API is used to search brand. This API is acccessible to anyone.")
	public ResponseEntity<?> searchProducts(@RequestParam String brandName, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
	@PostMapping("/save-all")
	@Operation(summary = "Save All", description = "This API is used to save multiple brand. This API is only accessible to admin.")
	public ResponseEntity<?> saveMultipleBrand(@RequestBody List<BrandDTO> brandDTOs) throws ValidationException;
	
//	Create a handler to restore the brand:
	@Operation(summary = "Restore Brand By Id", description = "This API is used restore brand by id. This API is only accessible to admin.")
	@PostMapping("/restore-single/{brandId}")
	public ResponseEntity<?> restoreSingleBrand(@PathVariable Integer brandId);
	
//	Create a handler to restore multiple brands:
	@Operation(summary = "Restore Multiple Brand", description = "This API is used to restore multiple bran. This is only accessible to admin.")
	@PostMapping("/restore-multiple")
	public ResponseEntity<?> restoreMultipleBrands(@RequestBody List<Integer> brandIds);
	
//	Create a handler to delete multiple brands:
	@Operation(summary = "Delete Multiple Brand", description = "This API is used to delete multiple brand. This API is only accessible to admin.")
	@DeleteMapping("/delete-multiple")
	public ResponseEntity<?> deleteMultipleBrands(@RequestBody List<Integer> brandIds);
	
//	Create a method to update brand:
	@PutMapping("/update")
	@Operation(summary = "Update Brand", description = "This API is used to update brand. This is only accessible to admin.")
	public ResponseEntity<?> updateBrands(@RequestBody BrandDTO brandDTO) throws ValidationException;
	
}
