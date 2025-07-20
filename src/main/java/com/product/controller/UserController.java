package com.product.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.LoginRequest;
import com.product.dto.PasswordChangeRequest;
import com.product.dto.UserDTO;
import com.product.endpoints.UserEndpoint;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;
import com.product.service.UserService;
import com.product.util.CommonUtils;
import com.product.util.JwtUtil;
import com.product.validation.UserValidation;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController implements UserEndpoint {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserValidation userValidation;
	
	@Autowired
	private UserService userService;
	
	@Override
	public String loginUser(LoginRequest loginRequest) throws Exception {
		
//		Check user is verified or not:
	userValidation.isVerifiedUser(loginRequest.getEmail());
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		
		if(!authenticate.isAuthenticated())
			throw new Exception("Invalid Credentials!!");
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticate.getName());
		String role = authenticate.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).get();
		String token = jwtUtil.generateToken(userDetails.getUsername(), role);
		return token;
	}
	
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO, HttpServletRequest request) throws ValidationException, UnsupportedEncodingException, MessagingException {
		userValidation.validate(userDTO);
		
		Boolean status = userService.createUser(userDTO, request);
		
		if(status) {			
			return CommonUtils.createBuildResponseMessage("Your account created successfully!", HttpStatus.CREATED);
		}
		
		return CommonUtils.createErrorResponseMessage("Failed to create account!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> verifyAccount(String vcode) throws ResourceNotFoundException {
		Boolean verifyAccount = userService.verifyAccount(vcode);
		
		if(verifyAccount)
			return CommonUtils.createBuildResponseMessage("Account Verified Successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to verify account!", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> passwordReset(String email, HttpServletRequest request) throws UnsupportedEncodingException, ResourceNotFoundException, MessagingException {
//		Check user is verified or not:
	userValidation.isVerifiedUser(email);
		userService.resetPassword(email, request);
		return CommonUtils.createBuildResponseMessage("Password reset email sent successfully to " + email, HttpStatus.OK);
	}

	
	@Override
	public ResponseEntity<?> verifyPasswordResetToken(String passwordResetToken, @RequestBody PasswordChangeRequest changeRequest) throws ResourceNotFoundException, Exception {
		Boolean verifyStatus = userService.verifyAndResetPassword(passwordResetToken, changeRequest);
		
		if(verifyStatus)
			return CommonUtils.createBuildResponseMessage("Password Changed Successfully!", HttpStatus.OK);
		
		return CommonUtils.createErrorResponseMessage("Failed to change password!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
