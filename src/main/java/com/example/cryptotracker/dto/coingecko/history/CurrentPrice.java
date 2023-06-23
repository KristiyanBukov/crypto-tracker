package com.example.cryptotracker.dto.coingecko.history;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class CurrentPrice{
	@JsonProperty("usd")
	private BigDecimal usd;

	public BigDecimal getUsd() {
		return usd;
	}

	public void setUsd(BigDecimal usd) {
		this.usd = usd;
	}

	@Override
	public String toString() {
		return "CurrentPrice{" +
				"usd=" + usd +
				'}';
	}
}