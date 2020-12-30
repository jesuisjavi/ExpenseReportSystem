package com.javierperez.utils;

public class LoginInfo {
    private boolean success;
    private String employeeType;
    private int id;

    public LoginInfo(boolean success, String employeeType, int id) {
        this.success = success;
        this.employeeType = employeeType;
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public int getId() {
        return id;
    }
}
