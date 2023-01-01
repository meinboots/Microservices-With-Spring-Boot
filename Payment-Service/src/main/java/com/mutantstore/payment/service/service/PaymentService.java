package com.mutantstore.payment.service.service;

import com.mutantstore.payment.service.model.PaymentRequest;

public interface PaymentService {

    long doPayment(PaymentRequest paymentRequest);
}
