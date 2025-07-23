package com.product.service;

import java.io.UnsupportedEncodingException;

import com.product.dto.PasswordChangeRequest;
import com.product.dto.UserDTO;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.ValidationException;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

//	Create a method to create account:
	public Boolean createUser(UserDTO userDTO, HttpServletRequest httpServletRequest) throws ValidationException, UnsupportedEncodingException, MessagingException;
	
//	Create a method to verify account:
	public Boolean verifyAccount(String vcode) throws ResourceNotFoundException;
	
//	Create a method to generate reset password link:
	public void resetPassword(String email, HttpServletRequest request) throws ResourceNotFoundException, UnsupportedEncodingException, MessagingException;

//	Verify Password reset token and change password:
	public Boolean verifyAndResetPassword(String passwordResetToken, PasswordChangeRequest passwordChangeRequest) throws ResourceNotFoundException, Exception;

//	Create a method to get user profile:
	public UserDTO getUserProfile() throws ResourceNotFoundException;
	
}
