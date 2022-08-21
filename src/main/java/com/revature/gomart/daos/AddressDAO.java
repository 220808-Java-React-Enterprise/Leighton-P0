package com.revature.gomart.daos;

import com.revature.gomart.models.*;
import com.revature.gomart.utils.custom_exceptions.InvalidSQLException;
import com.revature.gomart.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressDAO implements CrudDAO<Address>{
    @Override
    public void save(Address obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, full_name, street1, street2, city, region, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1,obj.getId());
            ps.setString(2,obj.getFullName());
            ps.setString(3,obj.getStreet1());
            ps.setString(4,obj.getStreet2());
            ps.setString(5,obj.getCity());
            ps.setString(6,obj.getRegion());
            ps.setString(7,obj.getUser_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("Error. Could not save user to database");
        }
    }

    @Override
    public void update(Address obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Address getById(String id) {
        return null;
    }

    @Override
    public List<Address> getAll() {
        return null;
    }

    public Address getByUserId(String uid) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM addresses WHERE user_id = ?");
            ps.setString(1,uid);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return new Address(
                    rs.getString("id"),
                    rs.getString("full_name"),
                    rs.getString("street1"),
                    rs.getString("street2"),
                    rs.getString("city"),
                    rs.getString("region"),
                    rs.getString("user_id")
                );
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error. Could not save user to database");
        }
        return null;
    }
}
