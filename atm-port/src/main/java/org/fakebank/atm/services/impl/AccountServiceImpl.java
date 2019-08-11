package org.fakebank.atm.services.impl;

import org.fakebank.atm.services.AccountService;
import org.fakebank.model.Account;
import org.fakebank.model.Amount;
import org.fakebank.repository.adapters.AccountAdapter;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountAdapter accountAdapter;

    public AccountServiceImpl(AccountAdapter accountAdapter) {
        this.accountAdapter = accountAdapter;
    }

    @Override
    public Account createAccount(Account account) {
        return accountAdapter.addAccount(account);
    }

    @Override
    public Account checkBalance(String accountId) {
        return accountAdapter.retrieve(accountId);
    }

    @Override
    public Account addBalance(String accountId, Amount amountToAdd) {
        Account accountToAdd = accountAdapter.retrieve(accountId);
        if(!accountToAdd.getCurrency().equals(amountToAdd.getCurrency())) {
            throw new IllegalArgumentException("Mismatched currencies.");
        }
        return accountAdapter.changeAmount(accountId, accountToAdd.getBalance() + amountToAdd.getValue());
    }

    @Override
    public Account withdrawAmount(String accountId, Amount amountToWithdraw) {
        Account accountToWithdraw = accountAdapter.retrieve(accountId);
        if(!accountToWithdraw.getCurrency().equals(amountToWithdraw.getCurrency())) {
            throw new IllegalArgumentException("Mismatched currencies.");
        }

        if(accountToWithdraw.getBalance() < amountToWithdraw.getValue()) {
            throw new RuntimeException("Not enough money.");
        }
        return accountAdapter.changeAmount(accountId, accountToWithdraw.getBalance() - amountToWithdraw.getValue());
    }
}
