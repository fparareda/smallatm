package org.fakebank.model;

public class Amount {

    private Currency currency;
    private double value;

    public Currency getCurrency() {
        return currency;
    }

    public double getValue() {
        return value;
    }

    public static final class AmountBuilder {
        private Currency currency;
        private double value;

        private AmountBuilder() {
        }

        public static AmountBuilder anAmount() {
            return new AmountBuilder();
        }

        public AmountBuilder withCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public AmountBuilder withValue(double value) {
            this.value = value;
            return this;
        }

        public Amount build() {
            Amount amount = new Amount();
            amount.value = this.value;
            amount.currency = this.currency;
            return amount;
        }
    }
}
