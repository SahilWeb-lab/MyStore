package com.product.service;

import java.util.List;

import com.product.dto.CategoryDTO;
import com.product.exception.AlreadyRestoredException;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;
import com.product.page.response.CategoryPageResponse;

public interface CategoryService {

//	Create a method to save the category:
	public Boolean saveCategory(CategoryDTO categoryDTO) throws ValidationException;
	
//	Create a method to update the category:
	public Boolean updateCategory(CategoryDTO categoryDTO) throws ValidationException;
	
//	Create a method to get all categories:
	public CategoryPageResponse getCategories(Integer pageNo, Integer pageSize);
	
//	Create a method to soft delete the category:
	public Boolean deleteCategory(Integer categoryId) throws ResourceNotFoundException;
	
//	Create a method to get category by id:
	public CategoryDTO getCategory(Integer categoryId) throws ResourceNotFoundException;
	
//	Create a method to save the multiple category in single transaction:
	public Boolean saveMultipleCategory(List<CategoryDTO> categoryDTOs) throws ValidationException;
	
//	Create a method to restore the deleted category;
	public Boolean restoreSingleCategory(Integer categoryId) throws ResourceNotFoundException;
	
//	Create a method to restore multiple category:
	public Boolean restoreMultipleCategory(List<Integer> categoryIds) throws AlreadyRestoredException;
	
//	Create a method to restore all the categories:
	public Boolean restoreAllCategory() throws AlreadyRestoredException;
	
//	Create a method to get all the deleted categories:
	public CategoryPageResponse getDeletedCategories(Integer pageNo, Integer pageSize);
	
//	Create a method to get all active categories:
	public CategoryPageResponse getAllActiveCategories(Integer pageNo, Integer pageSize);
	
	
//	create a method to delete all categories:
	public Boolean deleteAllCategories();
	
//	Create a method to delete multiple categories:
	public Boolean deleteMultipleCategories(List<Integer> categoryIds);
	
//	Create a method to find category by their name:
	public CategoryPageResponse findCategoryByName(String categoryName, Integer pageNo, Integer pageSize);

	
}
