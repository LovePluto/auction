package com.wyh.darkhorse.auction.util;

import java.util.UUID;

public class UuidUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
