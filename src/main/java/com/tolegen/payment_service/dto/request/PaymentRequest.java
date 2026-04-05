package com.tolegen.payment_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotBlank(message = "currency is required")
    @Pattern(regexp = "^(KZT|USD|EUR|RUB|CNY)$",
            message = "Currency must be one of: KZT, USD, EUR, RUB, CNY")
    private String currency;

    @Size(max = 255, message = "Description is too long")
    private String description;

    @NotBlank(message = "Client ID is required")
    private String clientId;
}
