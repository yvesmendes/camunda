package br.com.livelo.camunda.dto;

public class VariableDTO {
    private InitDTO init;
    private AmountDTO amount;
    private IDDTO id;

    public InitDTO getInit() {
        return init;
    }

    public void setInit(InitDTO init) {
        this.init = init;
    }

    public AmountDTO getAmount() {
        return amount;
    }

    public void setAmount(AmountDTO amount) {
        this.amount = amount;
    }

    public IDDTO getId() {
        return id;
    }

    public void setId(IDDTO id) {
        this.id = id;
    }
}
