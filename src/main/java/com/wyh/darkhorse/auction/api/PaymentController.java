package com.wyh.darkhorse.auction.api;

import com.wyh.darkhorse.auction.api.dto.CreateMarginDto;
import com.wyh.darkhorse.auction.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/auction/margins")
    public String createMargins(CreateMarginDto createMarginDto) {
        return paymentService.createMargins(createMarginDto);
    }
}
