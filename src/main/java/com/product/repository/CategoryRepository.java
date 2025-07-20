package com.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.product.model.Category;
import com.product.model.Product;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
//	Create a method to get all the deleted categories:
	public Page<Category> findByIsDeleted(Boolean isDeleted, Pageable pageable);
	
//	Create a method to restore multiple categories:
	@Modifying
	@Transactional
    @Query("UPDATE Category c SET c.isDeleted = false WHERE c.id IN :ids AND c.isDeleted = true")
    int restoreCategoriesByIds(@Param("ids") List<Integer> ids);
	
//	Create a method to restore all the categories:
	@Modifying
	@Transactional
	@Query("UPDATE Category c SET c.isDeleted = false WHERE c.isDeleted = true")
	int restoreAllCategories();
	
//	Create a method to delete all the categoreis:
	@Modifying
	@Transactional
	@Query("UPDATE Category c SET c.isDeleted = true WHERE c.isDeleted = false")
	public int deleteCategories();
	
//	Create a method to restore/delete multiple categories:
	@Modifying
	@Transactional
    @Query("UPDATE Category c SET c.isDeleted = true WHERE c.id IN :ids AND c.isDeleted = false")
    int deleteCategoriesByIds(@Param("ids") List<Integer> ids);
	
	
//	Create a method to check category is already exists or not:
	public Boolean existsByName(String name);
	
//	Create a method to search category by their name:
	public Page<Category> findByNameContainingIgnoreCaseAndIsDeletedFalse(String categoryName, Pageable pageable);
	
//	Create a method to check the category already exists or not:
	boolean existsByNameIgnoreCaseAndIdNot(String name, Integer id);
	
//	Create a method to find by id and also make sure that category is active:
	public Category findByIdAndIsDeletedFalse(Integer productId);
	
//	Create a method to soft delete the category:
	@Transactional
	@Modifying
	@Query("UPDATE Category c SET c.isDeleted = true WHERE c.id = :id AND c.isDeleted = false")
	public Integer deleteSingleCategory(@Param("id") Integer id);
}
