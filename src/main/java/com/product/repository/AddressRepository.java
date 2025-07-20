package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
