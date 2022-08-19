package com.revature.gomart.daos;

import com.revature.gomart.models.Product;
import com.revature.gomart.utils.custom_exceptions.InvalidSQLException;
import com.revature.gomart.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<Product> getByCategory(String category) {
        List<Product> products = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE category = ?");
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product(
                        rs.getString("id"),
                        rs.getString("item_name"),
                        rs.getString("category"),
                        rs.getInt("price"),
                        rs.getInt("stock"),
                        rs.getString("warehouse_id")
                );
                products.add(p);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
        return products;
    }
}
