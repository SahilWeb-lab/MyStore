package com.product.dto;

import com.product.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AddressDTO {

	private Long id;
	
	private String fullname;

	private String phoneNumber;

	private String street;

	private String city;

	private String state;

	private String zipcode;

	private String country;

}
