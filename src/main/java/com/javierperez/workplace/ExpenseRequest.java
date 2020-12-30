package com.javierperez.workplace;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="EXPENSEREQUEST")
public class ExpenseRequest {
    @Column(name="dob")
    private String dob;
    @Column(name="AMOUNT")
    private double amount;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="STATUS")
    private String status;
    @Column(name="REQUESTERID")
    private int requesterId;
    @Column(name="RESOLVER")
    private String resolver;
    @Column(name="INFO")
    private String info;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    public ExpenseRequest(String dob, double amount, String description, String status, int requesterId, String resolver, String info) {
        this.dob = dob;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.requesterId = requesterId;
        this.resolver = resolver;
        this.info = info;
    }

    public ExpenseRequest() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(int requesterId) {
        this.requesterId = requesterId;
    }

    public String getResolver() {
        return resolver;
    }

    public void setResolver(String resolver) {
        this.resolver = resolver;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseRequest that = (ExpenseRequest) o;
        return Double.compare(that.amount, amount) == 0 &&
                requesterId == that.requesterId &&
                dob.equals(that.dob) &&
                description.equals(that.description) &&
                status.equals(that.status) &&
                resolver.equals(that.resolver) &&
                info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dob, amount, description, status, requesterId, resolver, info);
    }
}
