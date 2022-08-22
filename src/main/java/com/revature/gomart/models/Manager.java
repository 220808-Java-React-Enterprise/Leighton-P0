package com.revature.gomart.models;

public class Manager extends User {

    public Manager(){}

    public Manager(String id, String fname, String username, String password, String email){
        this.id = id;
        this.title = "Manager";
        this.fname = fname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.hometown = "Admin";
        this.admin = true;
    }
}
