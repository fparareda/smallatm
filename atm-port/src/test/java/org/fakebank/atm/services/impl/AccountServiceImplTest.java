package org.fakebank.atm.services.impl;

import org.fakebank.atm.services.AccountService;
import org.fakebank.atm.services.config.TestConfig;
import org.fakebank.model.Account;
import org.fakebank.model.Amount;
import org.fakebank.model.Currency;
import org.fakebank.repository.adapters.AccountAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@SpringBootApplication
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountServiceImplTest {

    @Autowired
    AccountService accountService;

    @Autowired
    private AccountAdapter accountAdapter;

    @Before
    public void before(){
        addAccountsWithBalance();
    }

    private static String ACCOUNT1_ID = "01001";
    private static double ACCOUNT1_AMOUNT = 2738.59;
    private static Currency ACCOUNT1_CURRENCY = Currency.GBP;

    private static String ACCOUNT2_ID = "01002";
    private static double ACCOUNT2_AMOUNT = 23;
    private static Currency ACCOUNT2_CURRENCY = Currency.GBP;

    private static String ACCOUNT3_ID = "01003";
    private static double ACCOUNT3_AMOUNT = 0;
    private static Currency ACCOUNT3_CURRENCY = Currency.GBP;


    public void addAccountsWithBalance() {
        Account account1 = Account.AccountBuilder.anAccount()
                .withId(ACCOUNT1_ID).withBalance(ACCOUNT1_AMOUNT)
                .withCurrency(ACCOUNT1_CURRENCY).build();
        accountService.createAccount(account1);

        Account account2 = Account.AccountBuilder.anAccount()
                .withId(ACCOUNT2_ID).withBalance(ACCOUNT2_AMOUNT)
                .withCurrency(ACCOUNT2_CURRENCY).build();
        accountService.createAccount(account2);

        Account account3 = Account.AccountBuilder.anAccount()
                .withId(ACCOUNT3_ID).withBalance(ACCOUNT3_AMOUNT)
                .withCurrency(ACCOUNT3_CURRENCY).build();
        accountService.createAccount(account3);
    }

    @Test
    public void checkBalance_successfully() {
        Account accountChecked = accountService.checkBalance(ACCOUNT1_ID);
        assertEquals(accountChecked.getBalance(), ACCOUNT1_AMOUNT, 0.0);
    }

    @Test(expected = RuntimeException.class)
    public void checkBalance_account_fail() {
        accountService.checkBalance(ACCOUNT1_ID + "1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addBalance_currency_fails() {
        Amount amount = Amount.AmountBuilder.anAmount().withValue(100).withCurrency(Currency.EUR).build();
        accountService.addBalance(ACCOUNT3_ID, amount);
    }

    @Test
    public void addBalance_successfully() {
        double amountToAdd = 200;
        Amount amount = Amount.AmountBuilder.anAmount().withValue(amountToAdd).withCurrency(Currency.GBP).build();
        Account account = accountService.addBalance(ACCOUNT3_ID, amount);
        assertEquals(account.getBalance(), amountToAdd, 0.0);
    }


    @Test(expected = Exception.class)
    public void withdrawAmount_nomoney_fails(){
        Amount amount = Amount.AmountBuilder.anAmount().withValue(200).withCurrency(Currency.EUR).build();
        accountService.withdrawAmount(ACCOUNT1_ID, amount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawAmount_currency_fails(){
        Amount amount = Amount.AmountBuilder.anAmount().withValue(100).withCurrency(Currency.EUR).build();
        accountService.withdrawAmount(ACCOUNT1_ID, amount);
    }

    @Test
    public void withdrawAmount_successfully() {
        double amountToWithdraw = 20;
        Amount amount = Amount.AmountBuilder.anAmount().withValue(amountToWithdraw).withCurrency(Currency.GBP).build();
        Account account = accountService.withdrawAmount(ACCOUNT2_ID, amount);
        assertEquals(account.getBalance(), (ACCOUNT2_AMOUNT - amountToWithdraw), 0.0);
    }
}