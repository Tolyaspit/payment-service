package com.tolegen.payment_service.service;

import com.tolegen.payment_service.dto.request.PaymentRequest;
import com.tolegen.payment_service.dto.response.PaymentDetailsResponse;
import com.tolegen.payment_service.dto.response.PaymentResponse;
import com.tolegen.payment_service.dto.response.PaymentSummaryResponse;
import com.tolegen.payment_service.enums.PaymentStatus;
import com.tolegen.payment_service.exception.InvalidPaymentStateException;
import com.tolegen.payment_service.exception.PaymentNotFoundException;
import com.tolegen.payment_service.mapper.PaymentMapper;
import com.tolegen.payment_service.model.Payment;
import com.tolegen.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {
        log.info("Creating new payment for client: {}", request.getClientId());

        Payment payment = paymentMapper.toModel(request);
        Payment savedPayment = paymentRepository.save(payment);

        log.info("Payment created successfully with id: {}", savedPayment.getPaymentId());
        return paymentMapper.toResponse(savedPayment);
    }

    @Override
    public PaymentDetailsResponse getPayment(Long paymentId) {
        log.info("Fetching payment with id: {}", paymentId);

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + paymentId));

        return paymentMapper.toDetailsResponse(payment);
    }

    @Override
    public PaymentResponse confirmPayment(Long paymentId) {
        log.info("Confirming payment with id: {}", paymentId);

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + paymentId));

        if (payment.getStatus() != PaymentStatus.PENDING){
            throw new InvalidPaymentStateException(
                    String.format("Cannot confirm payment with status: %s. Only PENDING payments can be confirmed.", payment.getStatus())
            );
        }

        payment.setStatus(PaymentStatus.CONFIRMED);
        Payment updated = paymentRepository.save(payment);

        log.info("Payment {} confirmed successfully", paymentId);
        return paymentMapper.toResponse(updated);
    }

    @Override
    public PaymentResponse cancelPayment(Long paymentId) {
        log.info("Cancelling payment with id: {}", paymentId);

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + paymentId));

        if (payment.getStatus() != PaymentStatus.PENDING){
            throw new InvalidPaymentStateException(
                    String.format("Cannot cancel payment with status: %s. Only PENDING payments can be canceled", payment.getStatus())
            );
        }

        payment.setStatus(PaymentStatus.CANCELED);
        Payment updated = paymentRepository.save(payment);

        log.info("Payment {} cancelled successfully", paymentId);
        return paymentMapper.toResponse(updated);
    }

    @Override
    public List<PaymentSummaryResponse> getClientPayments(String clientId) {
        log.info("Fetching all payments for client: {}", clientId);
        List<Payment> payments = paymentRepository.findByClientIdOrderByCreatedAtDesc(clientId);

        if (payments.isEmpty()){
            throw new PaymentNotFoundException("No payments found for clients: " + clientId);
        }

        log.info("Found {} payments for client: {}", payments.size(), clientId);
        return paymentMapper.toSummaryResponseList(payments);
    }
}
