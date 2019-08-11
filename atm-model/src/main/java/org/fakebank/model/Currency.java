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

    public static Currency valueOfName(String name) {
        for (Currency currency : values()) {
            if (currency.name.equals(name)) {
                return currency;
            }
        }
        return null;
    }

}
