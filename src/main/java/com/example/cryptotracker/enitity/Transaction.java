package com.example.cryptotracker.enitity;

import com.example.cryptotracker.enums.AssetType;
import com.example.cryptotracker.enums.CurrencyType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING) // Storing enums like strings in database and retrieving them like enums
    private AssetType assetType;
    private LocalDate buyDate;
    @Column( scale = 2, precision = 11)
    private BigDecimal currencyInvested;
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    @Column( scale = 8, precision = 11)
    private BigDecimal amountOfAsset;
    @Column( scale = 2, precision = 11)
    private BigDecimal assetBuyingPrice;
    private Double exchangeRateOfUsd;
    private Double exchangeRateOfEur;

    public AssetType getAssetType() {
        return assetType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getAmountOfAsset() {
        return amountOfAsset;
    }

    public void setAmountOfAsset(BigDecimal amountOfAsset) {
        this.amountOfAsset = amountOfAsset;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getCurrencyInvested() {
        return currencyInvested;
    }

    public void setCurrencyInvested(BigDecimal currencyInvested) {
        this.currencyInvested = currencyInvested;
    }

    public BigDecimal getAssetBuyingPrice() {
        return assetBuyingPrice;
    }

    public void setAssetBuyingPrice(BigDecimal assetBuyingPrice) {
        this.assetBuyingPrice = assetBuyingPrice;
    }

    public Double getExchangeRateOfUsd() {
        return exchangeRateOfUsd;
    }

    public void setExchangeRateOfUsd(Double exchangeRateOfUsd) {
        this.exchangeRateOfUsd = exchangeRateOfUsd;
    }

    public Double getExchangeRateOfEur() {
        return exchangeRateOfEur;
    }

    public void setExchangeRateOfEur(Double exchangeRateOfEur) {
        this.exchangeRateOfEur = exchangeRateOfEur;
    }
}
