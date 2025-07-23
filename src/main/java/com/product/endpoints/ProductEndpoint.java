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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product APIs", description = "All product related APIs.")
@RequestMapping("/api/v1/product")
public interface ProductEndpoint {

//	Create a handler to save the product:
	@Operation(summary = "Save Product", description = "This API is used to save/create product. This API is only accessible to admin.")
	@PostMapping("/save")
	public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO) throws ValidationException;
	
//	Create a handler to save multiple products in a single transaction:
	@Operation(summary = "Save Multiple Product", description = "This API is used to save/create multiple product. This API is only accessible to admin.")
	@PostMapping("save-many")
	public ResponseEntity<?> saveMultipleProducts(@RequestBody List<ProductDTO> productDTOs) throws ValidationException;
	
//	Create a handler to get all the products:
	@Operation(summary = "Get All Product", description = "This API is used to get all product. This API is only accessible to admin.")
	@GetMapping("/")
	public ResponseEntity<?> getAllProducts(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a handler to get product by id:
	@Operation(summary = "Get Product By Id", description = "This API is used to get product by id. This API is accessible to anyone.")
	@GetMapping("/public/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable Integer productId) throws ResourceNotFoundException;
	
//	Create a handler to delete the product:
	@Operation(summary = "Delete Product By Id", description = "This API is used to delete product by id. This API is only accessible to admin.")
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) throws ResourceNotFoundException;
	
//	Create a method to find the product by their name:
	@Operation(summary = "Search Product", description = "This API is used to search product by their name. This API is accessible to anyone.")
	@GetMapping("public/search")
	public ResponseEntity<?> searchProduct(@RequestParam String searchTerm, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a handler to delete multiple products:
	@Operation(summary = "Delete Multiple Product", description = "This API is used to delete multiple product. This API is only accessible to admin.")
	@DeleteMapping("/delete-multiple")
	public ResponseEntity<?> deleteMultipleProducts(@RequestBody List<Integer> productIds);
	
//	Create a handler to get all the active products:
	@Operation(summary = "Get All Active Product", description = "This API is used to get all active product. This API is accessible to anyone.")
	@GetMapping("/public/all-active")
	public ResponseEntity<?> getAllActiveProducts(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a handler to get all the deleted products:
	@Operation(summary = "Get All Deleted Product", description = "This API is used to get all deleted product. This API is only accessible to admin.")
	@GetMapping("/all-deleted")
	public ResponseEntity<?> getAllDeletedProducts(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
	
//	Create a handler to restore all the prodcuts:
	@Operation(summary = "Restore All Product", description = "This API is used to restore all product. This API is only accessible to admin.")
	@PostMapping("/restore-all")
	public ResponseEntity<?> restoreAllProducts();
	
//	Create a handler to restore the single product:
	@Operation(summary = "Restore Product By Id", description = "This API is used to restore single product by their id. This API is only accessible to admin.")
	@PostMapping("/restore/{productId}")
	public ResponseEntity<?> restoreSingleProduct(@PathVariable Integer productId);
	
//	Create a handler to restore multiple product:
	@Operation(summary = "Restore Multiple Product", description = "This API is used to restore multiple product. This API is only accessible to admin.")
	@PostMapping("/restore-multiple")
	public ResponseEntity<?> restoreMultipleProducts(@RequestBody List<Integer> productIds);
	
	@Operation(summary = "Get Multiple Product", description = "This API is used to get multiple product. This API is accessible to anyone.")
//	Create a handler to get multiple the products:
	@GetMapping("/public/mulitple-product")
	public ResponseEntity<?> getMultipleProducts(@RequestBody List<Integer> productIds);
	
	
//	Create a method get product by categories:
	@Operation(summary = "Get Products By Category Id", description = "This API is used to get all product according to category. This API is accessible to anyone.")
	@GetMapping("/public/get-by-category")
	public ResponseEntity<?> getProductByCategories(@RequestParam Integer categoryId, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "name") String sortValue, @RequestParam(defaultValue = "0") Integer sortOrder);
	
//	Create a handler to get product by brands:
	@Operation(summary = "Get Products By Brand Id", description = "This API is used to get all product according to brand. This API is accessible to anyone.")
	@GetMapping("/public/get-by-brand")
	public ResponseEntity<?> getProductByBrands(@RequestParam Integer brandId, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a handler to update the product:
	@Operation(summary = "Update Product", description = "This API is used to update product by their id. This is only accessible to admin.")
	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) throws ValidationException;
}
