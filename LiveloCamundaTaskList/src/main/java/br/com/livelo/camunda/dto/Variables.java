package br.com.livelo.camunda.dto;

public class Variables{
    private AVariable firstName;
    private AnotherVariable approved;

    public AnotherVariable getApproved() {
        return approved;
    }

    public void setApproved(AnotherVariable ok) {
        this.approved = ok;
    }

    public void setFirstName(AVariable firstName) {
        this.firstName = firstName;
    }

    public AVariable getFirstName() {
        return firstName;
    }
}