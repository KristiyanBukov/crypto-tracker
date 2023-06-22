package com.example.cryptotracker.service;

import com.example.cryptotracker.dto.TransactionDto;
import com.example.cryptotracker.dto.exchagerate.ExchangeRateResponse;
import com.example.cryptotracker.enitity.Transaction;

public interface ExchangeRateService {

        void getHistoricalRatesData(TransactionDto transactionDto);
}
