package com.javierperez.workplace;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
    @Column(name="FIRSTNAME")
    protected String firstName;
    @Column(name="LASTNAME")
    protected String lastName;
    @Column(name="DOB")
    protected String dob;
    @Column(name="USERNAME")
    protected String username;
    @Column(name="PASSWORD")
    protected String password;
    @Column(name="JOBPOSITION")
    protected String jobPosition;
    @Column(name="approvallimit")
    protected double approvallimit;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    public Employee(String firstName, String lastName, String dob, String username, String password, String jobPosition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.username = username;
        this.password = password;
        this.jobPosition = jobPosition;
    }

    public Employee() {

    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public double getApprovallimit() {
        return approvallimit;
    }

    public void setApprovallimit(double approvallimit) {
        this.approvallimit = approvallimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.approvallimit, approvallimit) == 0 &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName) &&
                dob.equals(employee.dob) &&
                username.equals(employee.username) &&
                password.equals(employee.password) &&
                jobPosition.equals(employee.jobPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dob, username, password, jobPosition, approvallimit);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
