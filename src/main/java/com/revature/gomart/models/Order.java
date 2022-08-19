package com.revature.gomart.models;

import java.util.HashMap;
import java.time.*;

public class Order {
    private String id;
    private int tot_price;
    private LocalDate orderDate;
    private String deliveryType;
    private LocalDate deliveryDate;
    private String user_id;

    public Order() {

    }

    public Order(String id, int tot_price, LocalDate orderDate, String deliveryType, LocalDate deliveryDate, String user_id) {
        this.id = id;
        this.tot_price = tot_price;
        this.orderDate = orderDate;
        this.deliveryType = deliveryType;
        this.deliveryDate = deliveryDate;
        this.user_id =  user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTot_price() {
        return tot_price;
    }

    public void setTot_price(int tot_price) {
        this.tot_price = tot_price;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", tot_price=" + tot_price +
                ", orderDate=" + orderDate +
                ", deliveryType='" + deliveryType + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
