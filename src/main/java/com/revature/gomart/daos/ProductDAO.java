package com.revature.gomart.daos;

import com.revature.gomart.models.Product;

import java.io.IOException;
import java.util.List;

public class ProductDAO implements CrudDAO<Product>{

    @Override
    public void save(Product obj) throws IOException {

    }

    @Override
    public void update(Product obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Product getById(String id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    public List<Product> getByCategory() {return null;}
}
