package com.kutay.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Kutay on 30.10.2016.
 */
public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static void buildSessionFactory()
    {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
    public static SessionFactory getInstance()
    {
        return sessionFactory;
    }

    public Session openSession()
    {
        return sessionFactory.openSession();
    }

    public Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public static void close()
    {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
