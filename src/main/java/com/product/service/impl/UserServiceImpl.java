package com.product.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.product.dto.PasswordChangeRequest;
import com.product.dto.UserDTO;
import com.product.exception.ResourceNotFoundException;
import com.product.exception.UserVerificationException;
import com.product.exception.ValidationException;
import com.product.model.AccountStatus;
import com.product.model.Cart;
import com.product.model.Role;
import com.product.model.User;
import com.product.repository.AccountStatusRepository;
import com.product.repository.RoleRepository;
import com.product.repository.UserRepository;
import com.product.service.EmailService;
import com.product.service.UserService;
import com.product.validation.UserValidation;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserValidation userValidation;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AccountStatusRepository accountStatusRepository;
	
	@Override
	public Boolean createUser(UserDTO userDTO, HttpServletRequest request) throws ValidationException, UnsupportedEncodingException, MessagingException {
		String url = request.getRequestURL().toString();
		String actualURL = url.replace("register", "verify");

		User user = modelMapper.map(userDTO, User.class);
		Role userRole = roleRepository.findById(2).get();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(userRole);
		
//		Create Account Status:
		AccountStatus accountStatus = new AccountStatus();
		accountStatus.setAccountStatus(com.product.enums.AccountStatus.INACTIVE);
		String vcode = UUID.randomUUID().toString();
		accountStatus.setAccountVerificationToken(vcode);
		user.setAccountStatus(accountStatus);
		
		User savedUser = userRepository.save(user);
		
		if(ObjectUtils.isEmpty(savedUser))
			return false;
			
		
//		Send Account Verification Link:
		emailService.sendVerificationEmail(userDTO.getEmail(), user.getName(), actualURL + "?vcode=" + vcode);
		return true;
	}

	@Override
	public Boolean verifyAccount(String vcode) throws ResourceNotFoundException {
		Optional<AccountStatus> accountStatus = accountStatusRepository.findByAccountVerificationToken(vcode);
		
		if(accountStatus.isPresent()) {
			AccountStatus status = accountStatus.get();
			status.setAccountStatus(com.product.enums.AccountStatus.ACTIVE);
			status.setAccountVerificationToken(null);
			AccountStatus saveStatus = accountStatusRepository.save(status);
			
			if(ObjectUtils.isEmpty(saveStatus))
				return false;
			
			return true;
		}
		
		throw new ResourceNotFoundException("Invalid Token!!");
	}

	@Override
	public void resetPassword(String email, HttpServletRequest request) throws ResourceNotFoundException, UnsupportedEncodingException, MessagingException {
		User user = userRepository.findByEmail(email);
		
		String url = request.getRequestURL().toString() + "/";
		System.out.println(url);
		
		if(ObjectUtils.isEmpty(user))
			throw new ResourceNotFoundException("Invalid Email Address!");
		
//		Generate Token:
		String passwordResetToken = UUID.randomUUID().toString();
		user.getAccountStatus().setPasswordResetToken(passwordResetToken);
		userRepository.save(user);
		
		emailService.sendPasswordResetEmail(email, user.getName(), url + passwordResetToken);
	}

	@Override
	public Boolean verifyAndResetPassword(String passwordResetToken, PasswordChangeRequest passwordChangeRequest) throws Exception {
		AccountStatus accountStatus = accountStatusRepository.findByPasswordResetToken(passwordResetToken);
		
		if(ObjectUtils.isEmpty(accountStatus))
			throw new ResourceNotFoundException("Invalid Password Reset Token!");
		
		User user = userRepository.findByAccountStatus(accountStatus);
		
		if(ObjectUtils.isEmpty(user))
			throw new ResourceNotFoundException("Invalid Access!");
		
		userValidation.passwordValidation(passwordChangeRequest);
		
		user.setPassword(passwordEncoder.encode(passwordChangeRequest.getPassword()));
		user.getAccountStatus().setPasswordResetToken(null);
		userRepository.save(user);
		
		return ObjectUtils.isEmpty(user) ? false : true;
	}

	
	
}
