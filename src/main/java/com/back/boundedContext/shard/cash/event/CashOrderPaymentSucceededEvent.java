package com.back.boundedContext.shard.cash.event;

import com.back.boundedContext.shard.market.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CashOrderPaymentSucceededEvent {
    private final OrderDto order;
    private final long pgPaymentAmount;
}
