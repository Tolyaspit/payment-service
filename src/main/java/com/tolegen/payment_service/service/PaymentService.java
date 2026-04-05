package com.tolegen.payment_service.service;

import com.tolegen.payment_service.dto.request.PaymentRequest;
import com.tolegen.payment_service.dto.response.PaymentDetailsResponse;
import com.tolegen.payment_service.dto.response.PaymentResponse;
import com.tolegen.payment_service.dto.response.PaymentSummaryResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest request);
    PaymentDetailsResponse getPayment(Long paymentId);
    PaymentResponse confirmPayment(Long paymentId);
    PaymentResponse cancelPayment(Long paymentId);
    List<PaymentSummaryResponse> getClientPayments(String clientId);
}
