package com.lcwd.electronic.store.dtos;

import com.lcwd.electronic.store.entities.CartItems;
import com.lcwd.electronic.store.entities.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
    private String cartId;
    private Date createdT;

    private UserDto user;
    private List<CartItemDto> items = new ArrayList<>();

}
