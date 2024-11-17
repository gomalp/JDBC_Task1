package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

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

}
