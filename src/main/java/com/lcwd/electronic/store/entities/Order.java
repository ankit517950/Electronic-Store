package com.lcwd.electronic.store.entities;

import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.internal.bytebuddy.matcher.FilterableList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="orders")
public class Order {
    @Id
    private String orderId;
    //PENDING, DISPATCH,DELIVERED
    private String orderStatus;
    //NOT PAID, PAID
    private String paymentStatus;
    private int orderAmount;
    @Column(length = 100)
    private String billingAddress;
    private String billingPhone;
    private String billingName;
    private Date oderedDate;
    private Date deliveredDate;
    //User
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id")
    private User user;
    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


}
