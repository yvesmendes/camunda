package br.com.livelo.camunda.dto;

public class AssignDTO {
    public AssignDTO(String userId){
        this.userId = userId;
    }
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
