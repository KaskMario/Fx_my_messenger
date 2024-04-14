package com.example.fx_my_messenger;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    static Connection connection;
    private Connect() {}

    public static Connection createConnection () {
        final String URL = "jdbc:mysql://localhost/messages";
        final String PASSWORD = "0104";
        final String USER = "root";

        connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;

    }
    public static void closeDb () {
        if(connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }





}





