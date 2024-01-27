package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.dtos.AddItemToCartRequestDto;
import com.lcwd.electronic.store.dtos.CartDto;
import com.lcwd.electronic.store.entities.Cart;
import com.lcwd.electronic.store.entities.CartItems;
import com.lcwd.electronic.store.entities.Product;
import com.lcwd.electronic.store.entities.User;
import com.lcwd.electronic.store.exception.ResourceNotFoundException;
import com.lcwd.electronic.store.repositiories.CartItemRepository;
import com.lcwd.electronic.store.repositiories.CartRepository;
import com.lcwd.electronic.store.repositiories.ProductRepository;
import com.lcwd.electronic.store.repositiories.UserRepository;
import com.lcwd.electronic.store.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequestDto requestDto) {
        String productId = requestDto.getProductId();
        int quantity = requestDto.getQuantity();
        //fetch the product from db
       Product product = productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("product id not found !!"));
        //fetch the user from db
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User is not find in database"));
        Cart cart =null;
        try {
            cart=cartRepository.findByUser(user).get();
        }catch (NoSuchElementException e)
        {
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedT(new Date());

        }
        //Perform cart operation
        //if cart item allready present:then update
        AtomicReference<Boolean> updated = new AtomicReference<>(false);

        List<CartItems> items = cart.getItems();
        items = items.stream().map(item -> {
            if (item.getProduct().getProductId().equals(productId)) {
                //item allready present
                item.setQuantity(quantity);
                item.setTotalAmount(quantity*product.getDisCountedPrice());
                updated.set(true);
            }
            return item;
        }).collect(Collectors.toList());
        //cart.setItems(updatedItem);
        if(!updated.get()) {
            CartItems cartItems = CartItems.builder()
                    .quantity(quantity)
                    .totalAmount(quantity * product.getDisCountedPrice())
                    .cart(cart)
                    .product(product)
                    .build();
            cart.getItems().add(cartItems);
        }
        cart.setUser(user);
        Cart save = cartRepository.save(cart);
        return mapper.map(save,CartDto.class);
    }

    @Override
    public void removeItemFromCart(String userId, int cartItem) {
        Cart cart = cartRepository.findById(String.valueOf(cartItem)).orElseThrow(() -> new ResourceNotFoundException("Cart item not found in db !!"));
        cartRepository.delete(cart);
    }

    @Override
    public void clearCart(String userId) {
        //fetch the user from db
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User is not find in database"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("card of given user not found"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public CartDto getCartByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User is not find in database"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("card of given user not found"));

        return mapper.map(cart,CartDto.class);
    }
}
