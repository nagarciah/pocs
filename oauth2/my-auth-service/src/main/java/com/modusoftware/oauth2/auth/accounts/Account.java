package com.modusoftware.oauth2.auth.accounts;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account implements Serializable {

    private static final long serialVersionUID = -4387253258245533713L;

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private boolean active;

    public Account(){}
    
    public Account(String username, String password, boolean active) {
        super();
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Account [id=");
        builder.append(id);
        builder.append(", username=");
        builder.append(username);
        builder.append(", password=");
        builder.append(password);
        builder.append(", active=");
        builder.append(active);
        builder.append("]");
        return builder.toString();
    }
}
