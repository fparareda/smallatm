package org.fakebank.repository.fake;

import org.fakebank.repository.model.AccountJPA;
import org.fakebank.repository.repository.AccountRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountRepositoryFake implements AccountRepository {

    private Map<String, AccountJPA> accountJPAMap;

    public AccountRepositoryFake() {
        this.accountJPAMap = new HashMap<>();
    }

    @Override
    public AccountJPA addAccount(AccountJPA accountJPA){
        if(!accountJPAMap.containsKey(accountJPA.getId())){
            accountJPAMap.put(accountJPA.getId(), accountJPA);
        }
        return accountJPAMap.get(accountJPA.getId());
    }

    @Override
    public Optional<AccountJPA> findById(String accountId) {
        return Optional.ofNullable(accountJPAMap.get(accountId));
    }

    @Override
    public AccountJPA save(AccountJPA accountJPA) {
        return accountJPAMap.put(accountJPA.getId(), accountJPA);
    }
}
