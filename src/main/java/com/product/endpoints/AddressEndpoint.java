package com.product.endpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.product.dto.AddressDTO;
import com.product.exception.ResourceNotFoundException;

@RequestMapping("/api/v1/address")
public interface AddressEndpoint {

//	Create a handler to add address:
	@PostMapping("/")
	public ResponseEntity<?> addAddress(@RequestBody AddressDTO addressDTO) throws ResourceNotFoundException;
	
//	Create a handler to get all the addresses:
	@GetMapping("/")
	public ResponseEntity<?> getAllAddresses() throws ResourceNotFoundException;
	
//	Create a handler to get single address:
	@GetMapping("/{addressId}")
	public ResponseEntity<?> getSingleAddress(@PathVariable Long addressId) throws ResourceNotFoundException;
	
//	Create a handler to delete the address:
	@DeleteMapping("/{addressId}")
	public ResponseEntity<?> deleteAddress(@PathVariable Long addressId) throws ResourceNotFoundException;
	
}
