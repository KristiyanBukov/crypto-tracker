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

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final RestTemplate restTemplate;

    @Autowired
    public ExchangeRateServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void getHistoricalRatesUsdEurToBgn(TransactionDto transactionDto) {

        ResponseEntity<ExchangeRateResponse> exchangeRateUsd = restTemplate.getForEntity(buildUrl(transactionDto.getBuyDate(),
                CurrencyType.USD, CurrencyType.BGN), ExchangeRateResponse.class);

        ResponseEntity<ExchangeRateResponse> exchangeRateEur = restTemplate.getForEntity(buildUrl(transactionDto.getBuyDate(),
                CurrencyType.EUR, CurrencyType.BGN), ExchangeRateResponse.class);

        transactionDto.setExchangeRateUsdToBgn(exchangeRateUsd.getBody().getRates().get(CurrencyType.BGN.getCode()));
        transactionDto.setExchangeRateEurToBgn(exchangeRateEur.getBody().getRates().get(CurrencyType.BGN.getCode()));

        if (CollectionUtils.isEmpty(exchangeRateUsd.getBody().getRates()) ||
                CollectionUtils.isEmpty(exchangeRateEur.getBody().getRates())) {
            throw new IllegalStateException("Failed to retrieve historical rates data for Bgn");
        }
    }

    @Override
    public void getHistoricalExchangeRates(TransactionDto transactionDto) {

        ResponseEntity<ExchangeRateResponse> exchangeRateResponse = restTemplate.getForEntity(buildUrl(transactionDto.getBuyDate(),
                transactionDto.getCurrencyType(), CurrencyType.USD), ExchangeRateResponse.class);

        BigDecimal exchangeRate = exchangeRateResponse.getBody().getRates().get(CurrencyType.USD.getCode());
        transactionDto.setCurrencyInvestedExchangeRateToUsd(exchangeRate);
        transactionDto.setCurrencyInvested(transactionDto.getCurrencyInvested().multiply(exchangeRate));
        System.out.println(exchangeRate);
        if (CollectionUtils.isEmpty(exchangeRateResponse.getBody().getRates())) {
            throw new IllegalStateException("Failed to retrieve historical exchange rates");
        }
    }

    private String buildUrl(LocalDate localDate, CurrencyType currencyTypeBase, CurrencyType currencyTypeSymbols) {
        return String.format("https://api.exchangerate.host/%s?base=%s&symbols=%s", localDate, currencyTypeBase, currencyTypeSymbols);
    }

}
