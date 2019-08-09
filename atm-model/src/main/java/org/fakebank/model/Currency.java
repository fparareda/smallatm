package org.fakebank.model;

public enum Currency {
    GBP("£", "pound"),
    USD("$", "dollar"),
    EUR("€", "euro");

    public final String symbol;
    public final String name;

    Currency(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }
}
