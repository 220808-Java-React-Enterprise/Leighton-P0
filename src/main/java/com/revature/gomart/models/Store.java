package com.revature.gomart.models;

import java.util.HashMap;

public class Store {
    private String id;
    private String location;
    private HashMap<String, Integer> inventory;

    public Store(){}

    public Store(String location){
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
