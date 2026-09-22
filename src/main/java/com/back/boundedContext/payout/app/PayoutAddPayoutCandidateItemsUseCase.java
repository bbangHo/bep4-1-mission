package com.back.boundedContext.payout.app;

import com.back.shared.market.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayoutAddPayoutCandidateItemsUseCase {

    @Transactional
    public void addPayoutCandidateItems(OrderDto order) {
        log.debug("addPayoutCandidateItems.order: {}", order.getId());
    }
}
