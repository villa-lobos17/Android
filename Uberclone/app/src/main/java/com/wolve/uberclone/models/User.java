package com.wolve.uberclone.models;

public class User {
    String Id;
    String User;
    String Email;

    public User(String id, String user, String email) {
        Id = id;
        User = user;
        Email = email;
    }
    public User(){}

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
