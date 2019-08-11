package org.fakebank.model;

public class Notes {

    private Currency currency;
    private int amount;
    private int quantity;

    public Currency getCurrency() {
        return currency;
    }

    public int getAmount() {
        return amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public static final class NoteBuilder {
        private Currency currency;
        private int amount;
        private int quantity;

        private NoteBuilder() {
        }

        public static NoteBuilder aNote() {
            return new NoteBuilder();
        }

        public NoteBuilder withCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public NoteBuilder withAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public NoteBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Notes build() {
            Notes notes = new Notes();
            notes.quantity = this.quantity;
            notes.amount = this.amount;
            notes.currency = this.currency;
            return notes;
        }
    }
}
