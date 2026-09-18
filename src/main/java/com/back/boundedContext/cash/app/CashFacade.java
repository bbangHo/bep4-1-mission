package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.boundedContext.cash.out.WalletRepository;
import com.back.boundedContext.shard.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashFacade {
    private final CashMemberRepository cashMemberRepository;
    private final WalletRepository walletRepository;

    @Transactional(readOnly = true)
    public CashMember findCashMemberByUsername(String username) {
        return cashMemberRepository.findByUsername(username).get();
    }

    @Transactional(readOnly = true)
    public Wallet findWalletById(int id) {
        return walletRepository.findById(id).get();
    }

    @Transactional
    public CashMember syncMember(MemberDto memberDto) {
        return cashMemberRepository.save(
                new CashMember(
                        memberDto.getId(),
                        memberDto.getUsername(),
                        "",
                        memberDto.getNickname(),
                        memberDto.getCreateDate(),
                        memberDto.getModifyDate(),
                        memberDto.getActivityScore()
                )
        );
    }

    @Transactional
    public Wallet createWallet(CashMember member) {
        Wallet wallet = walletRepository.save(new Wallet(member));
        return wallet;
    }
}
