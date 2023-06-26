package com.example.cryptotracker.dto;

import com.example.cryptotracker.enums.AssetType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionStatisticDto {

    private Long id;
    private AssetType assetType;
    private LocalDate buyDate;
    private BigDecimal currencyInvested;
    private BigDecimal amountOfAsset;
    private BigDecimal assetBuyingPrice;
    private BigDecimal currentValue;
    private BigDecimal  priceChange24h;
    private BigDecimal profitLoss;
    private BigDecimal currentPriceOfAsset;
    private BigDecimal roi;
    private BigDecimal averageDailyRoi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public BigDecimal getCurrencyInvested() {
        return currencyInvested;
    }

    public void setCurrencyInvested(BigDecimal currencyInvested) {
        this.currencyInvested = currencyInvested;
    }

    public BigDecimal getAmountOfAsset() {
        return amountOfAsset;
    }

    public void setAmountOfAsset(BigDecimal amountOfAsset) {
        this.amountOfAsset = amountOfAsset;
    }

    public BigDecimal getAssetBuyingPrice() {
        return assetBuyingPrice;
    }

    public void setAssetBuyingPrice(BigDecimal assetBuyingPrice) {
        this.assetBuyingPrice = assetBuyingPrice;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public BigDecimal getPriceChange24h() {
        return priceChange24h;
    }

    public void setPriceChange24h(BigDecimal priceChange24h) {
        this.priceChange24h = priceChange24h;
    }

    public BigDecimal getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(BigDecimal profitLoss) {
        this.profitLoss = profitLoss;
    }

    public BigDecimal getRoi() {
        return roi;
    }

    public void setRoi(BigDecimal roi) {
        this.roi = roi;
    }

    public BigDecimal getAverageDailyRoi() {
        return averageDailyRoi;
    }

    public void setAverageDailyRoi(BigDecimal averageDailyRoi) {
        this.averageDailyRoi = averageDailyRoi;
    }

    public BigDecimal getCurrentPriceOfAsset() {
        return currentPriceOfAsset;
    }

    public void setCurrentPriceOfAsset(BigDecimal currentPriceOfAsset) {
        this.currentPriceOfAsset = currentPriceOfAsset;
    }
}
