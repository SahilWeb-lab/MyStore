package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
