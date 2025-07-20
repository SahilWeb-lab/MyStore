package com.product.service.impl;

import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.product.dto.CartDTO;
import com.product.dto.CartItemDTO;
import com.product.exception.ResourceNotFoundException;
import com.product.model.Cart;
import com.product.model.CartItem;
import com.product.model.Product;
import com.product.model.User;
import com.product.repository.CartItemRepository;
import com.product.repository.CartRepository;
import com.product.repository.ProductRepository;
import com.product.repository.UserRepository;
import com.product.service.CartService;
import com.product.util.CommonMethods;
import com.product.validation.CartValidation;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CommonMethods commonMethods;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartValidation cartValidation;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CartItemRepository cartItemRepository;

//	Create a method to add product to cart:
	@Override
	public Boolean addToCart(CartDTO cartDTO) throws ResourceNotFoundException {
//		Cart Create Method:
		createCart();
		
//		Check product already exists in the cart or not:
		cartValidation.validate(cartDTO);

//		Get Logged in user cart:
		Cart userCart = commonMethods.getLoggedInUserCart();
		
//		Set Price of total cart items to 0, if cart doesn't have any product:
		if(userCart.getCartItems().size() == 0) 
			userCart.setTotalAmount(0.0);
		
		Product product = productRepository.findById(cartDTO.getProductId()).get();
		CartItem cartItem = CartItem.builder()
		.cart(userCart)
		.product(product)
		.quantity(cartDTO.getQuantity())
		.totalPrice(cartDTO.getQuantity() * product.getDiscountedPrice().doubleValue())
		.build();
		
		userCart.getCartItems().add(cartItem);
		
//		Calculate Total Product Cart Amount:
		userCart.setTotalAmount(userCart.getTotalAmount() + cartItem.getTotalPrice());
		
		Cart savedCart = cartRepository.save(userCart);
		
		return (ObjectUtils.isEmpty(savedCart)) ? false : true;
	}

//	Create a method to get all cart items or products:
	@Override
	public List<CartItemDTO> getAllCartItem() throws ResourceNotFoundException {
		Cart loggedInUserCart = commonMethods.getLoggedInUserCart();
		List<CartItem> cartItems = loggedInUserCart.getCartItems();
		List<CartItemDTO> list = cartItems.stream().map(item -> modelMapper.map(item, CartItemDTO.class))
				.sorted(Comparator.comparing(CartItemDTO::getId).reversed())
				.toList();
		return list;
	}

//	Create a method to remove product from cart:
	@Override
	public Boolean removeCartItem(Long cartItemId) throws ResourceNotFoundException {
//		Get loggedIn user cart:
		Cart loggedInUserCart = commonMethods.getLoggedInUserCart();
		
		CartItem cartItem = loggedInUserCart.getCartItems().stream().filter(item -> item.getId().equals(cartItemId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Cart item not found!"));
		loggedInUserCart.getCartItems().remove(cartItem);
		cartItemRepository.delete(cartItem);
		
//		Remove delete cart item amount from total amount of cart products:
		loggedInUserCart.setTotalAmount(loggedInUserCart.getTotalAmount() - cartItem.getTotalPrice());
		
		Cart savedCart = cartRepository.save(loggedInUserCart);
		return (ObjectUtils.isEmpty(savedCart)) ? false : true;
	}
	
//	Create a method to update quantity:
	public Boolean updateQuantity(Long cartItemId, Integer quantity) throws ResourceNotFoundException {
		Cart loggedInUserCart = commonMethods.getLoggedInUserCart();
		CartItem cartItem = loggedInUserCart.getCartItems().stream().filter(item -> item.getId().equals(cartItemId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Cart item not found!"));
		
//		Update total cart total amount price:
		Integer currentQuantity = quantity;
		Integer pastQuantity = cartItem.getQuantity();
		Integer actualQuantity = currentQuantity - pastQuantity;
		
		loggedInUserCart.setTotalAmount(loggedInUserCart.getTotalAmount() + actualQuantity * cartItem.getProduct().getDiscountedPrice());
		
		cartItem.setQuantity(quantity);
		cartItem.setTotalPrice(cartItem.getQuantity() * cartItem.getProduct().getDiscountedPrice().doubleValue());
		
		Cart saveCart = cartRepository.save(loggedInUserCart);
		
		return (ObjectUtils.isEmpty(saveCart)) ? false : true;
	}
	
//	Create a method to create cart:
	public void createCart() throws ResourceNotFoundException {
//		Get Logged In user Info:
		User user = commonMethods.getLoggedInUser();
		
//	Get or Create Cart for logged in user:
		Cart cart = cartRepository.findByUser(user);

		if (ObjectUtils.isEmpty(cart)) {
			Cart newCart = new Cart();
			newCart.setUser(user);
			Cart savedCart = cartRepository.save(newCart);
		}
	}
}
