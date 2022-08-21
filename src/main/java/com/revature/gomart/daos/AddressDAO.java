package com.revature.gomart.daos;

import com.revature.gomart.models.*;
import com.revature.gomart.utils.custom_exceptions.InvalidSQLException;
import com.revature.gomart.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO implements CrudDAO<Address>{
    @Override
    public void save(Address obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, full_name, street1, city, region, user_id) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1,obj.getId());
            ps.setString(2,obj.getFullName());
            ps.setString(3,obj.getStreet());
            ps.setString(4,obj.getCity());
            ps.setString(5,obj.getRegion());
            ps.setString(6,obj.getUser_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("Error. Could not save user to database");
        }
    }

    @Override
    public void update(Address address) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE addresses SET (full_name, street1, city, region) = (?, ?, ?, ?) WHERE id = ?");
            ps.setString(1, address.getFullName());
            ps.setString(2, address.getStreet());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getRegion());
            ps.setString(5, address.getUser_id());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
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
                    rs.getString("street"),
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
