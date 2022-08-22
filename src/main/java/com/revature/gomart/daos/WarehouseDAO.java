package com.revature.gomart.daos;

import com.revature.gomart.models.Warehouse;
import com.revature.gomart.utils.custom_exceptions.InvalidSQLException;
import com.revature.gomart.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO implements CrudDAO<Warehouse> {
    @Override
    public void save(Warehouse obj) throws IOException {

    }

    @Override
    public void update(Warehouse obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Warehouse getById(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM warehouses WHERE id = ?");
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Warehouse(
                        rs.getString("id"),
                        rs.getString("location"),
                        rs.getString("stock_type")
                );
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("Error connecting to database");
        }
        return null;
    }

    @Override
    public List<Warehouse> getAll() {
        return null;
    }
}
