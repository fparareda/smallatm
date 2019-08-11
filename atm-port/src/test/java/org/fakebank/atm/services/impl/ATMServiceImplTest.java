package org.fakebank.atm.services.impl;

import org.fakebank.atm.services.ATMService;
import org.fakebank.atm.services.AccountService;
import org.fakebank.atm.services.config.TestConfig;
import org.fakebank.model.Account;
import org.fakebank.model.Amount;
import org.fakebank.model.Currency;
import org.fakebank.model.Notes;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@SpringBootApplication
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ATMServiceImplTest {

    @Autowired
    AccountService accountService;

    @Autowired
    ATMService atmService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void before(){
        addAccountsWithBalance();
    }

    private static String ACCOUNT1_ID = "01001";
    private static double ACCOUNT1_AMOUNT = 2738.59;
    private static Currency ACCOUNT1_CURRENCY = Currency.GBP;

    public void addAccountsWithBalance() {
        Account account1 = Account.AccountBuilder.anAccount()
                .withId(ACCOUNT1_ID).withBalance(ACCOUNT1_AMOUNT)
                .withCurrency(ACCOUNT1_CURRENCY).build();
        accountService.createAccount(account1);
    }

    @Test
    public void replenishBalance_successfully() {
        int replenishAmount = 100;
        Amount amount = Amount.AmountBuilder.anAmount().withValue(replenishAmount).withCurrency(Currency.GBP).build();
        Account accountWithNewBalance = atmService.replenish(ACCOUNT1_ID, amount);
        assertThat(ACCOUNT1_AMOUNT + replenishAmount).isEqualTo(accountWithNewBalance.getBalance());
    }

    @Test(expected = RuntimeException.class)
    public void replenishBalance_account_fail() {
        Amount amount = Amount.AmountBuilder.anAmount().withValue(100).withCurrency(Currency.GBP).build();
        atmService.replenish(ACCOUNT1_ID + "1", amount);
    }

    @Test
    public void checkBalance_successfully() {
        assertThat(String.valueOf(ACCOUNT1_AMOUNT)).isEqualTo(atmService.checkBalance(ACCOUNT1_ID));
    }

    @Test(expected = RuntimeException.class)
    public void withdraw_range_fail() {
        int replenishAmount = 1000;
        Amount amount = Amount.AmountBuilder.anAmount().withValue(replenishAmount).withCurrency(Currency.GBP).build();
        atmService.withdraw(ACCOUNT1_ID, amount);
    }

    @Test(expected = RuntimeException.class)
    public void withdraw_currency_fail() {
        int replenishAmount = 1000;
        Amount amount = Amount.AmountBuilder.anAmount().withValue(replenishAmount).withCurrency(Currency.EUR).build();
        atmService.withdraw(ACCOUNT1_ID, amount);
    }

    @Test(expected = RuntimeException.class)
    public void withdraw_multiple_fail() {
        int replenishAmount = 203;
        Amount amount = Amount.AmountBuilder.anAmount().withValue(replenishAmount).withCurrency(Currency.GBP).build();
        atmService.withdraw(ACCOUNT1_ID, amount);
    }

    @Test
    public void withdraw_notes_with_resplit_successfully() {
        int replenishAmount = 250;
        Amount amount = Amount.AmountBuilder.anAmount().withValue(replenishAmount).withCurrency(Currency.GBP).build();
        List<Notes> notes = atmService.withdraw(ACCOUNT1_ID, amount);

        assertThat(notes).isNotEmpty();

        for (Notes note : notes){
            if(note.getAmount() == 50) {
                assertThat(note.getQuantity()).isEqualTo(4);
            } else if(note.getAmount() == 20) {
                assertThat(note.getQuantity()).isEqualTo(2);
            } else if(note.getAmount() == 5) {
                assertThat(note.getQuantity()).isEqualTo(2);
            }
        }
    }

    @Test
    public void withdraw_notes_without_resplit_successfully() {
        int replenishAmount = 205;
        Amount amount = Amount.AmountBuilder.anAmount().withValue(replenishAmount).withCurrency(Currency.GBP).build();
        List<Notes> notes = atmService.withdraw(ACCOUNT1_ID, amount);

        assertThat(notes).isNotEmpty();

        for (Notes note : notes){
            if(note.getAmount() == 50) {
                assertThat(note.getQuantity()).isEqualTo(4);
            } else if(note.getAmount() == 5) {
                assertThat(note.getQuantity()).isEqualTo(2);
            }
        }
    }
}