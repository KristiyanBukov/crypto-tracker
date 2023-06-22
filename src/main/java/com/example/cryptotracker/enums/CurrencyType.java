package com.example.cryptotracker.enums;

public enum CurrencyType {

    BGN("Bulgarian lev", "lv", "BGN"),
    EUR("Euro", "€", "EUR"),
    USD("US Dollar", "$", "USD"),
    GBP("British Pound", "£", "GBP"),
    JPY("Japanese Yen", "¥", "JPY"),
    AUD("Australian Dollar", "$", "AUD"),
    CAD("Canadian Dollar", "$", "CAD"),
    CHF("Swiss Franc", "CHF", "CHF"),
    CNY("Chinese Yuan", "¥", "CNY"),
    INR("Indian Rupee", "₹", "INR");

    private final String displayName;
    private final String symbol;
    private final String code;

    CurrencyType(String displayName, String symbol, String code) {
        this.displayName = displayName;
        this.symbol = symbol;
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCode() {
        return code;
    }
}
