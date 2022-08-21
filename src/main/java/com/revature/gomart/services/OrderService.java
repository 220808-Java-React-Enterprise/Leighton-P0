package com.revature.gomart.services;

import com.revature.gomart.daos.OrderDAO;
import com.revature.gomart.models.Order;
import com.revature.gomart.utils.custom_exceptions.*;

import java.util.*;

public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {this.orderDAO = orderDAO;}

    public void createNew(Order order) {orderDAO.save(order);}

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

    public List<Order> getPastOrders(String uid){
        List<Order> orders = orderDAO.getPreviousOrders(true, uid);
        return orders;
    }

    public void completeOrder(Order o) {orderDAO.completeOrder(o);}
}
