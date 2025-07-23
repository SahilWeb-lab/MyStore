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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Address APIs", description = "All address related APIs.")
@RequestMapping("/api/v1/address")
public interface AddressEndpoint {

//	Create a handler to add address:
	@Operation(summary = "Create/Save Address", description = "This api is used to create/save address. This api is only accessible to logged in user.")
	@PostMapping("/")
	public ResponseEntity<?> addAddress(@RequestBody AddressDTO addressDTO) throws ResourceNotFoundException;
	
//	Create a handler to get all the addresses:
	@Operation(summary = "Get all Address", description = "This api is used to get all address by current logged in user.")
	@GetMapping("/")
	public ResponseEntity<?> getAllAddresses() throws ResourceNotFoundException;
	
//	Create a handler to get single address:
	@Operation(summary = "Get Address By Id", description = "This api is used to get address by id and logged in user can get their address by id.")
	@GetMapping("/{addressId}")
	public ResponseEntity<?> getSingleAddress(@PathVariable Long addressId) throws ResourceNotFoundException;
	
//	Create a handler to delete the address:
	@Operation(summary = "Delete Address By Id", description = "This api is used to delete address by id and logged in user can delete their address by id.")
	@DeleteMapping("/{addressId}")
	public ResponseEntity<?> deleteAddress(@PathVariable Long addressId) throws ResourceNotFoundException;
	
}
