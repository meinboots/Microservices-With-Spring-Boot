package com.mutantstore.order.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private long orderId;
    private String orderStatus;
    private Instant orderDate;
    private long amount;
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;

    //Inner Class for Order Response
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductDetails {

        private long productId;
        private String productName;
        private long price;
        private long quantity;

    }

    //Inner Class for Payment Response
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PaymentDetails {

        private long paymentId;
        private PaymentMode paymentMode;
        private String paymentStatus;
        private Instant paymentDate;

    }


}
