package com.demo.em.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect{    
    private static final String URL = "jdbc:mysql://localhost:3306/employeedemo";
    private static final String USER = "root"; // change if needed
    private static final String PASSWORD = ""; // your MySQL password
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

