package com.revature.SiteName.models;

public class Product {
    private String id;
    private String name;
    private int price;
    private int stock;

    public Product() {

    }

    public Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id +
                ", name='" + name + '\'' +
                ", price=" + price + '\'' +
                ", inventory=" + stock +
                '}';
    }
}
