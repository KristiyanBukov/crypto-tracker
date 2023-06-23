package com.example.cryptotracker.service;

import com.example.cryptotracker.dto.TransactionDto;

public interface ExchangeRateService {

    void getHistoricalRatesUsdEurToBgn(TransactionDto transactionDto);

    void getHistoricalExchangeRates(TransactionDto transactionDto);
}
