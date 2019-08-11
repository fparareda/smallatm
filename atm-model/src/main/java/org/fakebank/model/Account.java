package org.fakebank.model;

public class Account {

    private String id;
    private Currency currency;
    private double balance;

    public String getId() {
        return id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getBalance() {
        return balance;
    }

    public static final class AccountBuilder {
        private String id;
        private Currency currency;
        private double balance;

        private AccountBuilder() {
        }

        public static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        public AccountBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public AccountBuilder withCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public AccountBuilder withBalance(double amount) {
            this.balance = amount;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.id = this.id;
            account.currency = this.currency;
            account.balance = this.balance;
            return account;
        }
    }
}
