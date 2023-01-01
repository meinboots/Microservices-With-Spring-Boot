package com.mutantstore.order.service.service;

import com.mutantstore.order.service.entity.Order;
import com.mutantstore.order.service.external.client.PaymentService;
import com.mutantstore.order.service.external.client.ProductService;
import com.mutantstore.order.service.external.request.PaymentRequest;
import com.mutantstore.order.service.model.OrderRequest;
import com.mutantstore.order.service.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public long placeOrder(OrderRequest orderRequest) {

        //Order Entity-> Save the data with status order created
        //Product Service-> Block products(Reduce the quantity)
        //Payment Service-> If payment-> success->COMPLETE or CANCEL the Order

        log.info("Placing order request {}", orderRequest);

        //Product Service-> Block products(Reduce the quantity)
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

        log.info("Creating order with status : CREATED ");

        //Creating order entity object
        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        order = orderRepository.save(order);

        //Do payment
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

        String orderStatus = null;

        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done successfully changing Order Status to : PLACED");
            orderStatus = "PLACED";
        }catch (Exception e){
            log.info("Payment done successfully changing Order Status to : FAILED");
            orderStatus = "FAILED";
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        log.info("Order placed successfully with Order Id: {}", order.getId());

        return order.getId();
    }
}
