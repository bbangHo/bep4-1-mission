package com.back.shard.market.event;

import com.back.shard.market.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MarketOrderPaymentRequestedEvent {
    private final OrderDto order;
    private final long pgPaymentAmount;
}
