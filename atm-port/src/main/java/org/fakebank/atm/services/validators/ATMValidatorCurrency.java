package org.fakebank.atm.services.validators;

import org.fakebank.model.Amount;
import org.fakebank.model.Currency;

public class ATMValidatorCurrency implements ATMValidator {

    private static final int VALIDATOR_ORDER = 3;
    private static final Currency VALIDATOR_CURRENCY = Currency.GBP;

    @Override
    public int getOrder(){
        return VALIDATOR_ORDER;
    }

    @Override
    public boolean validate(Amount amount){
        if (!amount.getCurrency().equals(VALIDATOR_CURRENCY)) {
            throw new RuntimeException("The currency is not the same. We can offer you " + VALIDATOR_CURRENCY.name);
        }
        return true;
    }
}
