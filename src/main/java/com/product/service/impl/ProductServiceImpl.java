package com.product.service.impl;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.product.dto.ProductDTO;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;
import com.product.model.Product;
import com.product.page.response.PaginationInfo;
import com.product.page.response.ProductPageResponse;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;
import com.product.validation.ProductValidation;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductValidation productValidation;

	@Override
	public Boolean saveProduct(ProductDTO productDTO) throws ValidationException {
		
//		Call the validate method for validation:
		productValidation.validate(productDTO);
		
		Product product = modelMapper.map(productDTO, Product.class);
		Product save = productRepository.save(product);

		if (!ObjectUtils.isEmpty(save))
			return true;

		return false;
	}

	@Override
	public ProductPageResponse getProducts(Integer pageNo, Integer pageSize) {
		Page<Product> products = productRepository.findAll(PageRequest.of(pageNo, pageSize));
		Page<ProductDTO> productDTOs = products.map(p -> modelMapper.map(p, ProductDTO.class));
		return getProductPageResponse(productDTOs);
	}

	@Override
	public Boolean deleteProduct(Integer productId) throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id : " + productId));

		if (product.getIsDeleted())
			return false;

		Integer totalDeletedProduct = productRepository.deleteProduct(productId);

		if (totalDeletedProduct > 0)
			return true;

		return false;
	}

	@Override
	public ProductDTO getProduct(Integer productId) throws ResourceNotFoundException {
		Product product = productRepository.findByIdAndIsDeletedFalse(productId);
		
		if(ObjectUtils.isEmpty(product))
			throw new ResourceNotFoundException("Product not found with id : " + productId);

		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

		return productDTO;
	}

	@Override
	public ProductPageResponse findProducts(String searchTerm, Integer pageNo, Integer pageSize) {
		Page<Product> products = productRepository.searchProducts(searchTerm, PageRequest.of(pageNo, pageSize));
		Page<ProductDTO> productDTOs = products.map(p -> modelMapper.map(p, ProductDTO.class));

		return getProductPageResponse(productDTOs);
	}

	@Override
	public Boolean saveMultipleProducts(List<ProductDTO> productDTOs) throws ValidationException {
		
//		Validate product:
		for(ProductDTO productDTO : productDTOs)
			productValidation.validate(productDTO);
		
		List<Product> productList = productDTOs.stream().map(pro -> modelMapper.map(pro, Product.class)).toList();
		List<Product> saveAll = productRepository.saveAll(productList);

		if (CollectionUtils.isEmpty(saveAll))
			return false;
		return true;
	}

	@Override
	public Boolean deleteMultipleProduct(List<Integer> productIds) {
		Integer deleteMultipleProduct = productRepository.deleteMultipleProduct(productIds);

		if (deleteMultipleProduct > 0)
			return true;

		return false;
	}

	@Override
	public ProductPageResponse getAllActiveProducts(Integer pageNo, Integer pageSize) {
		Page<Product> products = productRepository
				.findByIsDeletedFalse(PageRequest.of(pageNo, pageSize, Sort.by("id").ascending()));
		Page<ProductDTO> productDTOs = products.map(p -> modelMapper.map(p, ProductDTO.class));
		return getProductPageResponse(productDTOs);
	}

	@Override
	public ProductPageResponse getAllDeletedProduct(Integer pageNo, Integer pageSize) {
		Page<Product> deletedProducts = productRepository.findByIsDeletedTrue(PageRequest.of(pageNo, pageSize, Sort.by("id").ascending()));
		Page<ProductDTO> deletedProductsDTO = deletedProducts.map(p -> modelMapper.map(p, ProductDTO.class));
		return getProductPageResponse(deletedProductsDTO);
	}
	
	
	@Override
	public Boolean restoreProduct(Integer productId) {
		Integer totalRestoredProduct = productRepository.restoreSingleProduct(productId);
		
		if(totalRestoredProduct > 0)
			return true;
		
		return false;
	}

	@Override
	public Boolean restoreAllProducts() {
		Integer totalRestoredProducts = productRepository.restoreAllProducts();
		
		if(totalRestoredProducts > 0)
			return true;
		
		return false;
	}

	@Override
	public Boolean restoreMultipleProduct(List<Integer> productIds) {
		Integer totalRestoredProduct = productRepository.restoreMultipleProduct(productIds);
		
		if(totalRestoredProduct > 0)
			return true;
		
		return false;
	}

	@Override
	public List<ProductDTO> getMultipleProducts(List<Integer> productIds) {
		List<Product> productList = productRepository.findByIdInAndIsDeletedFalse(productIds);
		List<ProductDTO> productDTOList = productList.stream().map(p -> modelMapper.map(p, ProductDTO.class)).toList();
		return productDTOList;
	}
	
	
//	Create a method to get products by category:
	@Override
	public ProductPageResponse getProductsByCategory(Integer categoryId, Integer pageNo, Integer pageSize, String sortValue, Integer sortOrder) {
		Sort sort = Sort.by((sortOrder == 0) ? Sort.Direction.ASC : Sort.Direction.DESC, sortValue);
		Page<Product> productPage = productRepository.findByCategory_IdAndIsDeletedFalse(categoryId,PageRequest.of(pageNo, pageSize, sort));
		Page<ProductDTO> productPageDTO = productPage.map(p -> modelMapper.map(p, ProductDTO.class));
		
		return getProductPageResponse(productPageDTO);
	}
	
	
	
	


	@Override
	public ProductPageResponse getProductByBrand(Integer brandId, Integer pageNo, Integer pageSize) {
		Page<Product> productPage = productRepository.findByBrand_idAndIsDeletedFalse(brandId, PageRequest.of(pageNo, pageSize));
		
		Page<ProductDTO> productPageDTO = productPage.map(p -> modelMapper.map(p, ProductDTO.class));
		return getProductPageResponse(productPageDTO);
	}

	@Override
	public Boolean updateProduct(ProductDTO productDTO) throws ValidationException {
		if(productDTO.getId() == null) 
			throw new ValidationException(Map.of("Id", "Id is required for updation!"));
		
		Boolean saveProduct = saveProduct(productDTO);
		
		if(saveProduct)
			return true;
			
		return false;
	}
	
	
	
	
	
	

	// Create a method:
	public ProductPageResponse getProductPageResponse(Page<ProductDTO> productDTOs) {
		PaginationInfo paginationInfo = PaginationInfo.builder().totalItems(productDTOs.getTotalElements())
				.totalPages(productDTOs.getTotalPages()).currentPage(productDTOs.getNumber() + 1)
				.lastPage(productDTOs.isLast()).build();

		return ProductPageResponse.builder().paginationInfo(paginationInfo).products(productDTOs.getContent()).build();
	}
}
