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

import com.product.dto.CategoryDTO;
import com.product.exception.AlreadyRestoredException;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;
import com.product.model.Category;
import com.product.page.response.CategoryPageResponse;
import com.product.page.response.PaginationInfo;
import com.product.repository.CategoryRepository;
import com.product.service.CacheManagerService;
import com.product.service.CategoryService;
import com.product.validation.CategoryValidation;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryValidation categoryValidation;
	
	@Autowired
	private CacheManagerService cacheManagerService;

	@Override
	public Boolean saveCategory(CategoryDTO categoryDTO) throws ValidationException {

//		Validate Category:
		categoryValidation.validate(categoryDTO);

		Category category = modelMapper.map(categoryDTO, Category.class);
		Category saveCategory = categoryRepository.save(category);

		if (ObjectUtils.isEmpty(saveCategory))
			return false;

		return true;
	}

	@Override
	public CategoryPageResponse getCategories(Integer pageNo, Integer pageSize) {
		Page<Category> categoryList = categoryRepository
				.findAll(PageRequest.of(pageNo, pageSize, Sort.by("id").descending()));
		Page<CategoryDTO> categoryDTOList = categoryList.map(c -> modelMapper.map(c, CategoryDTO.class));
		return getCategoryPageResponse(categoryDTOList);
	}

	@Override
	public Boolean deleteCategory(Integer categoryId) throws ResourceNotFoundException {
		Integer totalDeletedCategory = categoryRepository.deleteSingleCategory(categoryId);
		
		if(totalDeletedCategory > 0) {
			cacheManagerService.removeCacheByNames(List.of("allCategory", "activeCategories"));
			return true;
		}
		
		return false;
	}

	@Override
	public CategoryDTO getCategory(Integer categoryId) throws ResourceNotFoundException {
		Category category = categoryRepository.findByIdAndIsDeletedFalse(categoryId);
		if(ObjectUtils.isEmpty(category))
			throw new ResourceNotFoundException("Category not found with id : " + categoryId);
		
		CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
		return categoryDTO;
	}

	@Override
	public Boolean saveMultipleCategory(List<CategoryDTO> categoryDTOs) throws ValidationException {
		
//		Validate category:
		for(CategoryDTO categoryDTO : categoryDTOs)
			categoryValidation.validate(categoryDTO);
		
		List<Category> categoryList = categoryDTOs.stream().map(c -> modelMapper.map(c, Category.class)).toList();
		List<Category> saveAll = categoryRepository.saveAll(categoryList);

		if (CollectionUtils.isEmpty(saveAll))
			return false;

		return true;
	}

	@Override
	public Boolean restoreSingleCategory(Integer categoryId) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id : [" + categoryId + "]"));

		if (category.getIsDeleted()) {
			category.setIsDeleted(false);
			Category saveCategory = categoryRepository.save(category);

			if (ObjectUtils.isEmpty(saveCategory))
				return false;

			return true;
		}

		return false;
	}

	@Override
	public Boolean restoreMultipleCategory(List<Integer> categoryIds) throws AlreadyRestoredException {
		int totalCategoryRestored = categoryRepository.restoreCategoriesByIds(categoryIds);

		if (totalCategoryRestored > 0)
			return true;

		return false;
	}

	@Override
	public Boolean restoreAllCategory() throws AlreadyRestoredException {
		int totalRestoredCategory = categoryRepository.restoreAllCategories();

		if (totalRestoredCategory > 0)
			return true;
		else
			throw new AlreadyRestoredException("Categories are already restored!");
	}

	@Override
	public CategoryPageResponse getDeletedCategories(Integer pageNo, Integer pageSize) {
		Page<Category> categoryList = categoryRepository.findByIsDeleted(true, PageRequest.of(pageNo, pageSize));
		Page<CategoryDTO> categoryDTOList = categoryList.map(c -> modelMapper.map(c, CategoryDTO.class));
		return getCategoryPageResponse(categoryDTOList);
	}

	@Override
	public CategoryPageResponse getAllActiveCategories(Integer pageNo, Integer pageSize) {
		Page<Category> activeCategoryList = categoryRepository.findByIsDeleted(false,
				PageRequest.of(pageNo, pageSize, Sort.by("id").descending()));
		Page<CategoryDTO> activeCategoryDTOList = activeCategoryList.map(c -> modelMapper.map(c, CategoryDTO.class));

		return getCategoryPageResponse(activeCategoryDTOList);
	}

	@Override
	public Boolean deleteAllCategories() {
		int deleteCategories = categoryRepository.deleteCategories();
		System.out.println("Deleted Categories : " + deleteCategories);

		if (deleteCategories > 0)
			return true;

		return false;
	}

	@Override
	public Boolean deleteMultipleCategories(List<Integer> categoryIds) {
		int totalDeletedCategories = categoryRepository.deleteCategoriesByIds(categoryIds);
		System.out.println("Total Categories Deleted : " + totalDeletedCategories);

		if (totalDeletedCategories > 0)
			return true;

		else
			return false;
	}

	@Override
	public CategoryPageResponse findCategoryByName(String categoryName, Integer pageNo, Integer pageSize) {
		Page<Category> categoryList = categoryRepository.findByNameContainingIgnoreCaseAndIsDeletedFalse(categoryName, PageRequest.of(pageNo, pageSize));
		Page<CategoryDTO> categoryDTOList = categoryList.map(c -> modelMapper.map(c, CategoryDTO.class));
		return getCategoryPageResponse(categoryDTOList);
	}

	@Override
	public Boolean updateCategory(CategoryDTO categoryDTO) throws ValidationException {
		
		if(categoryDTO.getId() == null) {
			throw new ValidationException(Map.of("ID", "ID field is required!"));
		}
		
		Boolean saveCategory = saveCategory(categoryDTO);
		return saveCategory;
	}

	// Create a method:
	public CategoryPageResponse getCategoryPageResponse(Page<CategoryDTO> categoryDTOs) {
		PaginationInfo paginationInfo = PaginationInfo.builder().currentPage(categoryDTOs.getNumber() + 1)
				.totalItems(categoryDTOs.getTotalElements()).totalPages(categoryDTOs.getTotalPages())
				.lastPage(categoryDTOs.isLast()).build();
		return CategoryPageResponse.builder().categories(categoryDTOs.getContent()).paginationInfo(paginationInfo)
				.build();
	}
}
