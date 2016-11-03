package com.kutay.service;

import com.kutay.entities.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Kutay on 28.10.2016.
 */
public interface UserService {

    List<User> getAll() ;
    void create(User user);
    User getNote(BigDecimal id) ;
    void editNote(User u);
}
