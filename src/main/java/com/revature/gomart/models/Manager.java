package com.revature.gomart.models;

public class Manager extends User {

    public Manager(){}

    public Manager(String id, String name, String username, String password, String email){
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
