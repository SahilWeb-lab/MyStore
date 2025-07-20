package com.product.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.product.dto.WishlistDTO;
import com.product.exception.ResourceNotFoundException;
import com.product.model.Product;
import com.product.model.User;
import com.product.model.Wishlist;
import com.product.repository.ProductRepository;
import com.product.repository.WishlistRepository;
import com.product.service.WishlistService;
import com.product.util.CommonMethods;
import com.product.validation.WishlistValidation;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistRepository wishlistRepository;

	@Autowired
	private CommonMethods commonMethods;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private WishlistValidation wishlistValidation;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Boolean addToWishlist(Integer productId) throws ResourceNotFoundException {
//		Create Wishlist for user:
		createWishlist();

//		Validate:
		wishlistValidation.validate(productId);

		Wishlist userWishlist = commonMethods.getLoggedInUserWishlist();

//		Get Product by id:
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id : [" + productId + "]"));

		userWishlist.getProducts().add(product);
		Wishlist saveWishlist = wishlistRepository.save(userWishlist);
		return (ObjectUtils.isEmpty(saveWishlist)) ? false : true;
	}

//	Create a method to get wishlisted products:
	public List<WishlistDTO> getAllWishlist() throws ResourceNotFoundException {

//		Get logged in user wishlist:
		Wishlist userWishlist = commonMethods.getLoggedInUserWishlist();
		
		if(ObjectUtils.isEmpty(userWishlist))
			return null;
		
		List<Product> products = userWishlist.getProducts();
		List<WishlistDTO> wishlists = products.stream().map(product -> modelMapper.map(product, WishlistDTO.class))
				.toList();

		return wishlists;
	}

	@Override
	public Boolean removeWishlist(Integer productId) throws ResourceNotFoundException {
		Wishlist wishlist = commonMethods.getLoggedInUserWishlist();
		
		if(ObjectUtils.isEmpty(wishlist))
			return false;
		
		Product getProduct = wishlist.getProducts().stream().filter(product -> product.getId().equals(productId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Product not found with id : ["+ productId +"]"));
		boolean remove = wishlist.getProducts().remove(getProduct);
		
		if(remove) {
			wishlistRepository.save(wishlist);
			return true;
		}
		
		return false;
	}

	// Create a method to create wishlist:
	public void createWishlist() throws ResourceNotFoundException {
		Wishlist userWishlist = commonMethods.getLoggedInUserWishlist();
		User loggedInUser = commonMethods.getLoggedInUser();

		if (ObjectUtils.isEmpty(userWishlist)) {
//		Create wishlist:
			Wishlist newWishlist = new Wishlist();
			newWishlist.setUser(loggedInUser);
			wishlistRepository.save(newWishlist);
			System.out.println("Wishlsit Created!");
		}

	}

}
