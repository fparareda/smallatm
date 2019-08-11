package org.fakebank.repository.adapters;

import org.fakebank.model.Account;
import org.fakebank.repository.mappers.AccountMapper;
import org.fakebank.repository.model.AccountJPA;
import org.fakebank.repository.repository.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountAdapter {

    private AccountRepository accountRepository;
    private AccountMapper accountMapper;

    public AccountAdapter(AccountMapper accountMapper, AccountRepository accountRepository) {
        this.accountMapper = accountMapper;
        this.accountRepository = accountRepository;
    }

    public Account changeAmount(String accountId, Double newAmountInAccount) {
        Optional<AccountJPA> accountJPA = accountRepository.findById(accountId);
        if(!accountJPA.isPresent()) {
            throw new RuntimeException("Account not found");
        }
        AccountJPA accountUpdated = accountJPA.get();
        accountUpdated.setValue(newAmountInAccount);
        return accountMapper.getAccount(accountRepository.save(accountUpdated));
    }

    public Account retrieve(String accountId) {
        Optional<AccountJPA> accountJPA = accountRepository.findById(accountId);
        if(!accountJPA.isPresent()) {
            throw new RuntimeException("Account not found");
        }
        return accountMapper.getAccount(accountJPA.get());
    }

    public Account addAccount(Account account) {
        return accountMapper.getAccount(
                accountRepository.addAccount(
                        accountMapper.getAccount(account)));
    }
}
