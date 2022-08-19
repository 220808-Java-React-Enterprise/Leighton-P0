package com.revature.gomart.services;

import com.revature.gomart.daos.ProductDAO;
import com.revature.gomart.models.Product;
import com.revature.gomart.utils.custom_exceptions.*;

import java.util.*;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {this.productDAO = productDAO;}

    public List<Product> getProductsByCategory(String category) {
        List<Product> products = productDAO.getByCategory(category);
        if (products == null) throw new InvalidProductException("No products available");
        return products;
    }


}
