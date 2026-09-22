package com.back.shared.market.dto;

import com.back.boundedContext.market.domain.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class OrderDto {
    private final int id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final int customerId;
    private final String customerName;
    private final long price;
    private final long salePrice;
    private final LocalDateTime requestPaymentDate;
    private final LocalDateTime paymentDate;
}
