package com.back.boundedContext.market.domain;

import com.back.global.entity.BaseIdAndTime;
import com.back.shard.market.dto.OrderDto;
import com.back.shard.market.event.MarketOrderPaymentRequestedEvent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;

@Getter
@Entity
@Table(name = "MARKET_ORDER")
@NoArgsConstructor
@Slf4j
public class Order extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private MarketMember buyer;

    private long price;
    private long salePrice;

    private LocalDateTime requestPaymentDate;
    private LocalDateTime paymentDate;

    @OneToMany(mappedBy = "order", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Order(Cart cart) {
        this.buyer = cart.getBuyer();

        cart.getItems().forEach(item -> {
            addItem(item.getProduct());
        });
    }

    public void addItem(Product product) {
        OrderItem orderItem = new OrderItem(
                this,
                product,
                product.getName(),
                product.getPrice(),
                product.getSalePrice()
        );

        items.add(orderItem);

        price += product.getPrice();
        salePrice += product.getSalePrice();
    }

    public boolean isPaid() {
        return paymentDate != null;
    }

    public void completePayment() {
        paymentDate = LocalDateTime.now();
        log.info("completePayment: " + paymentDate);
    }

    public void requestPayment(long pgPaymentAmount) {
        requestPaymentDate = LocalDateTime.now();
        log.info("requestPayment: " + requestPaymentDate);
        publishEvent(new MarketOrderPaymentRequestedEvent(new OrderDto(this), pgPaymentAmount));
    }

    public void cancelRequestPayment() {
        requestPaymentDate = null;
    }
}
