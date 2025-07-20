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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.AddressDTO;
import com.product.endpoints.AddressEndpoint;
import com.product.exception.ResourceNotFoundException;
import com.product.service.AddressService;
import com.product.util.CommonUtils;

@RestController
public class AddressController implements AddressEndpoint {
	
	@Autowired
	private AddressService addressService;

	public ResponseEntity<?> addAddress(@RequestBody AddressDTO addressDTO) throws ResourceNotFoundException {
		AddressDTO address = addressService.addAddress(addressDTO);
		
		if(ObjectUtils.isEmpty(address))
			return CommonUtils.createBuildResponseMessage("Failed to add address", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return CommonUtils.createBuildResponseMessage("Address added successfully!", HttpStatus.CREATED);
	}
	

	public ResponseEntity<?> getAllAddresses() throws ResourceNotFoundException {
		List<AddressDTO> allAddress = addressService.getAllAddress();
		
		if(CollectionUtils.isEmpty(allAddress))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(allAddress, HttpStatus.OK);
	}
	

	public ResponseEntity<?> getSingleAddress(@PathVariable Long addressId) throws ResourceNotFoundException {
		AddressDTO address = addressService.getAddress(addressId);
		
		if(ObjectUtils.isEmpty(address))
			return CommonUtils.createErrorResponseMessage("Address Not Found!", HttpStatus.NOT_FOUND);
		
		return CommonUtils.createBuildResponse(address, HttpStatus.OK);
	}

	public ResponseEntity<?> deleteAddress(@PathVariable Long addressId) throws ResourceNotFoundException {
		Boolean deleteStatus = addressService.deleteAddress(addressId);
		
		if(deleteStatus)
			return CommonUtils.createBuildResponseMessage("Address Deleted Successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to delete address!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
