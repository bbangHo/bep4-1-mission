package com.back.shard.market.event;

import com.back.shard.market.dto.MarketMemberDto;
import lombok.Getter;

@Getter
public class MarketMemberCreatedEvent {
    MarketMemberDto memberDto;

    public MarketMemberCreatedEvent(MarketMemberDto memberDto) {
        this.memberDto = memberDto;
    }
}
