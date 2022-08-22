package com.revature.gomart.services;

import com.revature.gomart.daos.OpDAO;
import com.revature.gomart.models.*;

import java.util.List;

public class OPService {

    private final OpDAO opDAO;

    public OPService(OpDAO opDAO) {this.opDAO = opDAO;}

    public void saveToOP(Order o, Product p, int q) {opDAO.createNew(o, p, q);}

    public OrderProduct checkForProduct(Order o, Product p) {
        return opDAO.getByOrderAndProductIds(o, p);
    }

    public void addToProduct(OrderProduct op, Product p, int q) {opDAO.addQuantity(op, p, q);}

    public List<OrderProduct> getOrderProducts(String id) {
        return opDAO.getByOrderId(id);
    }

    public List<OrderProduct> getProductOrders(String id) {
        return opDAO.getByProductId(id);
    }
}
