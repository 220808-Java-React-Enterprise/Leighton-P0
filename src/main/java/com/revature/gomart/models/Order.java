package com.revature.gomart.models;

import java.util.HashMap;
import java.time.*;

public class Order {
    private String id;
    private String customer;
    private HashMap<String, Integer> items;
    private int tot_cost;
    private LocalDate date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Integer> items) {
        this.items = items;
    }

    public int getTot_cost() {
        return tot_cost;
    }

    public void setTot_cost(int tot_cost) {
        this.tot_cost = tot_cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customer='" + customer + '\'' +
                ", items=" + items +
                ", tot_cost=" + tot_cost +
                '}';
    }
}
