package com.revature.gomart.daos;

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

public class OrderDAO implements CrudDAO<Order>{
    @Override
    public void save(Order obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (id, price, order_date, delivery_type, delivery_date, order_complete, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1,obj.getId());
            ps.setNull(2, java.sql.Types.NULL);
            ps.setNull(3, java.sql.Types.NULL);
            ps.setNull(4, java.sql.Types.NULL);
            ps.setNull(5, java.sql.Types.NULL);
            ps.setBoolean(6, obj.isOrderComplete());
            ps.setString(7, obj.getUser_id());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
    }

    @Override
    public void update(Order obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Order getById(String id) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }

    public Order getExistingOrder(boolean b, String uid) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE order_complete = ? AND user_id = ?");
            ps.setBoolean(1, b);
            ps.setString(2, uid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Order(rs.getString("id"), rs.getBoolean("order_complete"), rs.getString("user_id"));
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }

        return null;
    }
}
