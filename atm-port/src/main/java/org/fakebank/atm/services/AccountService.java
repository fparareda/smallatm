package org.fakebank.atm.services;

import org.fakebank.model.Account;
import org.fakebank.model.Amount;

public interface AccountService {
    void checkBalance(Account account);
    void addBalance(Account account, Amount amount);
    void withdrawAmount(Account account, Amount amount);
}
