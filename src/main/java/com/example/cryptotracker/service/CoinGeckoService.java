package com.example.cryptotracker.service;

import com.example.cryptotracker.dto.TransactionDto;
import com.example.cryptotracker.dto.coingecko.history.CoinGeckoHistoryItem;
import com.example.cryptotracker.dto.coingecko.market.CoinGeckoMarketsItem;
import com.example.cryptotracker.enums.AssetType;

public interface CoinGeckoService {
    CoinGeckoMarketsItem getMarketsData(AssetType assetType);

    CoinGeckoHistoryItem getHistoryData(TransactionDto transactionDto);
}
