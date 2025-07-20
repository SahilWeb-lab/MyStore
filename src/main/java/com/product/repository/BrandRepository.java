package com.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.model.Brand;

import jakarta.transaction.Transactional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

//	Create a method to soft delete brand:
	@Transactional
	@Modifying
	@Query("UPDATE Brand b SET b.isDeleted = true WHERE b.id = :id AND b.isDeleted = false")
	public void deleteSingleBrand(@Param("id") Integer id);
	
//	Create a method to restore single brand:
	@Transactional
	@Modifying
	@Query("UPDATE Brand b SET b.isDeleted = false WHERE b.id = :id AND isDeleted = true")
	public Integer restoreSingleBrand(@Param("id") Integer brandId);

//	Create a method to get all active brand:
	public Page<Brand> findByIsDeletedFalse(Pageable pageable);
	
//	Create a method to get all deleted brand:
	public Page<Brand> findByIsDeletedTrue(Pageable pageable);
	
//	Create a method to find brand by name:
	public Page<Brand> findByNameContainingIgnoreCaseAndIsDeletedFalse(String name, Pageable pageable);
	
//	Create a method to get active brand by id only:
	public Brand findByIdAndIsDeletedFalse(Integer brandId);
	
//	Create a method to restore multiple brand:
	@Transactional
	@Modifying
	@Query("UPDATE Brand b SET b.isDeleted = false WHERE b.id IN :ids AND b.isDeleted = true")
	public Integer restoreMultipleBrand(@Param("ids") List<Integer> brandIds);
	
//	Create a method to delete multiple brand:
	@Transactional
	@Modifying
	@Query("UPDATE Brand b SET b.isDeleted = true WHERE b.id IN :ids AND b.isDeleted = false")
	public Integer deleteMultipleBrand(@Param("ids") List<Integer> brandIds);
	
//	Create a method to check brand is exists by name or not:
	public Boolean existsByNameIgnoreCase(String name);
	
//	Create a method to check the category already exists or not:
	boolean existsByNameIgnoreCaseAndIdNot(String name, Integer id);
}
