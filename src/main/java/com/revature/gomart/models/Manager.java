package com.revature.gomart.models;

public class Manager extends User {

    public Manager(){}

    public Manager(String id, String fname, String username, String password, String email){
        this.id = id;
        this.fname = fname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = true;
    }
}
