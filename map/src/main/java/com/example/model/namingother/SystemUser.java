package com.example.model.namingother;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "OtherSystemUser")
public class SystemUser {
    @Id
    @GeneratedValue
    protected Long id;
    protected String emailAddress;

    public SystemUser() {
    }
    public SystemUser(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getId() {
        return id;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "SystemUser{" +
                "id=" + id +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
