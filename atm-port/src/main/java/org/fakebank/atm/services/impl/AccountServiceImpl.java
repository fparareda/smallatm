package org.fakebank.atm.services.impl;

import org.fakebank.atm.services.AccountService;
import org.fakebank.model.Account;
import org.fakebank.model.Amount;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public void checkBalance(Account account) {

    }

    @Override
    public void addBalance(Account account, Amount amount) {

    }

    @Override
    public void withdrawAmount(Account account, Amount amount) {

    }
}
