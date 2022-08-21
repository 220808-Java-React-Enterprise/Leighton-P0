package com.revature.gomart.daos;

import com.revature.gomart.models.*;
import com.revature.gomart.utils.custom_exceptions.InvalidSQLException;
import com.revature.gomart.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        Product product = new Product();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                product.setId(rs.getString("id"));
                product.setItemName(rs.getString("item_name"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getInt("price"));
                product.setStock(rs.getInt("stock"));
                product.setWarehouse_id(rs.getString("warehouse_id"));
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    public List<Product> getByCategory(String category) {
        List<Product> products = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY sort ASC");
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

    public void reduceProductStock(Product product, int quantity) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps1 = con.prepareStatement("UPDATE products SET stock = ? WHERE id = ?");
            ps1.setInt(1, (product.getStock() - quantity));
            ps1.setString(2, product.getId());

            ps1.executeUpdate();

        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
    }
}
