package com.revature.gomart.daos;

import com.revature.gomart.models.User;

import java.io.IOException;

import java.io.*;
import java.util.List;

public class UserDAO implements CrudDAO<User> {
    String path = "src/main/resources/db/user.txt";


    @Override
    public void save(User obj) {
        try {
            File file = new File(path);
            FileWriter fw = new FileWriter(file, true);
            fw.write(obj.toFileString());
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException("\nError writing file.");
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
