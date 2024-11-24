package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Properties;

public final class Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    private static final SessionFactory sessionFactory=buildSessionFactory();

    private Util(){
    }
    // Метод для получения подключения
    public static Connection getConnection() {
        try {
             Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (ClassNotFoundException e) {

            e.printStackTrace();
            throw new RuntimeException("PostgreSQL load driver error ", e);
        }
        catch (SQLException  e) {
            throw new RuntimeException("PostgreSQL connect error ", e);
        }
    }

    private static SessionFactory buildSessionFactory() {
            try{
                return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

            } catch  (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
