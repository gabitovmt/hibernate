package com.example.model.naming;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity/*(name = "AnotherSystemUser")*/
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
