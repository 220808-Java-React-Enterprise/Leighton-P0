package com.revature.gomart.daos;

import com.revature.gomart.models.*;
import com.revature.gomart.utils.custom_exceptions.InvalidSQLException;
import com.revature.gomart.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OpDAO implements CrudDAO<OrderProduct>{
    @Override
    public void save(OrderProduct op) {

    }

    @Override
    public void update(OrderProduct obj) {

    }

    public void createNew(Order order, Product product, int quantity) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO order_products (id, product_name, product_price, product_quantity, order_id, product_id) VALUES (?,?,?,?,?,?)");
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, product.getItemName());
            ps.setInt(3, product.getPrice());
            ps.setInt(4, quantity);
            ps.setString(5, order.getId());
            ps.setString(6, product.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
    }

    public void addQuantity(OrderProduct op, Product product, int quantity) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE order_products SET product_quantity = ? WHERE product_id = ?");
            ps.setInt(1, Integer.sum(op.getProduct_quantity(), quantity));
            ps.setString(2, product.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public OrderProduct getById(String id) {
        return null;
    }

    @Override
    public List<OrderProduct> getAll() {
        return null;
    }

    public List<OrderProduct> getByOrderId(String orderId) {
        List<OrderProduct> ops = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM order_products WHERE order_id = ?");
            ps.setString(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderProduct p = new OrderProduct(
                        rs.getString("id"),
                        rs.getString("product_name"),
                        rs.getInt("product_price"),
                        rs.getInt("product_quantity"),
                        rs.getString("order_id"),
                        rs.getString("product_id")
                );
                ops.add(p);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
        return ops;
    }

    public OrderProduct getByOrderAndProductIds(Order order, Product product) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM order_products WHERE order_id = ? AND product_id = ?");
            ps.setString(1, order.getId());
            ps.setString(2, product.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return new OrderProduct(
                        rs.getString("id"),
                        rs.getString("product_name"),
                        rs.getInt("product_price"),
                        rs.getInt("Product_quantity"),
                        rs.getString("order_id"),
                        rs.getString("product_id")
                        );
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when trying to save to the database.");
        }

        return null;
    }

}
