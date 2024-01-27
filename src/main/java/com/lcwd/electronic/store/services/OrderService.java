package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.CreateOrderRequest;
import com.lcwd.electronic.store.dtos.OrderDto;
import com.lcwd.electronic.store.dtos.PageableResponse;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    //Create Order
     OrderDto createOrder(CreateOrderRequest orderDto);
    //Remove Order
     void removeOrder(String orderId);
    //Get Order of Users
     List<OrderDto> getOrderOfUser(String userId);
    //get Orders
     PageableResponse<OrderDto> getOrders(int pageNumber,int pageSize,String sortBy,String sortDir);
    //
}
