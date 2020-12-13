package org.example;

import java.sql.*;

public class ConnectionToDB {
    private static String url = "jdbc:mysql://localhost:3306/testsql?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static String username = "root";
    private static String password = "123";
    private static Connection connection;

    public static Connection createNewConnection() {
        try  {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Cannot create database connection");
            e.printStackTrace();
        } finally {
            return connection;
        }
    }
}
