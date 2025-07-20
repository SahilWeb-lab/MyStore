package com.product.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.product.dto.PasswordChangeRequest;
import com.product.dto.UserDTO;
import com.product.enums.AccountStatus;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.UserVerificationException;
import com.product.exception.ValidationException;
import com.product.model.User;
import com.product.repository.UserRepository;

@Component
public class UserValidation {
	
	@Autowired
	private UserRepository userRepository;

	public void validate(UserDTO userDTO) throws ValidationException {
		
		Map<String, Object> errors = new HashMap<>();
		
//		Validate name:
		String name = userDTO.getName();
		if(name == null || name.isBlank() || name.isEmpty())
			errors.put("Name", "Name is required.");
			
//		Validate Email:
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		String email = userDTO.getEmail();
		if(email == null || email.isBlank() || email.isEmpty())
			errors.put("Email", "Email is required.");
		else if(userRepository.existsByEmail(email))
			errors.put("Email", "Email already exists.");
		else if(!email.matches(emailRegex)) 
			errors.put("Email", "Invalid Email.");
		
//		Validate Password:
		String password = userDTO.getPassword();
		if(password == null || password.isEmpty() || password.isBlank())
			errors.put("Password", "Password is required.");
		else if(!(password.length() >= 6))
			errors.put("Password", "Password must be atleast 6 characters long.");
		
		if(!errors.isEmpty()) 
			throw new ValidationException(errors);
	}
	
	public void isVerifiedUser(String email) throws ResourceNotFoundException {
		User user = userRepository.findByEmail(email);
		
		if(ObjectUtils.isEmpty(user))
			throw new ResourceNotFoundException("Invalid Credentials!");
		
		String verificationToken = user.getAccountStatus().getAccountVerificationToken();
		
		if(verificationToken != null) 
			throw new UserVerificationException("Please verify your account first!");
	}
	
//	Method to validate password:
	public void passwordValidation(PasswordChangeRequest changeRequest) throws Exception {
		if(changeRequest.getPassword().length() < 6)
			throw new Exception("Password length must be atleast 6 character long!");
		
		if(!changeRequest.getPassword().equals(changeRequest.getConfirmPassword())) 
			throw new Exception("Password doesn't match!");
	}
}
