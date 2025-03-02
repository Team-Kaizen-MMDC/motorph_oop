/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domain.FullTimeEmployee;
import domain.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author brianjancarlos
 */
public class EmployeeService {
    public static FullTimeEmployee getEmployeeById(int employeeId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM EmployeeDetailsView WHERE employee_id = ?";    // Using the SQL View
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new FullTimeEmployee(
                    rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("birthday"),
                    rs.getString("address"),
                    rs.getString("phone_number"),
                    rs.getString("employment_status"),
                    rs.getString("job_position"),
                    rs.getInt("supervisor_id"),
                    new Role(rs.getInt("emp_role"), Role.getRoleName(rs.getInt("emp_role")))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
