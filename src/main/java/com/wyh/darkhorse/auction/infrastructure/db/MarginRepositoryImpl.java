package com.wyh.darkhorse.auction.infrastructure.db;

import com.wyh.darkhorse.auction.infrastructure.db.entity.MarginEntity;
import com.wyh.darkhorse.auction.infrastructure.db.jpa.MarginRepositoryJpa;
import com.wyh.darkhorse.auction.payment.Margin;
import com.wyh.darkhorse.auction.payment.respository.MarginRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MarginRepositoryImpl implements MarginRepository {
    private final MarginRepositoryJpa marginRepositoryJpa;

    @Override
    public void save(Margin margin) {
        MarginEntity entity = MarginEntity.from(margin);
        marginRepositoryJpa.save(entity);
    }
}
