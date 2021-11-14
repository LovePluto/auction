package com.wyh.darkhorse.auction.infrastructure.db.jpa;

import com.wyh.darkhorse.auction.infrastructure.db.entity.MarginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarginRepositoryJpa extends JpaRepository<MarginEntity, Long> {
}
