package org.fakebank.model;

public class Account {

    private String id;
    private Currency currency;
    private User user;

    public String getId() {
        return id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public User getUser() {
        return user;
    }

    public static final class AccountBuilder {
        private String id;
        private Currency currency;
        private User user;

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

        public AccountBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.id = this.id;
            account.user = this.user;
            account.currency = this.currency;
            return account;
        }
    }
}
