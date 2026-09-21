package com.back.boundedContext.market.in;

import com.back.boundedContext.market.app.MarketMemberFacade;
import com.back.boundedContext.shard.member.event.MemberJoinedEvent;
import com.back.boundedContext.shard.member.event.MemberModifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@RequiredArgsConstructor
public class MarketMemberListener {
    private final MarketMemberFacade memberFacade;

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberJoinedEvent event) {
        memberFacade.syncMember(event.getMemberDto());
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberModifiedEvent event) {
        memberFacade.syncMember(event.getMemberDto());
    }
}
