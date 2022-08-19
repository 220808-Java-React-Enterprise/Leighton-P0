package com.revature.gomart.daos;

import com.revature.gomart.models.Product;
import com.revature.gomart.models.Order;
import com.revature.gomart.utils.custom_exceptions.InvalidSQLException;
import com.revature.gomart.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDAO implements CrudDAO<Object>{
    @Override
    public void save(Object obj) throws IOException {

    }

    @Override
    public void update(Object obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Object getById(String id) {
        return null;
    }

    @Override
    public List<Object> getAll() {
        return null;
    }
}
