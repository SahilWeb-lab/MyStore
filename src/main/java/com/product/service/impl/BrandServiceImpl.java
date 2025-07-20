
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

import com.product.dto.BrandDTO;
import com.product.dto.CategoryDTO;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;
import com.product.model.Brand;
import com.product.page.response.BrandPageResponse;
import com.product.page.response.CategoryPageResponse;
import com.product.page.response.PaginationInfo;
import com.product.repository.BrandRepository;
import com.product.service.BrandService;
import com.product.validation.BrandValidation;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BrandValidation brandValidation;

	@Override
	public Boolean saveBrand(BrandDTO brandDTO) throws ValidationException {
		
//		Validate id is given or not:
		if(brandDTO.getId() != null)
			throw new ValidationException(Map.of("Id", "Id is not known field!"));
		
		// Validate Brand Details:
		brandValidation.validate(brandDTO);
		
		Brand brand = modelMapper.map(brandDTO, Brand.class);
		Brand saveBrand = brandRepository.save(brand);

		if (ObjectUtils.isEmpty(saveBrand))
			return false;

		return true;
	}

	@Override
	public BrandPageResponse getAllBrands(Integer pageNo, Integer pageSize) {
		Page<Brand> brandPage = brandRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by("id").descending()));
		Page<BrandDTO> brandPageDTO = brandPage.map(b -> modelMapper.map(b, BrandDTO.class));
		return getBrandPageResponse(brandPageDTO);
	}

	@Override
	public BrandDTO getSingleBrand(Integer brandId) throws ResourceNotFoundException {
		Brand brand = brandRepository.findByIdAndIsDeletedFalse(brandId);
		
		if(!ObjectUtils.isEmpty(brand))
			return modelMapper.map(brand, BrandDTO.class);
		
		throw new ResourceNotFoundException("Brand with id ["+brandId+"] not found!");
	}

	@Override
	public Boolean deleteSingleBrand(Integer brandId) throws ResourceNotFoundException {
		brandRepository.findById(brandId)
				.orElseThrow(() -> new ResourceNotFoundException("Brand with id [" + brandId + "] not found!"));
		brandRepository.deleteSingleBrand(brandId);
		return true;
	}

	@Override
	public BrandPageResponse getAllActiveBrands(Integer pageNo, Integer pageSize) {
		Page<Brand> brandPage = brandRepository.findByIsDeletedFalse(PageRequest.of(pageNo, pageSize, Sort.by("id").descending()));
		Page<BrandDTO> brandPageDTO = brandPage.map(b -> modelMapper.map(b, BrandDTO.class));
		return getBrandPageResponse(brandPageDTO);
	}

	@Override
	public BrandPageResponse getAllDeletedBrands(Integer pageNo, Integer pageSize) {
		Page<Brand> brandPage = brandRepository.findByIsDeletedTrue(PageRequest.of(pageNo, pageSize));
		Page<BrandDTO> brandPageDTO = brandPage.map(b -> modelMapper.map(b, BrandDTO.class));
		return getBrandPageResponse(brandPageDTO);
	}
	

	@Override
	public BrandPageResponse searchBrandByName(String brandName, Integer pageNo, Integer pageSize) {
		Page<Brand> brandPage = brandRepository.findByNameContainingIgnoreCaseAndIsDeletedFalse(brandName, PageRequest.of(pageNo, pageSize));
		Page<BrandDTO> brandPageDTO = brandPage.map(b -> modelMapper.map(b, BrandDTO.class));
		return getBrandPageResponse(brandPageDTO);
	}
	
	
	@Override
	public Boolean saveMultipleBrand(List<BrandDTO> brandDTOs) throws ValidationException {
		
//		Validate Brand:
		for(BrandDTO brandDTO : brandDTOs)
			brandValidation.validate(brandDTO);
		
		List<Brand> brandList = brandDTOs.stream().map(b -> modelMapper.map(b, Brand.class)).toList();
		List<Brand> saveAll = brandRepository.saveAll(brandList);
		
		if(CollectionUtils.isEmpty(saveAll))
			return false;
		
		return true;
	}

	@Override
	public Boolean restoreSingleBrand(Integer brandId) {
		Integer totalRestoredBrand = brandRepository.restoreSingleBrand(brandId);
		
		if(totalRestoredBrand > 0)
			return true;
		
		return false;
	}
	
	@Override
	public Boolean restoreMultipleBrand(List<Integer> brandIds) {
		Integer totalRestoredBrand = brandRepository.restoreMultipleBrand(brandIds);
		
		if(totalRestoredBrand > 0)
			return true;
		
		return false;
	}

	@Override
	public Boolean deleteMultipleBrand(List<Integer> brandIds) {
		Integer totalDeletedBrand = brandRepository.deleteMultipleBrand(brandIds);
		
		if(totalDeletedBrand > 0)
			return true;
		
		return false;
	}
	
	@Override
	public Boolean updateBrand(BrandDTO brandDTO) throws ValidationException {
		
//		Validate id is given or not:
		if(brandDTO.getId() == null)
			throw new ValidationException(Map.of("Id", "Id is required for updation!"));
		
//		Validate Brand:
		brandValidation.validate(brandDTO);
		
		Boolean updateBrand = saveBrand(brandDTO);
		
		if(updateBrand)
			return true;
		
		return false;
	}

	// Create a method for page response:
	public BrandPageResponse getBrandPageResponse(Page<BrandDTO> brandDTOs) {
		PaginationInfo paginationInfo = PaginationInfo.builder().currentPage(brandDTOs.getNumber() + 1)
				.totalItems(brandDTOs.getTotalElements()).totalPages(brandDTOs.getTotalPages())
				.lastPage(brandDTOs.isLast()).build();
		return BrandPageResponse.builder().brandDTOs(brandDTOs.getContent()).paginationInfo(paginationInfo).build();
	}
	
	

}
