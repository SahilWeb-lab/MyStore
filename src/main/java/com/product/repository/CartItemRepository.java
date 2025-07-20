package com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.model.Cart;
import com.product.model.CartItem;
import com.product.model.Product;

import jakarta.transaction.Transactional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

//	Create a method to update cart item quantity:
    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.quantity = :quantity WHERE c.id = :cartItemId")
    int updateQuantityById(@Param("cartItemId") Long cartItemId, @Param("quantity") int quantity);
    
//    Create a method to check cartItem of logged in user already exists:
    Boolean existsByCartAndProduct(Cart cart, Product product);
    
//    Create a method to get cart items bt cart:
    List<CartItem> findByCart(Cart cart);
    
//    Create a method to delete cart item by user:
    public Integer deleteByCart(Cart cart);
}
