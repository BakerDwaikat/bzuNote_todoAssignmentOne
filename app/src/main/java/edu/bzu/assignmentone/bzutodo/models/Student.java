package edu.bzu.assignmentone.bzutodo.models;

public class Student {

    private int studentID;
    private String studentName;
    private int studentPhone;
    private int studentAge;
    private String studentAddress;
    private String studentGender;

    public Student(int studentID, String studentName, int studentPhone, int studentAge, String studentAddress, String studentGender) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.studentAge = studentAge;
        this.studentAddress = studentAddress;
        this.studentGender = studentGender;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(int studentPhone) {
        this.studentPhone = studentPhone;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }
}
