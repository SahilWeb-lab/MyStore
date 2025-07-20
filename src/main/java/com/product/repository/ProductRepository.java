package com.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.dto.ProductDTO;
import com.product.model.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("""
			    SELECT p FROM Product p
			    WHERE p.isDeleted = false AND LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
			       OR LOWER(p.category.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
			       OR LOWER(p.brand.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
			""")
	Page<Product> searchProducts(@Param("keyword") String keyword, Pageable pageable);

//	Create a method to soft delete the product
	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.isDeleted = true WHERE p.id = :productId")
	public Integer deleteProduct(@Param("productId") Integer productId);

//	Create a method to delete multiple product:
	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.isDeleted = true WHERE p.id IN :ids AND p.isDeleted = false")
	public Integer deleteMultipleProduct(@Param("ids") List<Integer> ids);
	
	
//	Create a method to restore multiple product:
	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.isDeleted = false WHERE p.id IN :ids AND p.isDeleted = true")
	public Integer restoreMultipleProduct(@Param("ids") List<Integer> ids);
	
	
//	Create a method to restore all the products:
	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.isDeleted = false WHERE p.isDeleted = true")
	public Integer restoreAllProducts();
	
//	Create a method to restore single product:
	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.isDeleted = false WHERE p.isDeleted = true AND p.id = :productId")
	public Integer restoreSingleProduct(Integer productId);
	
//	Create a method to get all the active products:
	public Page<Product> findByIsDeletedFalse(Pageable pageable);
	
//	Create a method to get all deleted products:
	public Page<Product> findByIsDeletedTrue(Pageable pageable);
	
//	Create a method to get multiple products:
	public List<Product> findByIdInAndIsDeletedFalse(List<Integer> productIds);
	
//	Create a method to get product by category:
	public Page<Product> findByCategory_IdAndIsDeletedFalse(Integer categoryId, Pageable page);
	
//	Create a method get product by category:
	public Page<Product> findByBrand_idAndIsDeletedFalse(Integer brandId, Pageable pageable);
	
//	Create a method to get single product but active also:
	public Product findByIdAndIsDeletedFalse(Integer productId);
}
