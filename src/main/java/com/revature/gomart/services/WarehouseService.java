package com.revature.gomart.services;

import com.revature.gomart.daos.WarehouseDAO;
import com.revature.gomart.models.Warehouse;
import com.revature.gomart.utils.custom_exceptions.*;

import java.util.*;

public class WarehouseService {

    private final WarehouseDAO whDAO;

    public WarehouseService(WarehouseDAO whDAO) {this.whDAO = whDAO;}

    public Warehouse getById(String whid) {
        return whDAO.getById(whid);
    }
}
