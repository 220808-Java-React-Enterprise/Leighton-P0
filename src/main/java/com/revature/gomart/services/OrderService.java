package com.revature.gomart.services;

import com.revature.gomart.daos.OrderDAO;
import com.revature.gomart.models.Order;
import com.revature.gomart.utils.custom_exceptions.*;

import java.util.*;

public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {this.orderDAO = orderDAO;}

    public void createNew(Order order) {orderDAO.save(order);}

    public List<Order> getByUserId(String uid) {
        List<Order> orders = orderDAO.getByUserId(uid);
        return orders;
    }

    public Order retrieve(boolean orderComplete, String userId) {
        Order order = orderDAO.getExistingOrder(orderComplete, userId);
        return order;
    }

    public void updateCost(Order o, int q) {
        orderDAO.updateCost(o,q);
    }

    public int getCost(Order o) {
        int cost = orderDAO.getCost(o);
        return cost;
    }

    public List<Order> getPastOrdersDescending(String uid){
        List<Order> orders = orderDAO.getPreviousOrdersDescending(true, uid);
        return orders;
    }

    public List<Order> getPastOrdersAscending(String uid){
        List<Order> orders = orderDAO.getPreviousOrdersAscending(true, uid);
        return orders;
    }

    public List<Order> getPastOrdersPriceDescending(String uid){
        List<Order> orders = orderDAO.getPreviousOrdersPriceDescending(true, uid);
        return orders;
    }

    public List<Order> getPastOrdersPriceAscending(String uid){
        List<Order> orders = orderDAO.getPreviousOrdersPriceAscending(true, uid);
        return orders;
    }

    public void updateOrderStatus(Order o) {orderDAO.updateOrderStatus(o);}
}
