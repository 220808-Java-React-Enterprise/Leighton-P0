package com.revature.gomart.daos;

import com.revature.gomart.models.User;
import com.revature.gomart.utils.custom_exceptions.InvalidSQLException;
import com.revature.gomart.utils.database.ConnectionFactory;

import java.io.IOException;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements CrudDAO<User> {
    String path = "src/main/resources/db/user.txt";


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
            ps.setString(8,obj.getAdmin().toString());

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when trying to save to the database.");
        }
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
