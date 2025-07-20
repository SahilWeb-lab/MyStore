package com.product.endpoints;

import java.io.UnsupportedEncodingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.dto.LoginRequest;
import com.product.dto.PasswordChangeRequest;
import com.product.dto.UserDTO;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/api/v1/auth")
public interface UserEndpoint {

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO, HttpServletRequest request) throws ValidationException, UnsupportedEncodingException, MessagingException;
	
	@PostMapping("/login")
	public String loginUser(@RequestBody LoginRequest loginRequest) throws ValidationException, Exception;
	
	@GetMapping("/verify")
	public ResponseEntity<?> verifyAccount(@RequestParam String vcode) throws ResourceNotFoundException;

	@GetMapping("/reset-password")
	public ResponseEntity<?> passwordReset(@RequestParam String email, HttpServletRequest request) throws UnsupportedEncodingException, ResourceNotFoundException, MessagingException;
	
	@GetMapping("/reset-password/{passwordResetToken}")
	public ResponseEntity<?> verifyPasswordResetToken(@PathVariable String passwordResetToken, @RequestBody PasswordChangeRequest changeRequest) throws ResourceNotFoundException, Exception;

}
