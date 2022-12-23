package edu.bzu.assignmentone.bzutodo.models;

public class TaskModel {

    private String taskTitle;
    private String soleganText;
    private String completeTaskDate;

    public TaskModel(String taskTitle, String soleganText, String completeTaskDate) {
        this.taskTitle = taskTitle;
        this.soleganText = soleganText;
        this.completeTaskDate = completeTaskDate;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getSoleganText() {
        return soleganText;
    }

    public void setSoleganText(String soleganText) {
        this.soleganText = soleganText;
    }

    public String getCompleteTaskDate() {
        return completeTaskDate;
    }

    public void setCompleteTaskDate(String completeTaskDate) {
        this.completeTaskDate = completeTaskDate;
    }
}
