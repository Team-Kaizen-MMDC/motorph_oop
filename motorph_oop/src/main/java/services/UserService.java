/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domain.UserAccount;
import domain.EmployeeID;
import java.sql.*;

/**
 *
 * @author brianjancarlos
 */

// Used in LoginFrame
public class UserService {

    public static UserAccount login(String employeeId, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM UserAccounts WHERE employee_id = ? AND emp_password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(employeeId));
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            EmployeeID.empid = Integer.parseInt(employeeId);
            
            if (rs.next()) {
                return new UserAccount(
                        rs.getInt("user_id"),
                        rs.getInt("employee_id"),
                        rs.getString("emp_password"),
                        rs.getInt("emp_role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
