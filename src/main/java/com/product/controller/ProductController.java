package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.ProductDTO;
import com.product.endpoints.ProductEndpoint;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;
import com.product.page.response.ProductPageResponse;
import com.product.service.ProductService;
import com.product.util.CommonUtils;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController implements ProductEndpoint {

	@Autowired
	private ProductService productService;
	
	public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO) throws ValidationException {
		Boolean saveProduct = productService.saveProduct(productDTO);
		
		if(saveProduct) 
			return CommonUtils.createBuildResponseMessage("Product Saved Successfully!", HttpStatus.CREATED);
		
		return CommonUtils.createErrorResponseMessage("Failed to Save Product!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<?> saveMultipleProducts(@RequestBody List<ProductDTO> productDTOs) throws ValidationException {
		Boolean saveMultipleProducts = productService.saveMultipleProducts(productDTOs);
		
		if(saveMultipleProducts) 
			return CommonUtils.createBuildResponseMessage("Products are saved successfully!", HttpStatus.CREATED); 
		
		return CommonUtils.createErrorResponseMessage("Failed to Save Product!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<?> getAllProducts(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		ProductPageResponse products = productService.getProducts(pageNo, pageSize);
		
		if(CollectionUtils.isEmpty(products.getProducts())) 
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(products, HttpStatus.OK);
	}
	
	public ResponseEntity<?> getProduct(@PathVariable Integer productId) throws ResourceNotFoundException {
		ProductDTO product = productService.getProduct(productId);
		
		return CommonUtils.createBuildResponse(product, HttpStatus.OK);
	}
	
	public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) throws ResourceNotFoundException {
		Boolean deleteProduct = productService.deleteProduct(productId);
		
		if(deleteProduct) 
			return CommonUtils.createBuildResponseMessage("Product Deleted Successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to Delete Product", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<?> searchProduct(@RequestParam String searchTerm, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		ProductPageResponse productResponse = productService.findProducts(searchTerm, pageNo, pageSize);
		
		if(CollectionUtils.isEmpty(productResponse.getProducts())) 
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(productResponse, HttpStatus.OK);
	}
	
	public ResponseEntity<?> deleteMultipleProducts(@RequestBody List<Integer> productIds) {
		Boolean deleteMultipleProduct = productService.deleteMultipleProduct(productIds);
		
		if(deleteMultipleProduct)
			return CommonUtils.createBuildResponseMessage("Products are deleted successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to delete product!", HttpStatus.BAD_REQUEST);
	}
	
	
	public ResponseEntity<?> getAllActiveProducts(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		ProductPageResponse allActiveProducts = productService.getAllActiveProducts(pageNo, pageSize);
		
		if(CollectionUtils.isEmpty(allActiveProducts.getProducts()))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(allActiveProducts, HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> getAllDeletedProducts(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		ProductPageResponse allDeletedProduct = productService.getAllDeletedProduct(pageNo, pageSize);
		
		if(CollectionUtils.isEmpty(allDeletedProduct.getProducts())) 
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(allDeletedProduct, HttpStatus.OK);
	}
	
	public ResponseEntity<?> restoreAllProducts() {
		Boolean restoreAllProducts = productService.restoreAllProducts();
		
		if(restoreAllProducts)
			return CommonUtils.createBuildResponseMessage("Products are restored successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to restore products!", HttpStatus.NOT_FOUND);
	}
	
	
	public ResponseEntity<?> restoreSingleProduct(@PathVariable Integer productId) {
		Boolean restoreProduct = productService.restoreProduct(productId);
		
		if(restoreProduct)
			return CommonUtils.createBuildResponseMessage("Product restored successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to restore product!", HttpStatus.BAD_REQUEST);
	}
	
	
	public ResponseEntity<?> restoreMultipleProducts(@RequestBody List<Integer> productIds) {
		Boolean restoreMultipleProduct = productService.restoreMultipleProduct(productIds);
		
		if(restoreMultipleProduct)
			return CommonUtils.createBuildResponseMessage("Products are restored successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to restore products", HttpStatus.BAD_REQUEST);
	}
	
	
	public ResponseEntity<?> getMultipleProducts(@RequestBody List<Integer> productIds) {
		List<ProductDTO> multipleProducts = productService.getMultipleProducts(productIds);
		
		if(CollectionUtils.isEmpty(multipleProducts))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(multipleProducts, HttpStatus.OK);
	}
	
	public ResponseEntity<?> getProductByCategories(@RequestParam Integer categoryId, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "name") String sortValue, @RequestParam(defaultValue = "0") Integer sortOrder) {
		ProductPageResponse productsByCategory = productService.getProductsByCategory(categoryId, pageNo, pageSize, sortValue, sortOrder);
		
		if(CollectionUtils.isEmpty(productsByCategory.getProducts()))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(productsByCategory, HttpStatus.OK);
	}
	

	public ResponseEntity<?> getProductByBrands(@RequestParam Integer brandId, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		ProductPageResponse productByBrand = productService.getProductByBrand(brandId, pageNo, pageSize);
		
		if(CollectionUtils.isEmpty(productByBrand.getProducts()))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(productByBrand, HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) throws ValidationException {
		Boolean updateProduct = productService.updateProduct(productDTO);
		
		if(updateProduct)
			return CommonUtils.createBuildResponseMessage("Product updated successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to update product!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
