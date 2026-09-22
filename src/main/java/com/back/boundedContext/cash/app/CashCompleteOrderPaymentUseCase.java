package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashLog;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.global.eventPublisher.EventPublisher;
import com.back.shard.cash.event.CashOrderPaymentFailedEvent;
import com.back.shard.cash.event.CashOrderPaymentSucceededEvent;
import com.back.shard.market.event.MarketOrderPaymentRequestedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashCompleteOrderPaymentUseCase {
    private final CashMemberRepository cashMemberRepository;
    private final CashSupport cashSupport;
    private final EventPublisher eventPublisher;

    @Transactional
    public void handle(MarketOrderPaymentRequestedEvent event) {
        Wallet customerWallet  = cashSupport.findWalletByHolderId(event.getOrder().getCustomerId()).get();
        Wallet holdingWallet  = cashSupport.findHoldingWallet().get();

        if(event.getPgPaymentAmount() > 0) {
            customerWallet.credit(
                    event.getPgPaymentAmount(),
                    CashLog.EventType.충전__PG결제_토스페이먼츠,
                    "Order",
                    event.getOrder().getId()
            );
        }

        boolean canPay = customerWallet.getBalance() >= event.getOrder().getSalePrice();

        if(canPay) {
            customerWallet.debit(
                    event.getOrder().getSalePrice(),
                    CashLog.EventType.사용__주문결제,
                    "Order",
                    event.getOrder().getId()
            );

            holdingWallet.credit(
                    event.getOrder().getSalePrice(),
                    CashLog.EventType.임시보관__주문결제,
                    "Order",
                    event.getOrder().getId()
            );

            eventPublisher.publish(
                    new CashOrderPaymentSucceededEvent(event.getOrder(), event.getPgPaymentAmount()));
        } else {
            eventPublisher.publish(
                    new CashOrderPaymentFailedEvent(
                            "400-1",
                            "충전은 완료했지만 %d번 주문을 결제완료처리를 하기에는 예치금이 부족합니다.".formatted(event.getOrder().getId()),
                            event.getOrder(),
                            event.getPgPaymentAmount(),     // 충전 금액
                            event.getOrder().getSalePrice() - customerWallet.getBalance()  // 부족한 금액
                    )
            );
        }
    }
}
