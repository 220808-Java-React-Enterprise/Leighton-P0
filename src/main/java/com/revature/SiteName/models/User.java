package com.revature.SiteName.models;

public class User {
    private String name;
    private String username;
    private String password;
    private String email;
    private String region;
    private String hometown;

    public User() {

    }

    public User(String name, String username, String password, String email, String region, String hometown) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.region = region;
        this.hometown = hometown;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", region='" + region + '\'' +
                ", hometown='" + hometown + '\'' +
                '}';
    }
}
