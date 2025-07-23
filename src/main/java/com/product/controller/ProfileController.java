package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.UserDTO;
import com.product.endpoints.ProfileEndpoint;
import com.product.exception.ResourceNotFoundException;
import com.product.service.UserService;
import com.product.util.CommonUtils;

@RestController
public class ProfileController implements ProfileEndpoint {
	
	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<?> getProfile() throws ResourceNotFoundException {
		UserDTO userProfile = userService.getUserProfile();
		return CommonUtils.createBuildResponse(userProfile, HttpStatus.OK);
	}
	
}
