package com.example.cryptotracker.dto.coingecko.history;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarketData{

	@JsonProperty("current_price")
	private CurrentPrice currentPrice;

	public void setCurrentPrice(CurrentPrice currentPrice){
		this.currentPrice = currentPrice;
	}

	public CurrentPrice getCurrentPrice(){
		return currentPrice;
	}

	@Override
	public String toString() {
		return "MarketData{" +
				"currentPrice=" + currentPrice +
				'}';
	}
}