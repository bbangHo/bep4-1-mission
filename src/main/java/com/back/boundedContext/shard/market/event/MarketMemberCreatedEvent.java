package com.back.boundedContext.shard.market.event;

import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.shard.market.dto.MarketMemberDto;
import lombok.Getter;

@Getter
public class MarketMemberCreatedEvent {
    MarketMemberDto memberDto;

    public MarketMemberCreatedEvent(MarketMemberDto memberDto) {
        this.memberDto = memberDto;
    }
}
