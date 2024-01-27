package com.lcwd.electronic.store.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateOrderRequest {

    //PENDING, DISPATCH,DELIVERED
    @NotBlank(message = "cartId is required!!")
    private String cartId;
    @NotBlank(message = "userId is required!!")
    private String userId;
    private String orderStatus="PENDING";
    //NOT PAID, PAID
    private String paymentStatus="NOTPAID";
    @NotBlank(message = "address is required!!")
    private String billingAddress;
    @NotBlank(message = "Phone number is mandatory !!")
    private String billingPhone;
    @NotBlank(message = "BilingName is mandatory!!")
    private String billingName;

}
