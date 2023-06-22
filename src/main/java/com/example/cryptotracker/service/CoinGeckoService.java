package com.example.cryptotracker.service;

import com.example.cryptotracker.dto.TransactionDto;
import com.example.cryptotracker.dto.coingecko.market.CoinGeckoMarketsItem;
import com.example.cryptotracker.dto.coingecko.history.CoinGeckoHistoryItem;

public interface CoinGeckoService {
    CoinGeckoMarketsItem getMarketsData(TransactionDto transactionDto);
    CoinGeckoHistoryItem getHistoryData(TransactionDto transactionDto);
}
