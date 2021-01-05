package br.com.livelo.camunda.dto;

public class TaskLiveloDTO {
    private String taskId;
    private double amount;
    private String id;
    private String assigned;
    private String owner;

    public TaskLiveloDTO(String taskId, double amount, String id, String owner, String assigned){
        this.taskId = taskId;
        this.amount = amount;
        this.id = id;
        this.owner = owner;
        this.assigned = assigned;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }
}
