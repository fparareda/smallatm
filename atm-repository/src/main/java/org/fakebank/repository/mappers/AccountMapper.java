package org.fakebank.repository.mappers;

import org.fakebank.model.Account;
import org.fakebank.model.Currency;
import org.fakebank.repository.model.AccountJPA;

public class AccountMapper {

    public Account getAccount(AccountJPA accountJPA) {
        return Account.AccountBuilder.anAccount()
                .withId(accountJPA.getId())
                .withCurrency(Currency.valueOfName(accountJPA.getCurrency()))
                .withBalance(accountJPA.getValue()).build();
    }


    public AccountJPA getAccount(Account account) {
        return new AccountJPA(account.getId(),
                account.getBalance(),
                account.getCurrency().name);
    }
}
