package com.wyh.darkhorse.auction.infrastructure.client;

import com.wyh.darkhorse.auction.payment.valueobject.PayRequest;
import com.wyh.darkhorse.auction.payment.valueobject.PayResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentClient {
    public PayResponse pay(PayRequest payValue) {
        return null;
    }
}
