package com.tolegen.payment_service.mapper;

import com.tolegen.payment_service.dto.request.PaymentRequest;
import com.tolegen.payment_service.dto.response.PaymentDetailsResponse;
import com.tolegen.payment_service.dto.response.PaymentResponse;
import com.tolegen.payment_service.dto.response.PaymentSummaryResponse;
import com.tolegen.payment_service.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Payment toModel(PaymentRequest request);

    PaymentResponse toResponse(Payment payment);

    PaymentDetailsResponse toDetailsResponse(Payment payment);

    PaymentSummaryResponse toSummaryResponse(Payment payment);

    List<PaymentSummaryResponse> toSummaryResponseList(List<Payment> payments);
}
