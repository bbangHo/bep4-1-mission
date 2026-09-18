package com.back.boundedContext.cash.in;

import com.back.boundedContext.cash.app.CashCreateWalletUseCase;
import com.back.boundedContext.cash.app.CashSyncMemberUseCase;
import com.back.boundedContext.cash.domain.CashMember;
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
public class CashEventListener {
    private final CashCreateWalletUseCase cashCreateWalletUseCase;
    private final CashSyncMemberUseCase cashSyncMemberUseCase;

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberJoinedEvent event) {
        CashMember member = cashSyncMemberUseCase.syncMember(event.getMemberDto());

        cashCreateWalletUseCase.createWallet(member);
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberModifiedEvent event) {
        cashSyncMemberUseCase.syncMember(event.getMemberDto());
    }
}
