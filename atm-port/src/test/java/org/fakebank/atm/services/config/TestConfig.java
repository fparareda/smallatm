package org.fakebank.atm.services.config;

import org.fakebank.atm.services.validators.ATMValidator;
import org.fakebank.atm.services.validators.ATMValidatorCurrency;
import org.fakebank.atm.services.validators.ATMValidatorMultiple;
import org.fakebank.atm.services.validators.ATMValidatorRange;
import org.fakebank.repository.adapters.AccountAdapter;
import org.fakebank.repository.fake.AccountRepositoryFake;
import org.fakebank.repository.mappers.AccountMapper;
import org.fakebank.repository.repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class TestConfig {

    @Bean
    public AccountAdapter getAccountAdapter(AccountMapper accountMapper, AccountRepository accountRepository) {
        return new AccountAdapter(accountMapper, accountRepository);
    }

    @Bean
    public AccountMapper getAccountMapper() {
        return new AccountMapper();
    }

    @Bean
    public AccountRepository getAccountRepository() {
        return new AccountRepositoryFake();
    }

    @Bean
    public List<ATMValidator> getAtmValidators() {
        return Arrays.asList(new ATMValidatorRange(), new ATMValidatorCurrency(), new ATMValidatorMultiple());
    }

}
