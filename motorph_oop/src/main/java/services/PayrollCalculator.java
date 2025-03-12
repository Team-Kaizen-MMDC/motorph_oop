/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domain.Employee;
import domain.FullTimeEmployee;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import domain.Role;
/**
 *
 * @author brianjancarlos
 */

// Interface: Defines tax calculation behavior
public class PayrollCalculator {
    
      public static Payroll computePayroll(Connection conn, int employeeId, Date startDate, Date endDate) throws SQLException {
        String employeeQuery = "SELECT * FROM public.employee_details WHERE employee_id=?";
        
        try (PreparedStatement pst = conn.prepareStatement(employeeQuery)) {
            pst.setInt(1, employeeId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                 
                      //Ensure role is correctly retrieved
                String roleName = rs.getString("role"); // If column is "role"
                Role role = (roleName != null) ? new Role(0, roleName) : null; // Default ID to 0

               
                           //Handle birthday conversion
                String birthday = (rs.getDate("birthday") != null) ? rs.getDate("birthday").toString() : "";

                    Employee employee = new FullTimeEmployee(
                        employeeId,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        birthday, // Pass String, not Date
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("employment_status"),
                        rs.getString("job_position"),
                        rs.getInt("supervisor_id"),
                        role, // Pass Role object
                        rs.getString("sss_number"),
                        rs.getString("philhealth_number"),
                        rs.getString("tin_number"),
                        rs.getString("pagibig_number"),
                        rs.getDouble("basic_salary"),
                        rs.getDouble("rice_subsidy"),
                        rs.getDouble("phone_allowance"),
                        rs.getDouble("clothing_allowance"),
                        rs.getDouble("gross_semi_monthly_rate"),
                        rs.getDouble("hourly_rate")
                    );
                    
                    // Calculate total hours worked
                    double totalHours = CompensationDetails.calculateTotalHours(conn, employeeId, startDate, endDate);
                    
                    return new Payroll(employee, totalHours);
                }
            }
        }
        throw new SQLException("Employee not found");
    }
}