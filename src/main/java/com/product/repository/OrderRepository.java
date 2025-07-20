package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
