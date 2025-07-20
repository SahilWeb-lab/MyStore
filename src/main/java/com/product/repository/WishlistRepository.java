package com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

//	Create a method to get wishlist by user id:
	@Query("SELECT w FROM Wishlist w WHERE w.user.id=:userId")
	public Wishlist findByUserId(@Param("userId") Long userId);
	
//	Create a method to get all wishlist:
	@Query("SELECT w FROM Wishlist w WHERE w.id=:id")
	public List<Wishlist> findAllByWishlistId(@Param("id") Long wishlistId);
	
}
