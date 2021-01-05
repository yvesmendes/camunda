package br.com.livelo.camunda.domain;

public class Contract {
    private String id;
    private double amount;

    public double getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setId(String id) {
        this.id = id;
    }
}
