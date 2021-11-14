package com.wyh.darkhorse.auction.api.dto;

import com.wyh.darkhorse.auction.payment.Margin;

public class CreateMarginDto {

    public Margin toDomain() {
        return new Margin();
    }
}
