package com.kutay.service;

import com.kutay.dao.UserRepository;
import com.kutay.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Kutay on 28.10.2016.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void create(User k) {
        userRepository.create(k);
    }

    @Override
    public User getNote(BigDecimal id) {
        return userRepository.getNote(id);
    }

    @Override
    public void editNote(User u){
        userRepository.editNote(u);
    }
}
