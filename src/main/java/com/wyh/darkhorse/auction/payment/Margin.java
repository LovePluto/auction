package com.wyh.darkhorse.auction.payment;

import com.wyh.darkhorse.auction.payment.valueobject.PayResponse;
import com.wyh.darkhorse.auction.util.UuidUtil;
import lombok.Data;

@Data
public class Margin {
    private String id = UuidUtil.uuid();
    private String orderNumber;

    public void updatePayInfo(PayResponse payId) {

    }
}
