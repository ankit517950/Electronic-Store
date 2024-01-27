package com.lcwd.electronic.store.repositiories;

import com.lcwd.electronic.store.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems,Integer> {
}
