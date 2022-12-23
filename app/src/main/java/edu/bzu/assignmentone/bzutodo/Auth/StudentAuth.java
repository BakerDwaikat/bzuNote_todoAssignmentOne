package edu.bzu.assignmentone.bzutodo.Auth;

public class StudentAuth {

    private int studentID;
    private String username;
    private String password;

    public StudentAuth(int studentID, String username, String password) {
        this.studentID = studentID;
        this.username = username;
        this.password = password;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
