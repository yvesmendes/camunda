package br.com.livelo.camunda.dto;

import java.util.ArrayList;
import java.util.List;

public class CompleteVariablesDTO {
    private List<CompleteDTO> variables;

    public CompleteVariablesDTO(String firstname){
        variables = new ArrayList<>();
        variables.add(new CompleteDTO(firstname));
    }

    public List<CompleteDTO> getVariables() {
        return variables;
    }

    public void setVariables(List<CompleteDTO> variables) {
        this.variables = variables;
    }
}
