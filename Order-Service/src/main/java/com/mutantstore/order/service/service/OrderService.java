package com.mutantstore.order.service.service;

import com.mutantstore.order.service.model.OrderRequest;
import com.mutantstore.order.service.model.OrderResponse;

public interface OrderService {

    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(Long orderId);
}
