package com.back.boundedContext.payout.domain;

import com.back.global.entity.BaseIdAndTime;
import com.back.global.entity.BaseManualIdAndTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "PAYOUT_PAYOUT")
@NoArgsConstructor
public class Payout extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private PayoutMember payee;

    private long amount;
    private LocalDateTime payoutDate;

    public Payout(PayoutMember payee) {
        this.payee = payee;
        amount = 0;
        payoutDate = null;
    }

    @OneToMany(mappedBy = "payout", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<PayoutItem> items = new ArrayList<>();

    public PayoutItem addItem(PayoutEventType eventType, String relTypeCode, int relId, LocalDateTime payDate, PayoutMember payer, PayoutMember payee, long amount) {
        PayoutItem payoutItem = new PayoutItem(
                this, eventType, relTypeCode, relId, payDate, payer, payee, amount
        );

        items.add(payoutItem);

        this.amount += amount;

        return payoutItem;
    }
}
