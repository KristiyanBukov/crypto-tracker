package com.example.cryptotracker.dto;

import com.example.cryptotracker.enums.AssetType;
import java.math.BigDecimal;


public class AssetTypeStatisticDto {

    private AssetType assetType;
    private BigDecimal currencyInvested;
    private BigDecimal amountOfAsset;
    private BigDecimal assetBuyingPrice;
    private BigDecimal currenValue;
    private BigDecimal  priceChange24h;
    private BigDecimal profitLoss;
    private BigDecimal roi;
    private BigDecimal costOfInvestment;
    private BigDecimal holdings;

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
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

    public BigDecimal getCurrenValue() {
        return currenValue;
    }

    public void setCurrenValue(BigDecimal currenValue) {
        this.currenValue = currenValue;
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

    public BigDecimal getCostOfInvestment() {
        return costOfInvestment;
    }

    public void setCostOfInvestment(BigDecimal costOfInvestment) {
        this.costOfInvestment = costOfInvestment;
    }

    public BigDecimal getHoldings() {
        return holdings;
    }

    public void setHoldings(BigDecimal holdings) {
        this.holdings = holdings;
    }
}
