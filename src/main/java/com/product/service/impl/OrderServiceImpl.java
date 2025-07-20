package com.product.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.product.dto.CheckoutRequest;
import com.product.dto.OrderDTO;
import com.product.dto.OrderItemDTO;
import com.product.dto.OrderResponse;
import com.product.enums.OrderStatus;
import com.product.enums.PaymentStatus;
import com.product.exception.AlreadyExistsException;
import com.product.exception.ResourceNotFoundException;
import com.product.model.Address;
import com.product.model.Cart;
import com.product.model.Order;
import com.product.model.OrderItem;
import com.product.model.Product;
import com.product.model.User;
import com.product.repository.AddressRepository;
import com.product.repository.CartItemRepository;
import com.product.repository.CartRepository;
import com.product.repository.OrderRepository;
import com.product.repository.ProductRepository;
import com.product.repository.UserRepository;
import com.product.service.OrderService;
import com.product.util.CommonMethods;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommonMethods commonMethods;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public OrderDTO checkOut(CheckoutRequest checkoutRequest) throws Exception, ResourceNotFoundException {
//		Get Logged In user info:
		User loggedInUser = commonMethods.getLoggedInUser();
		
//		Get logged in user cart:
		Cart loggedInUserCart = commonMethods.getLoggedInUserCart();
		
//		Check logged in user has items in cart or not:
		if(loggedInUserCart.getCartItems().isEmpty())
			throw new Exception("Cart is Empty!");
		
//		Get Address:
		Address address = addressRepository.findById(checkoutRequest.getShippingAddressId()).orElseThrow(() -> new ResourceNotFoundException("Address Not Found!"));
		
		if(!address.getUser().equals(loggedInUser)) 
			throw new ResourceNotFoundException("Invalid Address!");
		
//		Create Order:
		Order order = new Order();
		order.setOrderDate(LocalDateTime.now());
		order.setOrderStatus(OrderStatus.PENDING);
		order.setPaymentMethod(checkoutRequest.getPaymentMethod());
		order.setPaymentStatus(PaymentStatus.UNPAID);
		order.setUser(loggedInUser);
		order.setTotalAmount(loggedInUserCart.getTotalAmount());
		order.setAddress(address);
		
//		Get all items of logged in user and convert it into cart items:
		List<OrderItem> orderItems = loggedInUserCart.getCartItems().stream().map(cartItem -> {
			OrderItem orderItem = OrderItem.builder()
			.productId(cartItem.getProduct().getId().longValue())
			.quantity(cartItem.getQuantity())
			.unitPrice(cartItem.getProduct().getDiscountedPrice().doubleValue())
			.totalPrice(cartItem.getTotalPrice())
			.order(order)
			.build();
			return orderItem;
		}).toList();
		
		order.setOrderItems(orderItems);
		
//		Save Order:
		Order savedOrder = orderRepository.save(order);
		
//		Update product available in stock:
		savedOrder.getOrderItems().stream().forEach(orderItem -> {
			Product product = productRepository.findById(orderItem.getProductId().intValue()).get();
			product.setStockStatus(product.getStockStatus() - orderItem.getQuantity());
			productRepository.save(product);
			System.out.println("Product Stock Status Changed!");
		});
		
		if(!ObjectUtils.isEmpty(savedOrder)) {			
			loggedInUserCart.getCartItems().clear();
//			Set total cart amount of logged in user to zero:
			loggedInUserCart.setTotalAmount(0.0);
			cartRepository.save(loggedInUserCart);
		}
		
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setEstimatedDelivery("3-5 business day.");
		orderDTO.setId(savedOrder.getId());
		orderDTO.setStatus(savedOrder.getOrderStatus());
		orderDTO.setTotalAmount(savedOrder.getTotalAmount());
		
		return orderDTO;
	}

	@Override
	public List<OrderResponse> getAllOrders() throws ResourceNotFoundException {
		User loggedInUser = commonMethods.getLoggedInUser();
		List<Order> orders = loggedInUser.getOrders();
		List<OrderResponse> orderList = orders.stream()
			.sorted(Comparator.comparing(Order::getOrderDate).reversed())
			.map(order -> {
			OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
			List<OrderItem> orderItems = order.getOrderItems();
			List<OrderItemDTO> orderItemDTOs = orderItems.stream().map(orderItem -> modelMapper.map(orderItem, OrderItemDTO.class)).toList();
			orderResponse.setOrderItem(orderItemDTOs);
			return orderResponse;
		}).toList();
		return orderList;
	}

	@Override
	public Boolean cancelOrder(Long orderId) throws ResourceNotFoundException {
		User loggedInUser = commonMethods.getLoggedInUser();
		Order foundOrder = loggedInUser.getOrders().stream().filter(order -> order.getId().equals(orderId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Order not found with id : ["+ orderId +"]"));
		
//		Check order status before chaning order status: for delivered user can't cancel:
		if(foundOrder.getOrderStatus().equals(OrderStatus.DELIVERED))
			throw new RuntimeException("You can't cancel order! Product is already delivered!");
		
//		Update product stock when user cancelled order:
		foundOrder.getOrderItems().stream().forEach(orderItem -> {
			Product product = productRepository.findById(orderItem.getProductId().intValue()).get();
			product.setStockStatus(product.getStockStatus() + orderItem.getQuantity());
			productRepository.save(product);
		});
		
		foundOrder.setOrderStatus(OrderStatus.CANCELLED);
		User savedUser = userRepository.save(loggedInUser);
		
		if(ObjectUtils.isEmpty(savedUser))
			return false;
		
		return true;
	}

//	For Admin:
	@Override
	public Boolean changeOrderStatus(Long orderId, OrderStatus orderStatus) throws ResourceNotFoundException {
		User loggedInUser = commonMethods.getLoggedInUser();
		Order foundOrder = loggedInUser.getOrders().stream().filter(order -> order.getId().equals(orderId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Order Not Found!"));
		
		if(foundOrder.getOrderStatus().equals(orderStatus))
			throw new AlreadyExistsException("Order is already : " + orderStatus.name());
		
		foundOrder.setOrderStatus(orderStatus);
		Order saveOrder = orderRepository.save(foundOrder);
		return (ObjectUtils.isEmpty(saveOrder)) ? false : true;
	}
	
	
	
}
