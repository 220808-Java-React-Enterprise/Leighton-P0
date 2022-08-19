package com.revature.gomart.models;

public class Warehouse {
    private String id;
    private String location;
    private String stockType;

    public Warehouse() {

    }

    public Warehouse(String id, String location, String stockType) {
        this.id = id;
        this.location = location;
        this.stockType = stockType;
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

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", stockType='" + stockType + '\'' +
                '}';
    }
}
