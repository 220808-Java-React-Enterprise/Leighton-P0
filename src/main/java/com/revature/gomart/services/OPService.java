package com.revature.gomart.services;

import com.revature.gomart.daos.OpDAO;
import com.revature.gomart.models.*;

public class OPService {

    private final OpDAO opDAO;

    public OPService(OpDAO opDAO) {this.opDAO = opDAO;}

    public void saveToOP(Order o, Product p, int q) {opDAO.createNew(o, p, q);}

    public OrderProduct checkForProduct(Order o, Product p) {
        OrderProduct op = opDAO.getByOrderAndProductIds(o, p);
        return op;
    }

    public void addToProduct(OrderProduct op, Product p, int q) {opDAO.update(op, p, q);}
}
