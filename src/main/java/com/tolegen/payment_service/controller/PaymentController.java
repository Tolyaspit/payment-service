package com.tolegen.payment_service.controller;


import com.tolegen.payment_service.dto.request.PaymentRequest;
import com.tolegen.payment_service.dto.response.PaymentDetailsResponse;
import com.tolegen.payment_service.dto.response.PaymentResponse;
import com.tolegen.payment_service.dto.response.PaymentSummaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api")
public interface PaymentController {

    @PostMapping("/payments")
    @Operation(summary = "Create new payment", description = "Creates new payment with status PENDING")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment successfully created"),
            @ApiResponse(responseCode = "400", description = "Incorrect input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest request);

    @GetMapping("/payments/{paymentId}")
    @Operation(summary = "Get payment information", description = "Returns detailed information about payment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment found"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    ResponseEntity<PaymentDetailsResponse> getPayment(
            @Parameter(description = "ID of payment", required = true)
            @PathVariable Long paymentId);

    @PostMapping("/payments/{paymentId}/confirm")
    @Operation(summary = "Confirm payment", description = "Changes status of payment to CONFIRMED")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment confirmed"),
            @ApiResponse(responseCode = "400", description = "Can not confirm payment"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    ResponseEntity<PaymentResponse> confirmPayment(
            @Parameter(description = "ID of payment", required = true)
            @PathVariable Long paymentId);

    @PostMapping("/payments/{paymentId}/cancel")
    @Operation(summary = "Cancel payment", description = "Changes status of payment to CANCELED")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment cancelled"),
            @ApiResponse(responseCode = "400", description = "Can not cancel payment"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    ResponseEntity<PaymentResponse> cancelPayment(
            @Parameter(description = "ID of payment", required = true)
            @PathVariable Long paymentId);

    @GetMapping("/clients/{clientId}/payments")
    @Operation(summary = "Fetch all payments of client", description = "Returns list of all payments of client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of payments found"),
            @ApiResponse(responseCode = "404", description = "Client not found or no payments")
    })
    ResponseEntity<List<PaymentSummaryResponse>> getClientPayments(
            @Parameter(description = "ID of payment", required = true)
            @PathVariable String clientId);
}
