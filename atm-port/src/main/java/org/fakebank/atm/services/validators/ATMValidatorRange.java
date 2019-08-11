package org.fakebank.atm.services.validators;

import org.fakebank.model.Amount;

public class ATMValidatorRange implements ATMValidator {

    private static final int VALIDATOR_ORDER = 1;
    private static final int VALIDATOR_RANGE_MIN = 20;
    private static final int VALIDATOR_RANGE_MAX = 250;

    @Override
    public int getOrder(){
        return VALIDATOR_ORDER;
    }

    @Override
    public boolean validate(Amount amount){
        if(amount.getValue() < VALIDATOR_RANGE_MIN) {
            throw new RuntimeException("The value is too low. Choose more quantity.");
        }
        if(amount.getValue() > VALIDATOR_RANGE_MAX) {
            throw new RuntimeException("The value is too high. Choose a less quantity.");
        }
        return true;
    }
}
