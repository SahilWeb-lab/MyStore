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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/api/v1/auth")
@Tag(name = "User Authentication", description = "All user related authentication APIs.")
public interface UserEndpoint {

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "User Registered Successfully"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Bad Request")
	})
	@Operation(summary = "User Register Endpoint", tags = "Auth", description = "This API endpoint is used for registration.")
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO, HttpServletRequest request) throws ValidationException, UnsupportedEncodingException, MessagingException;
	
	@Operation(summary = "User Login Endpoint", description = "This API endpoint is used for login.", tags = "Auth")
	@PostMapping("/login")
	public ResponseEntity<?>  loginUser(@RequestBody LoginRequest loginRequest) throws ValidationException, Exception;
	
	@Operation(summary = "Verify Email Enpoint", description = "This API endpoint is used for verifying email address. (Email verification link sent to your email address which you used for registration.)", tags = "Auth")
	@GetMapping("/verify")
	public ResponseEntity<?> verifyAccount(@RequestParam String vcode) throws ResourceNotFoundException;

	@Operation(summary = "Generate Password Reset Link Endpoint", tags = "Auth", description = "This API endpoint is used to generate password reset link which is sent to given email for password reset.")
	@GetMapping("/reset-password")
	public ResponseEntity<?> passwordReset(@RequestParam String email, HttpServletRequest request) throws UnsupportedEncodingException, ResourceNotFoundException, MessagingException;
	
	@Operation(summary = "Change Password Endpoint", tags = "Auth", description = "This API endpoint is used to change the password.")
	@PostMapping("/reset-password/{passwordResetToken}")
	public ResponseEntity<?> verifyPasswordResetToken(@PathVariable String passwordResetToken, @RequestBody PasswordChangeRequest changeRequest) throws ResourceNotFoundException, Exception;

	
}
