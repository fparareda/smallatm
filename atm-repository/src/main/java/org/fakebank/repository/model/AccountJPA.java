package org.fakebank.repository.model;

public class AccountJPA extends AuditableJPA {

    private String id;

    private Double value;

    private String currency;

    public AccountJPA(String id, Double value, String currency) {
        this.id = id;
        this.value = value;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
