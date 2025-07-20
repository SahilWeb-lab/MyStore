package com.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.product.dto.ProductDTO;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;
import com.product.page.response.ProductPageResponse;

public interface ProductService {

//	Create a method to save product:
	public Boolean saveProduct(ProductDTO product) throws ValidationException;
	
//	Create a method to save multiple product using single transaction:
	public Boolean saveMultipleProducts(List<ProductDTO> productDTOs) throws ValidationException;
	
//	Create a method to get all the products:
	public ProductPageResponse getProducts(Integer pageNo, Integer pageSize);
	
//	Create a method to delete the product:
	public Boolean deleteProduct(Integer productId) throws ResourceNotFoundException;
	
//	Create a method to get product By Id:
	public ProductDTO getProduct(Integer productId) throws ResourceNotFoundException;
	
//	Create a method to find product:
	public ProductPageResponse findProducts(String searchTerm, Integer pageNo, Integer pageSize);
	
//	Create a method to delete multiple product in one transaction:
	public Boolean deleteMultipleProduct(List<Integer> productIds);
	
//	Create a method to get all active products:
	public ProductPageResponse getAllActiveProducts(Integer pageNo, Integer pageSize);

//	Create a method to get all soft deleted product:
	public ProductPageResponse getAllDeletedProduct(Integer pageNo, Integer pageSize);
	
//	Create a method to restore single product:
	public Boolean restoreProduct(Integer productId);
	
//	Crate a method to restore all the products:
	public Boolean restoreAllProducts();
	
//	Create a method to restore multiple products:
	public Boolean restoreMultipleProduct(List<Integer> productIds);
	
//	Create a method to compare two products:
	public List<ProductDTO> getMultipleProducts(List<Integer> productIds);
	
//	Create a method to get product by categories:
	public ProductPageResponse getProductsByCategory(Integer categoryId, Integer pageNo, Integer pageSize, String sortValue, Integer sortOrder);
	
//	Create a method get product by brand:
	public ProductPageResponse getProductByBrand(Integer brandId, Integer pageNo, Integer pageSize);
	
//	Create a method to update product:
	public Boolean updateProduct(ProductDTO productDTO) throws ValidationException;
}
