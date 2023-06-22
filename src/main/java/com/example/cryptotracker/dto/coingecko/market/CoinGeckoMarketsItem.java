
package com.example.cryptotracker.dto.coingecko.market;

import com.example.cryptotracker.dto.coingecko.CoinGeckoPrice;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


public class CoinGeckoMarketsItem implements CoinGeckoPrice {

    private Long ath;
    @JsonProperty("ath_change_percentage")
    private Double athChangePercentage;
    @JsonProperty("ath_date")
    private String athDate;

    private Double atl;
    @JsonProperty("atl_change_percentage")
    private Double atlChangePercentage;
    @JsonProperty("atl_date")
    private String atlDate;
    @JsonProperty("circulating_supply")
    private Double circulatingSupply;
    @JsonProperty("current_price")
    private BigDecimal currentPrice;
    @JsonProperty("fully_diluted_valuation")
    private Long fullyDilutedValuation;
    @JsonProperty("high_24h")
    private Long high24H;

    private String id;

    private String image;
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("low_24h")
    private Long low24H;
    @JsonProperty("market_cap")
    private Long marketCap;
    @JsonProperty("market_cap_change_24h")
    private Long marketCapChange24H;
    @JsonProperty("market_cap_change_percentage_24h")
    private Double marketCapChangePercentage24H;
    @JsonProperty("market_cap_rank")
    private Long marketCapRank;
    @JsonProperty("max_supply")
    private Double maxSupply;

    private String name;
    @JsonProperty("price_change_24h")
    private Double priceChange24H;
    @JsonProperty("price_change_percentage_24h")
    private Double priceChangePercentage24H;

    private Object roi;

    private String symbol;
    @JsonProperty("total_supply")
    private Double totalSupply;
    @JsonProperty("total_volume")
    private Long totalVolume;

    @Override
    public BigDecimal getPrice() {
       return this.currentPrice;
    }

    public Long getAth() {
        return ath;
    }

    public void setAth(Long ath) {
        this.ath = ath;
    }

    public Double getAthChangePercentage() {
        return athChangePercentage;
    }

    public void setAthChangePercentage(Double athChangePercentage) {
        this.athChangePercentage = athChangePercentage;
    }

    public String getAthDate() {
        return athDate;
    }

    public void setAthDate(String athDate) {
        this.athDate = athDate;
    }

    public Double getAtl() {
        return atl;
    }

    public void setAtl(Double atl) {
        this.atl = atl;
    }

    public Double getAtlChangePercentage() {
        return atlChangePercentage;
    }

    public void setAtlChangePercentage(Double atlChangePercentage) {
        this.atlChangePercentage = atlChangePercentage;
    }

    public String getAtlDate() {
        return atlDate;
    }

    public void setAtlDate(String atlDate) {
        this.atlDate = atlDate;
    }

    public Double getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(Double circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Long getFullyDilutedValuation() {
        return fullyDilutedValuation;
    }

    public void setFullyDilutedValuation(Long fullyDilutedValuation) {
        this.fullyDilutedValuation = fullyDilutedValuation;
    }

    public Long getHigh24H() {
        return high24H;
    }

    public void setHigh24H(Long high24H) {
        this.high24H = high24H;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Long getLow24H() {
        return low24H;
    }

    public void setLow24H(Long low24H) {
        this.low24H = low24H;
    }

    public Long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }

    public Long getMarketCapChange24H() {
        return marketCapChange24H;
    }

    public void setMarketCapChange24H(Long marketCapChange24H) {
        this.marketCapChange24H = marketCapChange24H;
    }

    public Double getMarketCapChangePercentage24H() {
        return marketCapChangePercentage24H;
    }

    public void setMarketCapChangePercentage24H(Double marketCapChangePercentage24H) {
        this.marketCapChangePercentage24H = marketCapChangePercentage24H;
    }

    public Long getMarketCapRank() {
        return marketCapRank;
    }

    public void setMarketCapRank(Long marketCapRank) {
        this.marketCapRank = marketCapRank;
    }

    public Double getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(Double maxSupply) {
        this.maxSupply = maxSupply;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPriceChange24H() {
        return priceChange24H;
    }

    public void setPriceChange24H(Double priceChange24H) {
        this.priceChange24H = priceChange24H;
    }

    public Double getPriceChangePercentage24H() {
        return priceChangePercentage24H;
    }

    public void setPriceChangePercentage24H(Double priceChangePercentage24H) {
        this.priceChangePercentage24H = priceChangePercentage24H;
    }

    public Object getRoi() {
        return roi;
    }

    public void setRoi(Object roi) {
        this.roi = roi;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Long getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Long totalVolume) {
        this.totalVolume = totalVolume;
    }

}
