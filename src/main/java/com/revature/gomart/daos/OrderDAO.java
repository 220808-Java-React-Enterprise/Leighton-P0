package com.revature.gomart.daos;

import com.revature.gomart.models.Order;
import com.revature.gomart.utils.custom_exceptions.InvalidSQLException;
import com.revature.gomart.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
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
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new Order(
                        rs.getString("id"),
                        rs.getInt("price"),
                        rs.getDate("order_date").toLocalDate(),
                        rs.getString("delivery_type"),
                        rs.getDate("delivery_date").toLocalDate(),
                        rs.getBoolean("order_complete"),
                        rs.getString("user_id")
                );
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }

        return null;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE order_complete = ?");
            ps.setBoolean(1,true);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = new Order(
                        rs.getString("id"),
                        rs.getInt("price"),
                        rs.getDate("order_date").toLocalDate(),
                        rs.getString("delivery_type"),
                        rs.getDate("delivery_date").toLocalDate(),
                        rs.getBoolean("order_complete"),
                        rs.getString("user_id")
                );
                orders.add(o);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }

        return orders;
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

    public List<Order> getPreviousOrdersDescending(boolean b, String uid) {
        List<Order> orders = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE (order_complete, user_id) = (?, ?) ORDER BY order_date DESC");
            ps.setBoolean(1, b);
            ps.setString(2, uid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = new Order(
                    rs.getString("id"),
                    rs.getInt("price"),
                    rs.getDate("order_date").toLocalDate(),
                    rs.getString("delivery_type"),
                    rs.getDate("delivery_date").toLocalDate(),
                    rs.getBoolean("order_complete"),
                    rs.getString("user_id")
                );
                orders.add(o);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }

        return orders;
    }

    public List<Order> getPreviousOrdersAscending(boolean b, String uid) {
        List<Order> orders = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE (order_complete, user_id) = (?, ?) ORDER BY order_date ASC");
            ps.setBoolean(1, b);
            ps.setString(2, uid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = new Order(
                        rs.getString("id"),
                        rs.getInt("price"),
                        rs.getDate("order_date").toLocalDate(),
                        rs.getString("delivery_type"),
                        rs.getDate("delivery_date").toLocalDate(),
                        rs.getBoolean("order_complete"),
                        rs.getString("user_id")
                );
                orders.add(o);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }

        return orders;
    }

    public List<Order> getPreviousOrdersPriceDescending(boolean b, String uid) {
        List<Order> orders = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE (order_complete, user_id) = (?, ?) ORDER BY price DESC");
            ps.setBoolean(1, b);
            ps.setString(2, uid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = new Order(
                        rs.getString("id"),
                        rs.getInt("price"),
                        rs.getDate("order_date").toLocalDate(),
                        rs.getString("delivery_type"),
                        rs.getDate("delivery_date").toLocalDate(),
                        rs.getBoolean("order_complete"),
                        rs.getString("user_id")
                );
                orders.add(o);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }

        return orders;
    }

    public List<Order> getPreviousOrdersPriceAscending(boolean b, String uid) {
        List<Order> orders = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE (order_complete, user_id) = (?, ?) ORDER BY price ASC");
            ps.setBoolean(1, b);
            ps.setString(2, uid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = new Order(
                        rs.getString("id"),
                        rs.getInt("price"),
                        rs.getDate("order_date").toLocalDate(),
                        rs.getString("delivery_type"),
                        rs.getDate("delivery_date").toLocalDate(),
                        rs.getBoolean("order_complete"),
                        rs.getString("user_id")
                );
                orders.add(o);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }

        return orders;
    }

    public void updateCost(Order order, int quantity) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE orders SET price = ? WHERE id = ?");
            ps.setInt(1, quantity);
            ps.setString(2, order.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
    }

    public int getCost(Order order) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT price FROM orders WHERE id = ?");
            ps.setString(1, order.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt("price");
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
        return 0;
    }

    public void updateOrderStatus (Order order) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE orders SET (order_date, delivery_type, delivery_date, order_complete) = (?, ?, ?, ?) WHERE id = ?");
            ps.setDate(1, Date.valueOf(order.getOrderDate()));
            ps.setString(2, order.getDeliveryType());
            ps.setDate(3, Date.valueOf(order.getDeliveryDate()));
            ps.setBoolean(4, order.isOrderComplete());
            ps.setString(5, order.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
    }

    public List<Order> getByUserId(String uid) {
        List<Order> orders = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE user_id = ?");
            ps.setString(1, uid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = new Order(
                        rs.getString("id"),
                        rs.getBoolean("order_complete"),
                        rs.getString("user_id")
                );
                orders.add(o);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }

        return orders;
    }

    public List<Order> adminGetByUserId(String uid) {
        List<Order> orders = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE user_id = ? and order_complete = ?");
            ps.setString(1, uid);
            ps.setBoolean(2, true);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = new Order(
                        rs.getString("id"),
                        rs.getInt("price"),
                        rs.getDate("order_date").toLocalDate(),
                        rs.getString("delivery_type"),
                        rs.getDate("delivery_date").toLocalDate(),
                        rs.getBoolean("order_complete"),
                        rs.getString("user_id")
                );
                orders.add(o);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }

        return orders;
    }

    public String getUserIdById(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT user_id FROM orders WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("user_id");
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }

        return null;
    }

    public LocalDate getOrderDateIdById(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT order_date FROM orders WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDate("order_date").toLocalDate();
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }

        return null;
    }

}
