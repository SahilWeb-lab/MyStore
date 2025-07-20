package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.Brand;

public interface BrandRespository extends JpaRepository<Brand, Integer> {

}
