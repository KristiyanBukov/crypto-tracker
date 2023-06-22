package com.example.cryptotracker.dto;

import com.example.cryptotracker.enums.AssetType;
import com.example.cryptotracker.enums.CurrencyType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class TransactionDto implements Serializable {
    private Long id;
    @NotNull
    private AssetType assetType;
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private  LocalDate buyDate;
    @NotNull
    @Positive
    private BigDecimal currencyInvested;
    @NotNull
    private CurrencyType currencyType;
    private BigDecimal amountOfAsset;
    private BigDecimal assetBuyingPrice;
    private BigDecimal exchangeRateOfUsd;
    private BigDecimal exchangeRateOfEur;

    public void setId(Long id) {
        this.id = id;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public void setCurrencyInvested(BigDecimal currencyInvested) {
        this.currencyInvested = currencyInvested;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmountOfAsset() {
        return amountOfAsset;
    }

    public void setAmountOfAsset(BigDecimal amountOfAsset) {
        this.amountOfAsset = amountOfAsset;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public BigDecimal getCurrencyInvested() {
        return currencyInvested;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getAssetBuyingPrice() {
        return assetBuyingPrice;
    }

    public void setAssetBuyingPrice(BigDecimal assetBuyingPrice) {
        this.assetBuyingPrice = assetBuyingPrice;
    }

    public BigDecimal getExchangeRateOfUsd() {
        return exchangeRateOfUsd;
    }

    public void setExchangeRateOfUsd(BigDecimal exchangeRateOfUsd) {
        this.exchangeRateOfUsd = exchangeRateOfUsd;
    }

    public BigDecimal getExchangeRateOfEur() {
        return exchangeRateOfEur;
    }

    public void setExchangeRateOfEur(BigDecimal exchangeRateOfEur) {
        this.exchangeRateOfEur = exchangeRateOfEur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDto entity = (TransactionDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.assetType, entity.assetType) &&
                Objects.equals(this.buyDate, entity.buyDate) &&
                Objects.equals(this.currencyInvested, entity.currencyInvested);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assetType, buyDate, currencyInvested);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "assetType = " + assetType + ", " +
                "buyDate = " + buyDate + ", " +
                "currencyInvested = " + currencyInvested + ")";
    }
}