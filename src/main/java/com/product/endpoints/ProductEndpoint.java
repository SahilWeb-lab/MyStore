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

import com.product.dto.ProductDTO;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;

@RequestMapping("/api/v1/product")
public interface ProductEndpoint {

//	Create a handler to save the product:
	@PostMapping("/save")
	public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO) throws ValidationException;
	
//	Create a handler to save multiple products in a single transaction:
	@PostMapping("save-many")
	public ResponseEntity<?> saveMultipleProducts(@RequestBody List<ProductDTO> productDTOs) throws ValidationException;
	
//	Create a handler to get all the products:
	@GetMapping("/")
	public ResponseEntity<?> getAllProducts(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a handler to get product by id:
	@GetMapping("/public/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable Integer productId) throws ResourceNotFoundException;
	
//	Create a handler to delete the product:
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) throws ResourceNotFoundException;
	
//	Create a method to find the product by their name:
	@GetMapping("public/search")
	public ResponseEntity<?> searchProduct(@RequestParam String searchTerm, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a handler to delete multiple products:
	@DeleteMapping("/delete-multiple")
	public ResponseEntity<?> deleteMultipleProducts(@RequestBody List<Integer> productIds);
	
//	Create a handler to get all the active products:
	@GetMapping("/public/all-active")
	public ResponseEntity<?> getAllActiveProducts(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a handler to get all the deleted products:
	@GetMapping("/all-deleted")
	public ResponseEntity<?> getAllDeletedProducts(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
	
//	Create a handler to restore all the prodcuts:
	@PostMapping("/restore-all")
	public ResponseEntity<?> restoreAllProducts();
	
//	Create a handler to restore the single product:
	@PostMapping("/restore/{productId}")
	public ResponseEntity<?> restoreSingleProduct(@PathVariable Integer productId);
	
//	Create a handler to restore multiple product:
	@PostMapping("/restore-multiple")
	public ResponseEntity<?> restoreMultipleProducts(@RequestBody List<Integer> productIds);
	
//	Create a handler to get multiple the products:
	@GetMapping("/public/mulitple-product")
	public ResponseEntity<?> getMultipleProducts(@RequestBody List<Integer> productIds);
	
	
//	Create a method get product by categories:
	@GetMapping("/public/get-by-category")
	public ResponseEntity<?> getProductByCategories(@RequestParam Integer categoryId, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "name") String sortValue, @RequestParam(defaultValue = "0") Integer sortOrder);
	
//	Create a handler to get product by brands:
	@GetMapping("/public/get-by-brand")
	public ResponseEntity<?> getProductByBrands(@RequestParam Integer brandId, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a handler to update the product:
	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) throws ValidationException;
}
