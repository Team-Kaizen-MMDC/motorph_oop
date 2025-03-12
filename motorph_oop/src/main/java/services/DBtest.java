/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author brianjancarlos
 */
public class DBtest {

 private static final String URL = "jdbc:postgresql://localhost:5432/Motorph_oop"; 
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "postgres"; 

    public static void main(String[] args) {
        System.out.println("Testing database connection...");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (conn != null) {
                System.out.println("Database connection successful!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
}
