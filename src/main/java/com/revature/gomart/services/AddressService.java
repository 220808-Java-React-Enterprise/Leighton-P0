package com.revature.gomart.services;

import com.revature.gomart.daos.AddressDAO;
import com.revature.gomart.models.*;

public class AddressService {

    private final AddressDAO addressDAO;

    public AddressService(AddressDAO addressDAO) {this.addressDAO = addressDAO;}

    public void createNew(Address address) {addressDAO.save(address);}

    public Address retrieve(String uid) {
        Address addy = addressDAO.getByUserId(uid);
        return addy;
    }
}
