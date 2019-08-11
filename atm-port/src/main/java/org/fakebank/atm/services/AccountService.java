package org.fakebank.atm.services;

import org.fakebank.model.Account;
import org.fakebank.model.Amount;

public interface AccountService {
    Account createAccount(Account account);
    Account checkBalance(String accountId);
    Account addBalance(String accountId, Amount amount);
    Account withdrawAmount(String accountId, Amount amount);
}
