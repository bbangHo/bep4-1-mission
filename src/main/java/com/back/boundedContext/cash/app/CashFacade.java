package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.shard.cash.dto.CashMemberDto;
import com.back.shard.market.event.MarketOrderPaymentRequestedEvent;
import com.back.shard.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CashFacade {
    private final CashSupport cashSupport;
    private final CashCreateWalletUseCase cashCreateWalletUseCase;
    private final CashSyncMemberUseCase cashSyncMemberUseCase;
    private final CashCompleteOrderPaymentUseCase cashCompleteOrderPaymentUseCase;

    @Transactional
    public void handle(MarketOrderPaymentRequestedEvent event) {
        cashCompleteOrderPaymentUseCase.handle(event);
    }

    @Transactional(readOnly = true)
    public Optional<CashMember> findCashMemberByUsername(String username) {
        return cashSupport.findCashMemberByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Wallet> findWalletByHolder(CashMember cashMember) {
        return cashSupport.findWalletByHolder(cashMember);
    }

    @Transactional
    public CashMember syncMember(MemberDto memberDto) {
        return cashSyncMemberUseCase.syncMember(memberDto);
    }

    @Transactional
    public Wallet createWallet(CashMemberDto cashMemberDto) {
        return cashCreateWalletUseCase.createWallet(cashMemberDto);
    }

}
