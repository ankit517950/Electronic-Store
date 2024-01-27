package com.lcwd.electronic.store.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_item")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItem;
    @OneToOne
    @JoinColumn(name ="product_id")
    private Product product;
    private int quantity;
    private int totalAmount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="card_id")
    private Cart cart;
}
