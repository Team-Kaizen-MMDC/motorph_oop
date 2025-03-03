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
import services.LoggerService;

/**
 *
 * @author brianjancarlos
 */
public class EmployeeService {

    //  Fetch from EmployeeFullDetailsView (Includes Gov IDs & Compensation)
    public static FullTimeEmployee getEmployeeById(int employeeId, boolean fetchFullDetails) {
        if (!fetchFullDetails) {
            return getEmployeeById(employeeId, false); // Calls basic method
        }

        LoggerService.logInfo("Fetching full employee details for ID: " + employeeId);

        FullTimeEmployee employee = null;

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM EmployeeFullDetailsView WHERE employee_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                LoggerService.logInfo("Employee Service: Found full employee details for ID: " + rs.getInt("employee_id"));

                employee = new FullTimeEmployee(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("birthday"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("employment_status"),
                        rs.getString("job_position"),
                        rs.getInt("supervisor_id"),
                        new Role(rs.getInt("role_id"), rs.getString("emp_role")),
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

                // Log additional fields from EmployeeFullDetailsView
                LoggerService.logInfo("Gov IDs: SSS: " + rs.getString("sss_number")
                        + ", PhilHealth: " + rs.getString("philhealth_number")
                        + ", TIN: " + rs.getString("tin_number")
                        + ", Pag-IBIG: " + rs.getString("pagibig_number"));

                LoggerService.logInfo("Compensation: Salary: " + rs.getDouble("basic_salary")
                        + ", Rice Subsidy: " + rs.getDouble("rice_subsidy")
                        + ", Phone Allowance: " + rs.getDouble("phone_allowance")
                        + ", Clothing Allowance: " + rs.getDouble("clothing_allowance"));

                LoggerService.logInfo("Successfully retrieved full employee details for ID: " + employeeId);
            } else {
                LoggerService.logWarning("Employee Service: No full employee details found for ID: " + employeeId);
            }
        } catch (SQLException e) {
            LoggerService.logError("Database error while fetching full employee details for ID: " + employeeId, e);
        }
        return employee;
    }
}
