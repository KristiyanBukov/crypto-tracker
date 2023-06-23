package com.example.cryptotracker.dto.coingecko.history;

import com.example.cryptotracker.dto.coingecko.CoinGeckoPrice;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CoinGeckoHistoryItem implements CoinGeckoPrice {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("market_data")
    private MarketData marketData;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;

    @Override
    public BigDecimal getPrice() {
        return marketData.getCurrentPrice().getUsd();
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setMarketData(MarketData marketData) {
        this.marketData = marketData;
    }

    public MarketData getMarketData() {
        return marketData;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CoinGeckoHistoryItem{" +
                "symbol='" + symbol + '\'' +
                ", marketData=" + marketData +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}