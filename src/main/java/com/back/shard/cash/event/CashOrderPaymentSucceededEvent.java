package com.back.shard.cash.event;

import com.back.shard.market.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CashOrderPaymentSucceededEvent {
    private final OrderDto order;
    private final long pgPaymentAmount;
}
