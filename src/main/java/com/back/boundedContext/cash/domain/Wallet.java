package com.back.boundedContext.cash.domain;

import com.back.boundedContext.global.entity.BaseManualIdAndTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;

@Getter
@Entity
@Table(name = "CASH_WALLET")
@NoArgsConstructor
public class Wallet extends BaseManualIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private CashMember holder;

    @Getter
    private long balance;

    @OneToMany(mappedBy = "wallet", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<CashLog> cashLogs = new ArrayList<>();

    public Wallet(CashMember holder) {
        super(holder.getId());
        this.holder = holder;
    }

    public void credit(long amount, CashLog.EventType eventType, String relTypeCode, int relId){
        balance += amount;

        addCashLog(amount, eventType, relTypeCode, relId);
    }

    public void debit(long amount, CashLog.EventType eventType, String relTypeCode, int relId){
        balance -= amount;

        addCashLog(amount, eventType, relTypeCode, relId);
    }

    private CashLog addCashLog(long amount, CashLog.EventType eventType, String relTypeCode, int relId) {
        CashLog cashLog = new CashLog(
                eventType,
                relTypeCode,
                relId,
                holder,
                this,
                amount,
                balance
        );

        cashLogs.add(cashLog);

        return cashLog;
    }
}
