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

    public static FullTimeEmployee getEmployeeById(int employeeId) {
        LoggerService.logInfo("Employee Service: Fetching employee details for ID: " + employeeId);

        FullTimeEmployee employee = null; //  Prevents NullPointerException
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM EmployeeDetailsView WHERE employee_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                LoggerService.logInfo(" Employee Service: Found employee ID: " + rs.getInt("employee_id"));

                employee = new FullTimeEmployee(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("birthday"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("employment_status"),
                        rs.getString("job_position"),
                        rs.getInt("supervisor_id"), // Ensure this is not being swapped with employee_id
                        //rs.getString("supervisor_name"),
                        new Role(rs.getInt("role_id"), rs.getString("emp_role"))
                );

                LoggerService.logInfo("Employee Service: Successfully retrieved employee details for ID: " + employeeId);
            } else {
                LoggerService.logWarning("Employee Service: No employee found with ID: " + employeeId);
                LoggerService.logWarning("Employee Service: Check if this ID exists in EmployeeDetailsView.");
            }
        } catch (SQLException e) {
            LoggerService.logError("Employee Service: Database error while fetching employee ID: " + employeeId, e);
        }
        return employee;
    }
}
//        try (Connection conn = DatabaseConnection.getConnection()) {
//            LoggerService.logInfo("Employee Service: Fetching employee details for ID: " + employeeId);
//            String query = "SELECT * FROM EmployeeDetailsView WHERE employee_id = ?";    // Using the SQL View
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setInt(1, employeeId);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) { // Ensures we have a valid record
//                //return new FullTimeEmployee(
//                employee = new FullTimeEmployee(
//                        rs.getInt("employee_id"),
//                        rs.getString("first_name"),
//                        rs.getString("last_name"),
//                        rs.getString("birthday"),
//                        rs.getString("address"),
//                        rs.getString("phone_number"),
//                        rs.getString("employment_status"),
//                        rs.getString("job_position"),
//                        rs.getInt("supervisor_id"), //  Integer remains integer
//                        //rs.getString("supervisor_name"), //  Added Supervisor Name
//                        new Role(rs.getInt("role_id"), rs.getString("emp_role"))
//                ); // Fetches Role Name
//
//                LoggerService.logInfo("Employee Service: Successfully retrieved employee details for ID: " + employeeId);
//
//            } else {
//                LoggerService.logWarning("Employee Service: No employee found with ID: " + employeeId);
//                LoggerService.logWarning("Employee Service: Check if this ID exists in EmployeeDetailsView.");
//            }
//            if (rs.next()) {
//                return new FullTimeEmployee(
//                    rs.getInt("employee_id"),
//                    rs.getString("first_name"),
//                    rs.getString("last_name"),
//                    rs.getString("birthday"),
//                    rs.getString("address"),
//                    rs.getString("phone_number"),
//                    rs.getString("employment_status"),
//                    rs.getString("job_position"),
//                    rs.getInt("supervisor_name"),
//                    new Role(rs.getInt("emp_role"), Role.getRoleName(rs.getInt("emp_role")))
//                );
//            }
//    }
//    catch (SQLException e
//
//    
//        ) {
//            //e.printStackTrace();
//            LoggerService.logError("Employee Service: Database error while fetching employee ID: " + employeeId, e);
//    }
//
//}
