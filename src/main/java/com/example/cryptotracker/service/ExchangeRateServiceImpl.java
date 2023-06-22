package com.example.cryptotracker.service;

import com.example.cryptotracker.dto.TransactionDto;
import com.example.cryptotracker.dto.exchagerate.ExchangeRateResponse;
import com.example.cryptotracker.enums.CurrencyType;
import com.example.cryptotracker.exception.IllegalStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final RestTemplate restTemplate;

    @Autowired
    public ExchangeRateServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void getHistoricalRatesData(TransactionDto transactionDto) {

        ResponseEntity<ExchangeRateResponse> exchangeRateUsd = restTemplate.getForEntity(buildUrl(transactionDto.getBuyDate(),
                CurrencyType.USD), ExchangeRateResponse.class);

        ResponseEntity<ExchangeRateResponse> exchangeRateEur = restTemplate.getForEntity(buildUrl(transactionDto.getBuyDate(),
                CurrencyType.EUR), ExchangeRateResponse.class);

        transactionDto.setExchangeRateOfUsd(exchangeRateUsd.getBody().getRates().get(CurrencyType.BGN.getCode()));
        transactionDto.setExchangeRateOfEur(exchangeRateEur.getBody().getRates().get(CurrencyType.BGN.getCode()));

        if (CollectionUtils.isEmpty(exchangeRateUsd.getBody().getRates()) ||
                CollectionUtils.isEmpty(exchangeRateEur.getBody().getRates())) {
            throw new IllegalStateException("Failed to retrieve historical rates data");
        }

    }

    private String buildUrl(LocalDate localDate, CurrencyType currencyType) {
        return String.format("https://api.exchangerate.host/%s?base=%s&symbols=%s", localDate, currencyType, CurrencyType.BGN);
    }
}
