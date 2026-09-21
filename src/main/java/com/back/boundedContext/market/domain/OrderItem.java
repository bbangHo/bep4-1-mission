package com.back.boundedContext.market.domain;

import com.back.boundedContext.global.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Entity
@Table(name = "MARKET_ORDER_ITEM")
@NoArgsConstructor
public class OrderItem extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    private String productName;
    private long price;
    private long salePrice;
    private double payoutRate = MarketPolicy.PRODUCT_PAYOUT_RATE;

    public OrderItem(Order order, Product product , String productName, long price, long salePrice) {
        this.product = product;
        this.order = order;
        this.productName = productName;
        this.price = price;
        this.salePrice = salePrice;
    }
}
