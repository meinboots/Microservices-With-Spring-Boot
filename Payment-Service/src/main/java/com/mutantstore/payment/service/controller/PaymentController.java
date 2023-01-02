package com.mutantstore.payment.service.controller;


import com.mutantstore.payment.service.model.PaymentRequest;
import com.mutantstore.payment.service.model.PaymentResponse;
import com.mutantstore.payment.service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping()
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){

        return new ResponseEntity<>(paymentService.doPayment(paymentRequest),
                HttpStatus.OK);

    }

    @GetMapping("/order/{orderId}")
     public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable Long orderId){
        return new ResponseEntity<>(
                paymentService.getPaymentDetailsByOrderId(orderId),
                HttpStatus.OK
        );
     }

}
