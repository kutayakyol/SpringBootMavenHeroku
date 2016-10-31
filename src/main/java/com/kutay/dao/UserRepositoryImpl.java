package com.kutay.dao;

import com.kutay.entities.Note;
import com.kutay.entities.User;
import com.kutay.util.SessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kutay on 28.10.2016.
 */
@Repository("UserRepository")
public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> getAll() {
        List<User> lst = new ArrayList<User>();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            lst = s.createCriteria(User.class).list();

            s.getTransaction().commit();




        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }



        return lst;

    }
    @Override
    public void create(User k) {
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            s.save(k);
            s.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }

    }

    @Override
    public List<Note> getAllNotes(BigDecimal id) {
        List<Note> lst = new ArrayList<Note>();

        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            String SQL_QUERY = "from com.kutay.entities.Note as a where a.id in (select kr.noteId from com.kutay.entities.User kr where kr.id=?)";
            Query query = s.createQuery(SQL_QUERY);//.setString("kullaniciadi", userName);
            query.setParameter(0, id);
            lst = query.list();

            s.getTransaction().commit();




        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }



        return lst;

    }
    @Override
    public void createNote(Note note) {
        SessionFactoryUtil.buildSessionFactory();
        Session s = null;
        Transaction tx = null;
        s = SessionFactoryUtil.getInstance().openSession();
        try {
            s.beginTransaction();
            s.save(note);
            s.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        } finally {
            s.close();
            SessionFactoryUtil.close();
        }

    }
}
