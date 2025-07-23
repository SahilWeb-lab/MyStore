package com.product.endpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.product.exception.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Profile APIs", description = "Profile related APIs.")
@RequestMapping("/api/v1/profile")
public interface ProfileEndpoint {

	@Operation(summary = "User Profile", description = "This API is used to get user profile. This API is only accessible to logged in user.")
	@GetMapping("/")
	public ResponseEntity<?> getProfile() throws ResourceNotFoundException;
	
}
