/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import javax.swing.JOptionPane;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author brianjancarlos
 */
public class LeaveService {
    // Method to file a leave

    public static void fileLeave(int employeeId, String leaveType, Date startDate, Date endDate, String remarks) {
        // Validate date range
        if (endDate.before(startDate)) {
            String errorMessage = "End date cannot be before start date!";
            LoggerService.logWarning(errorMessage);
            JOptionPane.showMessageDialog(null, errorMessage, "Leave Filing Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Check if an existing leave overlaps with the requested dates
        if (isLeaveOverlapping(employeeId, startDate, endDate)) {
            String errorMessage = "Employee ID " + employeeId + " already has a leave filed on the requested dates.";
            LoggerService.logWarning(errorMessage);
            JOptionPane.showMessageDialog(null, errorMessage, "Leave Filing Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Get employee details
        String firstName = "";
        String lastName = "";

        try (Connection conn = DatabaseConnection.getConnection()) {
            String employeeQuery = "SELECT first_name, last_name FROM Employee WHERE employee_id = ?";
            PreparedStatement empStmt = conn.prepareStatement(employeeQuery);
            empStmt.setInt(1, employeeId);
            ResultSet empRs = empStmt.executeQuery();

            if (empRs.next()) {
                firstName = empRs.getString("first_name");
                lastName = empRs.getString("last_name");
            } else {
                String errorMessage = "Employee ID not found!";
                LoggerService.logWarning(errorMessage);
                JOptionPane.showMessageDialog(null, errorMessage, "Leave Filing Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException e) {
            String errorMessage = "Database error fetching employee details.";
            LoggerService.logError(errorMessage, e);
            JOptionPane.showMessageDialog(null, errorMessage + "\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Insert leave request
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO leave_requests (employee_id, first_name, last_name, leave_type, start_date, end_date, remarks) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeId);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, leaveType);
            stmt.setDate(5, new java.sql.Date(startDate.getTime())); // Convert java.util.Date to java.sql.Date
            stmt.setDate(6, new java.sql.Date(endDate.getTime())); // Convert java.util.Date to java.sql.Date
            stmt.setString(7, remarks);
            stmt.executeUpdate();

            String successMessage = "Leave request submitted successfully!";
            LoggerService.logInfo(successMessage);
            JOptionPane.showMessageDialog(null, successMessage, "Leave Filed", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            String errorMessage = "Database error filing leave request.";
            LoggerService.logError(errorMessage, e);
            JOptionPane.showMessageDialog(null, errorMessage + "\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Check if an existing leave overlaps with the requested dates
    private static boolean isLeaveOverlapping(int employeeId, Date startDate, Date endDate) {
        boolean leaveExists = false;

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM leave_requests WHERE employee_id = ? AND "
                    + "(start_date <= ? AND end_date >= ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeId);
            stmt.setDate(2, new java.sql.Date(endDate.getTime()));   // Ensures overlap detection
            stmt.setDate(3, new java.sql.Date(startDate.getTime())); // Ensures overlap detection
            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                leaveExists = true;
            }
        } catch (SQLException e) {
            LoggerService.logError("Database error checking existing leave for Employee ID: " + employeeId, e);
        }

        return leaveExists;
    }
}
