package com.javierperez.workplace;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="EMAIL")
public class Email {
    @Column(name="dob")
    private String dob;
    @Column(name="SENDER")
    private String sender;
    @Column(name="RECEIVERID")
    private int receiverId;
    @Column(name="SUBJECT")
    private String subject;
    @Column(name="BODY")
    private String body;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    public Email(String dob, String sender, int receiverId, String subject, String body) {
        this.dob = dob;
        this.sender = sender;
        this.receiverId = receiverId;
        this.subject = subject;
        this.body = body;
    }

    public Email() {

    }

    public String getSender() {
        return sender;
    }

    public void setSender(int senderId) {
        this.sender = sender;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return receiverId == email.receiverId &&
                dob.equals(email.dob) &&
                sender.equals(email.sender) &&
                subject.equals(email.subject) &&
                body.equals(email.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dob, sender, receiverId, subject, body);
    }
}
