package com.kutay.dao;

import com.kutay.entities.Note;
import com.kutay.entities.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Kutay on 28.10.2016.
 */
public interface UserRepository {

    List<User> getAll() ;
    void create(User user);
    List<Note> getAllNotes(BigDecimal id) ;
    void createNote (Note note);
}