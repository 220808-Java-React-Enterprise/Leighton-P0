package com.revature.gomart.models;

import java.time.*;
import java.util.Random;

public class Order {
    private String id;
    private int tot_price;
    private LocalDate orderDate;
    private String deliveryType;
    private LocalDate deliveryDate;

    private boolean orderComplete;
    private String user_id;

    public Order(String id, int tot_price, LocalDate orderDate, String deliveryType, LocalDate deliveryDate, boolean orderComplete, String user_id) {
        this.id = id;
        this.tot_price = tot_price;
        this.orderDate = orderDate;
        this.deliveryType = deliveryType;
        this.deliveryDate = deliveryDate;
        this.user_id =  user_id;
        this.orderComplete = orderComplete;
    }

    public Order(String id, String user_id) {
        this.id = id;
        this.user_id = user_id;
        this.orderComplete = false;
    }

    public Order(boolean orderComplete, String user_id) {
        this.orderComplete = orderComplete;
        this.user_id = user_id;
    }

    public Order(String id, boolean orderComplete, String user_id) {
        this.id = id;
        this.orderComplete = orderComplete;
        this.user_id = user_id;
    }

    public static String generateId() {
        final String lowers = "abcdefghijklmnopqrstuvwxyz";
        final String uppers = lowers.toUpperCase();
        final String nums = "1234567890";
        final String letsnums = lowers + uppers + nums;
        char[] charset = letsnums.toCharArray();
        char[] result = new char[10];

        Random random = new Random();

        for (int i = 0; i < result.length; i++) {
            int r = random.nextInt(charset.length);
            result[i] = charset[r];
        }
        StringBuilder sb = new StringBuilder();
        for (char c : result) {
            sb.append(c);
        }
        return sb.toString();
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

    public boolean isOrderComplete() {
        return orderComplete;
    }

    public void setOrderComplete(boolean orderComplete) {
        this.orderComplete = orderComplete;
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
                ", tot_price=" + tot_price + '\'' +
                ", orderDate=" + orderDate + '\'' +
                ", deliveryType='" + deliveryType + '\'' +
                ", deliveryDate=" + deliveryDate + '\'' +
                ", orderComplete=" + orderComplete + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
