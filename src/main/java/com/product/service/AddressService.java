package com.product.service;

import java.util.List;

import com.product.dto.AddressDTO;
import com.product.exception.ResourceNotFoundException;

public interface AddressService {

//	Create a method to add address:
	public AddressDTO addAddress(AddressDTO addressDTO) throws ResourceNotFoundException ;
	
//	Create a method to get all address:
	public List<AddressDTO> getAllAddress() throws ResourceNotFoundException;
	
//	Create a method to get single address;
	public AddressDTO getAddress(Long addressId) throws ResourceNotFoundException;
	
//	Create a method to delete address:
	public Boolean deleteAddress(Long addressId) throws ResourceNotFoundException;
	
}
