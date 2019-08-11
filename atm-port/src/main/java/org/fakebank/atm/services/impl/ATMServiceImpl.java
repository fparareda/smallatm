package org.fakebank.atm.services.impl;

import org.fakebank.atm.services.ATMService;
import org.fakebank.atm.services.AccountService;
import org.fakebank.atm.services.validators.ATMValidator;
import org.fakebank.model.Account;
import org.fakebank.model.Amount;
import org.fakebank.model.Currency;
import org.fakebank.model.Notes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ATMServiceImpl implements ATMService {

    private static List<Integer> NOTES_AMOUNTS = Arrays.asList(50, 20, 10, 5);
    private static double MUST_NOTE_VALUE = 5;

    private AccountService accountService;
    private List<ATMValidator> validators;

    public ATMServiceImpl(AccountService accountService, List<ATMValidator> validators) {
        this.accountService = accountService;
        this.validators = validators.stream()
                .sorted(Comparator.comparingInt(ATMValidator::getOrder))
                .collect(Collectors.toList());
    }

    @Override
    public Account replenish(String accountId, Amount amount) {
        return accountService.addBalance(accountId, amount);
    }

    @Override
    public String checkBalance(String accountId) {
        return String.valueOf(accountService.checkBalance(accountId).getBalance());
    }

    @Override
    public List<Notes> withdraw(String accountId, Amount amount) {
        validators.forEach(validator -> validator.validate(amount));
        accountService.withdrawAmount(accountId, amount);
        return calculateNotes(amount);
    }

    private List<Notes> calculateNotes(Amount amount) {
        List<Notes> notes = splitNotes(amount);
        if(notes.stream()
                .map(Notes::getAmount)
                .anyMatch(amountNote -> amountNote != MUST_NOTE_VALUE)) {
            return reSplitLastNote(amount);
        }
        return notes;
    }

    private List<Notes> splitNotes(Amount amount) {
        List<Notes> notes = new ArrayList<>();
        double amountToSplit = amount.getValue();
        for(Integer noteAmount : NOTES_AMOUNTS){
            int quantity = (int) (amountToSplit / noteAmount);
            if(quantity > 0) {
                notes.add(createNotes(noteAmount, quantity, amount.getCurrency()));
            }
            amountToSplit = amountToSplit % noteAmount;
        }
        return notes;
    }

    private List<Notes> reSplitLastNote(Amount amount) {
        List<Notes> notes = new ArrayList<>();
        List<Notes> notesWithOneNote = splitNotes(
                Amount.AmountBuilder.anAmount()
                        .withCurrency(amount.getCurrency())
                        .withValue(amount.getValue() - MUST_NOTE_VALUE)
                        .build()
        );

        for(Notes notesCalculated : notesWithOneNote) {
            if(notesCalculated.getAmount() == MUST_NOTE_VALUE) {
                notes.add(Notes.NoteBuilder.aNote()
                        .withAmount(notesCalculated.getAmount())
                        .withCurrency(notesCalculated.getCurrency())
                        .withQuantity(notesCalculated.getQuantity() + 1)
                        .build()
                );
            } else {
                notes.add(notesCalculated);
            }
        }
        return notes;
    }

    private Notes createNotes(Integer noteAmount, Integer quantity, Currency currency){
        return Notes.NoteBuilder.aNote()
                .withAmount(noteAmount)
                .withCurrency(currency)
                .withQuantity(quantity)
                .build();
    }
}
