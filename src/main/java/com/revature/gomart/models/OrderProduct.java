package com.revature.gomart.models;

public class OrderProduct {
    private String id;
    private String product_name;
    private int product_price;
    private int product_quantity;
    private String order_id;
    private String product_id;

    public OrderProduct() {

    }

    public OrderProduct(String id, String product_name, int product_price, int product_quantity, String order_id, String product_id) {
        this.id = id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.order_id = order_id;
        this.product_id = product_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id='" + id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                ", product_quantity=" + product_quantity +
                ", order_id='" + order_id + '\'' +
                ", product_id='" + product_id + '\'' +
                '}';
    }
}
