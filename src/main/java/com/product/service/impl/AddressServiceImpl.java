package com.product.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.product.dto.AddressDTO;
import com.product.exception.ResourceNotFoundException;
import com.product.model.Address;
import com.product.model.User;
import com.product.repository.AddressRepository;
import com.product.repository.UserRepository;
import com.product.service.AddressService;
import com.product.util.CommonMethods;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommonMethods commonMethods;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public AddressDTO addAddress(AddressDTO addressDTO) throws ResourceNotFoundException {
//		Get Logged in user:
		User loggedInUser = commonMethods.getLoggedInUser();
		
		if(addressDTO.getId() != null) {
			Long totalAddressFound = loggedInUser.getAddresses().stream().filter(address -> address.getId().equals(addressDTO.getId())).count();
			if(totalAddressFound == 0)
				throw new ResourceNotFoundException("Address not found with id : ["+ addressDTO.getId() +"]");
		}
		
		Address address = modelMapper.map(addressDTO, Address.class);
		address.setUser(loggedInUser);
		Address savedAddress = addressRepository.save(address);
		return modelMapper.map(savedAddress, AddressDTO.class);
	}

	@Override
	public List<AddressDTO> getAllAddress() throws ResourceNotFoundException {
//		Get Logged in user:
		User loggedInUser = commonMethods.getLoggedInUser();
		List<Address> addresses = loggedInUser.getAddresses();
		List<AddressDTO> addressDTOs = addresses.stream().map(address -> modelMapper.map(address, AddressDTO.class)).toList();
		return addressDTOs;
	}

	@Override
	public AddressDTO getAddress(Long addressId) throws ResourceNotFoundException {
		User loggedInUser = commonMethods.getLoggedInUser();
		Address foundAddress = loggedInUser.getAddresses().stream().filter(address -> address.getId().equals(addressId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Address not found with id : ["+ addressId +"]"));
		
		return modelMapper.map(foundAddress, AddressDTO.class);
	}

	@Override
	public Boolean deleteAddress(Long addressId) throws ResourceNotFoundException {
		User loggedInUser = commonMethods.getLoggedInUser();
		Address foundAddress = loggedInUser.getAddresses().stream().filter(address -> address.getId().equals(addressId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Address not found with id : ["+ addressId +"]"));
		loggedInUser.getAddresses().remove(foundAddress);
		addressRepository.delete(foundAddress);
		userRepository.save(loggedInUser);
		return true;
	}

}
