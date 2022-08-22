package com.revature.gomart.daos;

import com.revature.gomart.models.Customer;
import com.revature.gomart.models.Product;
import com.revature.gomart.models.User;
import com.revature.gomart.utils.custom_exceptions.InvalidSQLException;
import com.revature.gomart.utils.database.ConnectionFactory;

import java.io.IOException;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {


    @Override
    public void save(User obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, title, fname, username, password, email, hometown, admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1,obj.getId());
            ps.setString(2,obj.getTitle());
            ps.setString(3,obj.getFname());
            ps.setString(4,obj.getUsername());
            ps.setString(5,obj.getPassword());
            ps.setString(6,obj.getEmail());
            ps.setString(7,obj.getHometown());
            ps.setBoolean(8,obj.getAdmin());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("Error. Could not save user to database");
        }
    }

    @Override
    public void update(User user) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE users SET (title, fname, username, password, email, hometown) = (?, ?, ?, ?, ?, ?) WHERE id = ?");
            ps.setString(1, user.getTitle());
            ps.setString(2, user.getFname());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getHometown());
            ps.setString(7, user.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getById(String id) {
        Customer cust = new Customer();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cust.setId(rs.getString("id"));
                cust.setUsername(rs.getString("username"));
                cust.setTitle(rs.getString("title"));
                cust.setFname(rs.getString("fname"));
                cust.setPassword(rs.getString("password"));
                cust.setEmail(rs.getString("email"));
                cust.setHometown(rs.getString("hometown"));
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
        return cust;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                allUsers.add(new Customer(rs.getString("fname")));
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
        return allUsers;
    }

    public String getUsername(String username) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT (username) FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getString("username");
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
        return null;
    }

    public User getByUsernameAndPassword(String username, String password) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return new Customer(rs.getString("id"), rs.getString("title"), rs.getString("fname"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("hometown"));
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when trying to save to the database.");
        }

        return null;
    }

    public String getEmail(String email) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT (email) FROM users WHERE username = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getString("email");
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
        return null;
    }

    public User getAdmin (String username, String password) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE (username, password, admin) = (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setBoolean(3, true);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return new Customer(rs.getString("id"), rs.getString("title"), rs.getString("fname"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("hometown"));
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when trying to save to the database.");
        }

        return null;
    }
}
