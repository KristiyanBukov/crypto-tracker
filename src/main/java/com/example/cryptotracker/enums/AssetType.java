package com.example.cryptotracker.enums;

public enum AssetType {
    BTC("bitcoin"), ETH("ethereum"), MATIC("matic-network"), ADA("cardano"), XRP("ripple");

    private final String name;

    AssetType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
