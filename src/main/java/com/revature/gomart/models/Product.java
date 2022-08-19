package com.revature.gomart.models;

public class Product {
    private String id;
    private String itemName;
    private String category;
    private int price;
    private int stock;
    private String warehouse_id;

    public Product() {

    }

    public Product(String id, String itemName, String category, int price, int stock, String warehouse_id) {
        this.id = id;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.warehouse_id = warehouse_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", warehouse_id='" + warehouse_id + '\'' +
                '}';
    }
}
