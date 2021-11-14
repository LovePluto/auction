package com.wyh.darkhorse.auction.payment.respository;

import com.wyh.darkhorse.auction.payment.Margin;

public interface MarginRepository {
    void save(Margin createdMargin);
}
