package org.fakebank.repository.repository;

import org.fakebank.repository.model.AccountJPA;

import java.util.Optional;

public interface AccountRepository {
    AccountJPA addAccount(AccountJPA accountJPA);
    Optional<AccountJPA> findById(String accountId);
    AccountJPA save(AccountJPA accountJPA);
}
