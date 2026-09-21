package com.back.boundedContext.shard.cash.event;

import com.back.boundedContext.shard.cash.dto.CashMemberDto;
import lombok.Getter;

@Getter
public class CashMemberCreatedEvent {
    CashMemberDto cashMemberDto;

    public CashMemberCreatedEvent(CashMemberDto cashMemberDto) {
        this.cashMemberDto = cashMemberDto;
    }
}
