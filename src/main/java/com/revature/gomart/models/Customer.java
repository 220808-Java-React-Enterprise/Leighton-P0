package com.revature.gomart.models;

public class Customer extends User {

    public Customer(){this.admin = false;}

    public Customer(String id) {
        this.id = id;
    }

    public Customer(String id, String title, String fname, String username, String password, String email, String hometown) {
        this.id = id;
        this.title = title;
        this.fname = fname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.hometown = hometown;
        this.admin = false;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", id='" + id + '\'' +
                "title='" + title + '\'' +
                ", fname='" + fname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", hometown='" + hometown + '\'' +
                '}';
    }
}
