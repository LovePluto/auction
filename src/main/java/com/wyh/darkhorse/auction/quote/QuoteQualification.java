package com.wyh.darkhorse.auction.quote;

import com.wyh.darkhorse.auction.util.UuidUtil;
import lombok.Data;

@Data
public class QuoteQualification {
    private String id = UuidUtil.uuid();
}
