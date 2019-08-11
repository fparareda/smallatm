package org.fakebank.atm.services.validators;

import org.fakebank.model.Amount;

public interface ATMValidator {
    int getOrder();
    boolean validate(Amount amount);
}
