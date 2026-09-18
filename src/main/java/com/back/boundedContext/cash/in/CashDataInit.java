package com.back.boundedContext.cash.in;

import com.back.boundedContext.cash.app.CashFacade;
import com.back.boundedContext.cash.domain.CashLog;
import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class CashDataInit {
    private final CashDataInit self;
    private final CashFacade cashFacade;

    public CashDataInit(
            @Lazy CashDataInit self,
            CashFacade cashFacade
    ) {
        this.self = self;
        this.cashFacade = cashFacade;
    }

    @Order(3)
    @Bean
    public ApplicationRunner CashDataInitRunner() {
        return args -> {
            self.makeBaseWallets();
        };
    }

    @Transactional
    public void makeBaseWallets() {
        CashMember cashMember1 = cashFacade.findCashMemberByUsername("user1");
        CashMember cashMember2 = cashFacade.findCashMemberByUsername("user2");

        Wallet wallet1 = cashFacade.findWalletById(cashMember1.getId());
        Wallet wallet2 = cashFacade.findWalletById(cashMember2.getId());

        wallet1.credit(150000, CashLog.EventType.충전__무통장입금, "1", 1);
        wallet1.credit(100000, CashLog.EventType.충전__무통장입금, "1", 1);
        wallet1.credit(50000, CashLog.EventType.충전__무통장입금, "1", 1);
        wallet2.credit(150000, CashLog.EventType.충전__무통장입금, "1", 1);

    }
}
