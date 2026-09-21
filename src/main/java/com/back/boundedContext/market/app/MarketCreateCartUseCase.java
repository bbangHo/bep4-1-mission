package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.Cart;
import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.domain.Product;
import com.back.boundedContext.market.out.CartRepository;
import com.back.boundedContext.market.out.MarketMemberRepository;
import com.back.boundedContext.market.out.ProductRepository;
import com.back.boundedContext.shard.market.dto.MarketMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarketCreateCartUseCase {
    private final CartRepository cartRepository;
    private final MarketMemberRepository marketMemberRepository;

    @Transactional
    public Cart createCart(MarketMemberDto member) {
        MarketMember marketMember = marketMemberRepository.findById(member.getId()).get();
        return cartRepository.save(new Cart(marketMember));
    }
}
