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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

@Tag(name = "Category APIs", description = "All category related APIs.")
@RequestMapping("/api/v1/category")
public interface CategoryEndpoint {

//	Create a handler to save category:
	@Operation(description = "This api is used to create/save new category and this api is only accessible by admin user.", summary = "Create Category.")
	@PostMapping("/save")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryDTO categoryDTO) throws ValidationException;
	
//	Create a handler to get all the categories:
	@Operation(description = "This api is used to get all the categories active and inactive both. This api is only accessible by admin user.", summary = "Get All Category.")
	@GetMapping("/")
	public ResponseEntity<?> getCategories(@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "0") Integer pageNo);
	
//	Create a handler to soft delete category:
	@Operation(description = "This api is used to delete category by id. This api is only accessible by admin user.", summary = "Delete Category By Id")
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) throws ResourceNotFoundException;
	
//	Create a handler to hard delete category:
	
//	Create a handler to get category:
	@Operation(summary = "Get Category By Id.", description = "This api is used to get category by category id")
	@GetMapping("/public/{categoryId}")
	public ResponseEntity<?> getCategory(@PathVariable Integer categoryId) throws ResourceNotFoundException;
	
//	Create a handler to save multiple category:
	@Operation(summary = "Save Multiple Category.", description = "This api is used to save multiple category. This api is only accessible by admin user.")
	@PostMapping("/save-categories")
	public ResponseEntity<?> saveMultipleCategory(@RequestBody List<CategoryDTO> categoryDTOs) throws ValidationException;
	
//	Create a handler to restore the a single category:
	@Operation(summary = "Restore Category By Id.", description = "This api is used to restore category by category id. This api is only accessible by admin user.")
	@PostMapping("/restore/{categoryId}")
	public ResponseEntity<?> restoreSingleCategory(@PathVariable Integer categoryId) throws ResourceNotFoundException;
	
//	Create a handler to get all the deleted categories:
	@Operation(summary = "Get All Deleted Category", description = "This API is used to get all deleted categories. This API is only accessible to admin.")
	@GetMapping("/deleted-categories")
	public ResponseEntity<?> getDeletedCategories(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a handler to get all the active categoires:
	@Operation(summary = "Get All Active Category", description = "This API is used to get all active category. This API is accessible to anyone.")
	@GetMapping("/public/active-categories")
	public ResponseEntity<?> getAllActiveCategories(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a handler to restore multiple categories:
	@Operation(summary = "Restore Multiple Category", description = "This API is used to restore multiple category. This API is accessible to admin.")
	@PostMapping("/restore-multiple")
	public ResponseEntity<?> restoreMultipleCategories(@RequestBody List<Integer> categoryIds) throws AlreadyRestoredException;
	
//	Create a handler to restore all the categories:
	@Operation(summary = "Get All Category", description = "This API is used to get all category. This API is accessible to admin.")
	@PostMapping("/restore-all")
	public ResponseEntity<?> restoreAllCategories() throws AlreadyRestoredException;
	
	@Operation(summary = "Delete All Category", description = "This API is used to delete all category. This API is accessible to admin.")
//	Create a handler to soft delete all the categories:
	@DeleteMapping("/delete-all")
	public ResponseEntity<?> deleteAllCategories();
	
//	Create a handler to delete multiple categories:
	@Operation(summary = "Delete Multiple Category", description = "This API is used to get delete multiple category. This API is accessible to admin.")
	@DeleteMapping("/delete-multiple")
	public ResponseEntity<?> deleteMultipleCategories(@RequestBody List<Integer> categoriesId);
	
//	Create a handler to search the category:
	@Operation(summary = "Search Category", description = "This API is used to search category. This API is accessible to anyone.")
	@GetMapping("/public/search")
	public ResponseEntity<?> searchCategory(@RequestParam String categoryName, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);
	
//	Create a handler to update the category:
	@Operation(summary = "Update Category", description = "This API is used to update category. This API is accessible to admin.")
	@PutMapping("/update")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryDTO) throws ValidationException;
	
}
