package com.mutantstore.payment.service.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private long orderId;
    private PaymentMode paymentMode;
    private String referenceNumber;
    private long amount;

    //Reference :
//    private long orderId;
//    private String paymentMode;
//    private String referenceNumber;
//    private Instant paymentDate;
//    private String paymentStatus;
//    private long amount;

}
