package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.AddItemToCartRequestDto;
import com.lcwd.electronic.store.dtos.CartDto;
import org.hibernate.cfg.SchemaToolingSettings;

public interface CartService {
    //add items to cart
    //case1:cart for user is not available: we will create the cart and than add item
    //case2:cart available add the items to cart

    CartDto addItemToCart(String userId, AddItemToCartRequestDto requestDto);
    //remove item from cart
    void removeItemFromCart(String userId,int cartItem);

    //remove all items from cart
    void clearCart(String userId);
    CartDto getCartByUser(String userId);
}
