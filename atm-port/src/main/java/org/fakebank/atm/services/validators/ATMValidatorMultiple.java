package org.fakebank.atm.services.validators;

import org.fakebank.model.Amount;

public class ATMValidatorMultiple implements ATMValidator {

    private static final int VALIDATOR_ORDER = 2;
    private static final int VALIDATOR_MULTIPLE = 5;

    @Override
    public int getOrder(){
        return VALIDATOR_ORDER;
    }

    @Override
    public boolean validate(Amount amount){
        if (amount.getValue() % VALIDATOR_MULTIPLE != 0) {
            throw new RuntimeException("The value is not multiple with " + VALIDATOR_MULTIPLE);
        }
        return true;
    }
}
