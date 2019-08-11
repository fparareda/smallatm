# smallatm

Create an interface org.fakebank.atm.services.AccountService which declares;

-  Check balance

-  Withdraw an amount

Create AccountServiceImpl that is aware of the following accounts and balances

-  Account number 01001, Balance 2738.59

-  Account number 01002, Balance 23.00

-  Account number 01003, Balance 0.00

Create ATMServiceImpl and set it up to use the AccountServiceImpl. This should have the following behaviour;

- Replenish:

o Sets up the service with currency notes of denominations 5, 10, 20 and 50

- Check balance:

o Returns a formatted string to display

- Withdraw:

o Returns notes of the correct denominations

o Allow withdrawals between 20 and 250 inclusive, in multiples of 5 o Disburse smallest number of notes

o Always disburse at least one 5 notes, if possible

Assume currency as GBP. It is acceptable to disregard currency for all operations.

# Assumptions
- The account is not related with someone

# For the future
- Create a model to return in the controller
- Create an automated mapper (using mapstruct)
- Create specific exceptions
- Create a real UT (without creation of the accounts and set it into repository). Doing mocks
- Get the notes amount (5, 10, 20 and 50) from repository