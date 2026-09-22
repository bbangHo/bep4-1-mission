package com.back.shard.cash.event;

import com.back.shard.market.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CashOrderPaymentFailedEvent {
    private final String resultCode;
    private final String msg;
    private final OrderDto order;
    private final long pgPaymentAmount;
    private final long shortfallAmount;
}
