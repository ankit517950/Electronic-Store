package com.lcwd.electronic.store.dtos;

import com.lcwd.electronic.store.entities.OrderItem;
import com.lcwd.electronic.store.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDto {
    private String orderId;
    //PENDING, DISPATCH,DELIVERED
    private String orderStatus="PENDING";
    //NOT PAID, PAID
    private String paymentStatus="NOTPAID";
    private int orderAmount;

    private String billingAddress;
    private String billingPhone;
    private String billingName;
    private Date oderedDate=new Date();
    private Date deliveredDate;
    //User

    private UserDto user;
    private List<OrderItemDto> orderItemsDto = new ArrayList<>();

}
