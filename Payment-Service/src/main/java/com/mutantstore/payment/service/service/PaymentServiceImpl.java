package com.mutantstore.payment.service.service;

import com.mutantstore.payment.service.entity.TransactionDetails;
import com.mutantstore.payment.service.model.PaymentMode;
import com.mutantstore.payment.service.model.PaymentRequest;
import com.mutantstore.payment.service.model.PaymentResponse;
import com.mutantstore.payment.service.repository.TransactionDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public long doPayment(PaymentRequest paymentRequest) {

        // Reference :
//        private long orderId;
//        private String paymentMode;
//        private String referenceNumber;
//        private Instant paymentDate;
//        private String paymentStatus;
//        private long amount;
//
        log.info("Recording the payment... {}", paymentRequest);

        TransactionDetails transactionDetails = TransactionDetails.builder()
                .orderId(paymentRequest.getOrderId())
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())//.name() -> This is how we access ENUM Data
                .amount(paymentRequest.getAmount())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .paymentStatus("SUCCESS")
                .build();

        transactionDetailsRepository.save(transactionDetails);

        log.info("Transaction is completed with Id : 😒😒😒  {}", transactionDetails.getId());

        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(Long orderId) {

        log.info("Getting payment details for the orderId : {}", orderId);

        TransactionDetails transactionDetails = transactionDetailsRepository.findByOrderId(orderId);

        return PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .status(transactionDetails.getPaymentStatus())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .paymentDate(transactionDetails.getPaymentDate())
                .orderId(transactionDetails.getOrderId())
                .amount(transactionDetails.getAmount())
                .build();
    }
}
