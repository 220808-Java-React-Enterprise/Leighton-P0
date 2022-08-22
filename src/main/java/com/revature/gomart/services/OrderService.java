package com.revature.gomart.services;

import com.revature.gomart.daos.OrderDAO;
import com.revature.gomart.models.Order;

import java.time.LocalDate;
import java.util.*;

public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {this.orderDAO = orderDAO;}

    public void createNew(Order order) {orderDAO.save(order);}

    public List<Order> getByUserId(String uid) {
        return orderDAO.getByUserId(uid);
    }

    public List<Order> adminGetByUserId(String uid) {
        return orderDAO.adminGetByUserId(uid);
    }

    public Order retrieve(boolean orderComplete, String userId) {
        return orderDAO.getExistingOrder(orderComplete, userId);
    }

    public void updateCost(Order o, int q) {
        orderDAO.updateCost(o,q);
    }

    public int getCost(Order o) {
        return orderDAO.getCost(o);
    }

    public List<Order> getPastOrdersDescending(String uid){
        return orderDAO.getPreviousOrdersDescending(true, uid);
    }

    public List<Order> getPastOrdersAscending(String uid){
        return orderDAO.getPreviousOrdersAscending(true, uid);
    }

    public List<Order> getPastOrdersPriceDescending(String uid){
        return orderDAO.getPreviousOrdersPriceDescending(true, uid);
    }

    public List<Order> getPastOrdersPriceAscending(String uid){
        return orderDAO.getPreviousOrdersPriceAscending(true, uid);
    }

    public void updateOrderStatus(Order o) {orderDAO.updateOrderStatus(o);}

    public String getUserIdById(String oid) {return orderDAO.getUserIdById(oid);}

    public Order getById(String oid) {return orderDAO.getById(oid);}

    public LocalDate getOrderDateById(String oid) {
        return orderDAO.getOrderDateIdById(oid);
    }

    public List<Order> getAll() {return orderDAO.getAll();}
}
