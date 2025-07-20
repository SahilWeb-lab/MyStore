package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.CategoryDTO;
import com.product.endpoints.CategoryEndpoint;
import com.product.exception.AlreadyRestoredException;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;
import com.product.page.response.CategoryPageResponse;
import com.product.service.CategoryService;
import com.product.util.CommonUtils;

@RestController
public class CategoryController implements CategoryEndpoint {
	
	@Autowired
	private CategoryService categoryService;

	public ResponseEntity<?> saveCategory(@RequestBody CategoryDTO categoryDTO) throws ValidationException {
		Boolean saveCategory = categoryService.saveCategory(categoryDTO);
		
		if(saveCategory)
			return CommonUtils.createBuildResponseMessage("Category Saved Successfully!", HttpStatus.CREATED);
		
		return CommonUtils.createErrorResponseMessage("Failed to Save Category", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	public ResponseEntity<?> getCategories(@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "0") Integer pageNo) {
		CategoryPageResponse categories = categoryService.getCategories(pageNo, pageSize);
		
		if(CollectionUtils.isEmpty(categories.getCategories()))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(categories, HttpStatus.OK);
	}
	

	public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) throws ResourceNotFoundException {
		Boolean deleteCategory = categoryService.deleteCategory(categoryId);
		
		if(deleteCategory) 
			return CommonUtils.createBuildResponseMessage("Category Deleted Successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to Delete Category!", HttpStatus.NOT_FOUND);
	}
	
//	Create a handler to hard delete category:
	

	public ResponseEntity<?> getCategory(@PathVariable Integer categoryId) throws ResourceNotFoundException {
		CategoryDTO category = categoryService.getCategory(categoryId);
		
		if(ObjectUtils.isEmpty(category))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(category, HttpStatus.OK);
	}
	

	public ResponseEntity<?> saveMultipleCategory(@RequestBody List<CategoryDTO> categoryDTOs) throws ValidationException {
		Boolean saveMultipleCategory = categoryService.saveMultipleCategory(categoryDTOs);
		
		if(saveMultipleCategory) 
			return CommonUtils.createBuildResponseMessage("Categories Saved Successfully!", HttpStatus.CREATED);
		
		return CommonUtils.createErrorResponseMessage("Failed to Save Categories!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	public ResponseEntity<?> restoreSingleCategory(@PathVariable Integer categoryId) throws ResourceNotFoundException {
		Boolean restoreSingleCategory = categoryService.restoreSingleCategory(categoryId);
		
		if(restoreSingleCategory)
			return CommonUtils.createBuildResponseMessage("Category Restored Successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to Restore Category!", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	

	public ResponseEntity<?> getDeletedCategories(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		CategoryPageResponse deletedCategories = categoryService.getDeletedCategories(pageNo, pageSize);
		
		if(deletedCategories.getPaginationInfo().getTotalItems() <= 0)
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(deletedCategories, HttpStatus.OK);
	}
	

	public ResponseEntity<?> getAllActiveCategories(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		CategoryPageResponse allActiveCategories = categoryService.getAllActiveCategories(pageNo, pageSize);
		
		if(CollectionUtils.isEmpty(allActiveCategories.getCategories()))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(allActiveCategories, HttpStatus.OK);
	}
	

	public ResponseEntity<?> restoreMultipleCategories(@RequestBody List<Integer> categoryIds) throws AlreadyRestoredException {
		Boolean restoreMultipleCategory = categoryService.restoreMultipleCategory(categoryIds);
		
		if(restoreMultipleCategory)
			return CommonUtils.createBuildResponseMessage("Categories are restored successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Categories are not found!", HttpStatus.NOT_FOUND);
	}
	

	public ResponseEntity<?> restoreAllCategories() throws AlreadyRestoredException {
		Boolean restoreAllCategory = categoryService.restoreAllCategory();
		
		if(restoreAllCategory)
			return CommonUtils.createBuildResponseMessage("All categories are restored successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to restore categories!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	public ResponseEntity<?> deleteAllCategories() {
		Boolean deleteAllCategories = categoryService.deleteAllCategories();
		
		if(deleteAllCategories)
			return CommonUtils.createBuildResponseMessage("Categories are deleted successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Categories are already deleted!", HttpStatus.NOT_FOUND);
	}
	

	public ResponseEntity<?> deleteMultipleCategories(@RequestBody List<Integer> categoriesId) {
		Boolean deleteMultipleCategories = categoryService.deleteMultipleCategories(categoriesId);
		
		if(deleteMultipleCategories)
			return CommonUtils.createBuildResponseMessage("Categories are deleted successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Categories are not found!", HttpStatus.NOT_FOUND);
	}
	

	public ResponseEntity<?> searchCategory(@RequestParam String categoryName, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		CategoryPageResponse categories = categoryService.findCategoryByName(categoryName, pageNo, pageSize);
		
		if(CollectionUtils.isEmpty(categories.getCategories()))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(categories, HttpStatus.OK);
	}

	
	public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryDTO) throws ValidationException {
		Boolean updateCategory = categoryService.updateCategory(categoryDTO);
		
		if(updateCategory)
			return CommonUtils.createBuildResponseMessage("Category updated successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to update category!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
