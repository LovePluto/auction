package com.wyh.darkhorse.auction.service;

import com.wyh.darkhorse.auction.api.dto.CreateMarginDto;
import com.wyh.darkhorse.auction.infrastructure.client.KafkaClient;
import com.wyh.darkhorse.auction.infrastructure.client.PaymentClient;
import com.wyh.darkhorse.auction.infrastructure.client.RedisClient;
import com.wyh.darkhorse.auction.infrastructure.exception.PayException;
import com.wyh.darkhorse.auction.payment.Margin;
import com.wyh.darkhorse.auction.payment.respository.MarginRepository;
import com.wyh.darkhorse.auction.payment.valueobject.PayRequest;
import com.wyh.darkhorse.auction.payment.valueobject.PayResponse;
import com.wyh.darkhorse.auction.quote.QuoteQualification;
import com.wyh.darkhorse.auction.quote.respository.QuoteQualificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentService {
    private final MarginRepository marginRepository;
    private final QuoteQualificationRepository quoteQualificationRepository;
    private final PaymentClient paymentClient;
    private final RedisClient redisClient;
    private final KafkaClient kafkaClient;
    private static final String REDIS_QUOTE_QUALIFICATION_TEMPLATE = "redis:quote_qualification:%s";
    private static final long QUOTE_QUALIFICATION_EXPIRED = 2 * 60 * 60 * 1000;

    @Transactional
    public String createMargins(CreateMarginDto createMarginDto) {
        Margin createdMargin = createMarginDto.toDomain();
        try {
            PayResponse response = paymentClient.pay(getPayValue(createdMargin));
            if (!response.isResult()) {
                log.warn("Create margin payment failed! Response body is:{}", response);
                throw new PayException();
            }
            createdMargin.updatePayInfo(response);
            marginRepository.save(createdMargin);
            QuoteQualification quoteQualification = getQuoteQualification(createdMargin);
            quoteQualificationRepository.save(quoteQualification);
            redisClient.send(String.format(REDIS_QUOTE_QUALIFICATION_TEMPLATE, quoteQualification.getId()), QUOTE_QUALIFICATION_EXPIRED);
            return quoteQualification.getId();
        } catch (PayException payException) {
            throw payException;
        } catch (Exception e) {
            log.error("Create Margin has unknown exception, info is:", e);
            Map<String, String> map = new HashMap<>();
            map.put("orderNumber", createdMargin.getOrderNumber());
            map.put("status", "failed!");
            kafkaClient.send(map);
            throw new PayException();
        }

    }

    private QuoteQualification getQuoteQualification(Margin createdMargin) {
        return new QuoteQualification();
    }

    private PayRequest getPayValue(Margin createdMargin) {
        return new PayRequest();
    }

}
