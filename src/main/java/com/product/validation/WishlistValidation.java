package com.product.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.product.exception.AlreadyExistsException;
import com.product.exception.ResourceNotFoundException;
import com.product.model.Product;
import com.product.model.Wishlist;
import com.product.repository.ProductRepository;
import com.product.repository.WishlistRepository;
import com.product.util.CommonMethods;

@Component
public class WishlistValidation {

	@Autowired
	private CommonMethods commonMethods;
	
	@Autowired
	private ProductRepository productRepository;
	
	public void validate(Integer productId) throws ResourceNotFoundException {
		Wishlist loggedInUserWishlist = commonMethods.getLoggedInUserWishlist();
		
//		Check is product already exists in the logged in user wishlist or not:
		if(!ObjectUtils.isEmpty(loggedInUserWishlist)) {
			long count = loggedInUserWishlist.getProducts().stream().filter(item -> item.getId().equals(productId)).count();
			
			if(count >= 1)
				throw new AlreadyExistsException("Product already added to wishlist!");
		}
	}
	
}
