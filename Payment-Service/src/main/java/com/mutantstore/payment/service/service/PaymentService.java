package com.mutantstore.payment.service.service;

import com.mutantstore.payment.service.model.PaymentRequest;
import com.mutantstore.payment.service.model.PaymentResponse;

public interface PaymentService {

    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(Long orderId);
}
