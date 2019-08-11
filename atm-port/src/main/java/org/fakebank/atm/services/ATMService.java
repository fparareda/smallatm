package org.fakebank.atm.services;

import org.fakebank.model.Account;
import org.fakebank.model.Amount;
import org.fakebank.model.Notes;

import java.util.List;

public interface ATMService {
    Account replenish(String accountId, Amount amount);
    String checkBalance(String accountId);
    List<Notes> withdraw(String accountId, Amount amount);
}
