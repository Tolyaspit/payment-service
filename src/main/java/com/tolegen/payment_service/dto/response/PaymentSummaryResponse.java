package com.tolegen.payment_service.dto.response;

import com.tolegen.payment_service.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSummaryResponse {
    private Long paymentId;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;
}
