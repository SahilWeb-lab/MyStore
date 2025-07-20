package com.product.endpoints;

import java.util.List;

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

import com.product.dto.CategoryDTO;
import com.product.exception.AlreadyRestoredException;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;
import com.product.page.response.CategoryPageResponse;
import com.product.util.CommonUtils;

@RequestMapping("/api/v1/category")
public interface CategoryEndpoint {

//	Create a handler to save category:
	@PostMapping("/save")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryDTO categoryDTO) throws ValidationException;
	
//	Create a handler to get all the categories:
	@GetMapping("/")
	public ResponseEntity<?> getCategories(@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "0") Integer pageNo);
	
//	Create a handler to soft delete category:
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) throws ResourceNotFoundException;
	
//	Create a handler to hard delete category:
	
//	Create a handler to get category:
	@GetMapping("/public/{categoryId}")
	public ResponseEntity<?> getCategory(@PathVariable Integer categoryId) throws ResourceNotFoundException;
	
//	Create a handler to save multiple category:
	@PostMapping("/save-categories")
	public ResponseEntity<?> saveMultipleCategory(@RequestBody List<CategoryDTO> categoryDTOs) throws ValidationException;
	
//	Create a handler to restore the a single category:
	@PostMapping("/restore/{categoryId}")
	public ResponseEntity<?> restoreSingleCategory(@PathVariable Integer categoryId) throws ResourceNotFoundException;
	
//	Create a handler to get all the deleted categories:
	@GetMapping("/deleted-categories")
	public ResponseEntity<?> getDeletedCategories(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a handler to get all the active categoires:
	@GetMapping("/public/active-categories")
	public ResponseEntity<?> getAllActiveCategories(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a handler to restore multiple categories:
	@PostMapping("/restore-multiple")
	public ResponseEntity<?> restoreMultipleCategories(@RequestBody List<Integer> categoryIds) throws AlreadyRestoredException;
	
//	Create a handler to restore all the categories:
	@PostMapping("/restore-all")
	public ResponseEntity<?> restoreAllCategories() throws AlreadyRestoredException;
	
//	Create a handler to soft delete all the categories:
	@DeleteMapping("/delete-all")
	public ResponseEntity<?> deleteAllCategories();
	
//	Create a handler to delete multiple categories:
	@DeleteMapping("/delete-multiple")
	public ResponseEntity<?> deleteMultipleCategories(@RequestBody List<Integer> categoriesId);
	
//	Create a handler to search the category:
	@GetMapping("/public/search")
	public ResponseEntity<?> searchCategory(@RequestParam String categoryName, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a handler to update the category:
	@PutMapping("/update")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryDTO) throws ValidationException;
	
}
