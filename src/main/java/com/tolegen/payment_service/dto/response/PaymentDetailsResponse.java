package com.tolegen.payment_service.dto.response;

import com.tolegen.payment_service.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailsResponse {
    private Long paymentId;
    private BigDecimal amount;
    private String currency;
    private String description;
    private String clientId;
    private PaymentStatus status;
}
