package com.tolegen.payment_service.controller;


import com.tolegen.payment_service.dto.request.PaymentRequest;
import com.tolegen.payment_service.dto.response.PaymentDetailsResponse;
import com.tolegen.payment_service.dto.response.PaymentResponse;
import com.tolegen.payment_service.dto.response.PaymentSummaryResponse;
import com.tolegen.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PaymentControllerImpl implements PaymentController{

    private final PaymentService paymentService;

    @Override
    public ResponseEntity<PaymentResponse> createPayment(PaymentRequest request) {
        log.info("POST /api/payments - Creating payment for client: {}", request.getClientId());
        PaymentResponse response = paymentService.createPayment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    public ResponseEntity<PaymentDetailsResponse> getPayment(Long paymentId) {
        log.info("GET /api/payments/{} - Fetching payment", paymentId);
        PaymentDetailsResponse response = paymentService.getPayment(paymentId);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<PaymentResponse> confirmPayment(Long paymentId) {
        log.info("POST /api/payments{}/confirm - Confirming payment", paymentId);
        PaymentResponse response = paymentService.confirmPayment(paymentId);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<PaymentResponse> cancelPayment(Long paymentId) {
        log.info("POST /api/payments/{}/cancel - Cancelling payment", paymentId);
        PaymentResponse response = paymentService.cancelPayment(paymentId);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<PaymentSummaryResponse>> getClientPayments(String clientId) {
        log.info("GET /api/clients/{}/payments - Fetching all payments of client", clientId);
        List<PaymentSummaryResponse> responses = paymentService.getClientPayments(clientId);

        return ResponseEntity.ok(responses);
    }
}
