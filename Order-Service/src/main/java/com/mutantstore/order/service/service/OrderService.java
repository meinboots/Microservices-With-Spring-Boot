package com.mutantstore.order.service.service;

import com.mutantstore.order.service.model.OrderRequest;

public interface OrderService {

    long placeOrder(OrderRequest orderRequest);
}
