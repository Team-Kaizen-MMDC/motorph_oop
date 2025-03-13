/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;

/**
 *
 * @author brianjancarlos
 */
public class AttendanceService {

    public static boolean isTimedIn(int employeeId) {
        boolean timedIn = false;

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM attendance WHERE employee_id = ? AND date = CURRENT_DATE AND logout_time IS NULL";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                timedIn = (count > 0);
            }
        } catch (SQLException e) {
            LoggerService.logError(" Database error checking time-in status for Employee ID: " + employeeId, e);
        }

        return timedIn;
    }

    public static void logTimeIn(int employeeId) {
        //Timestamp timeIn = Timestamp.valueOf(LocalDateTime.now()); //  Corrected
        Time timeIn = Time.valueOf(LocalTime.now());
        Date loginDate = Date.valueOf(LocalDate.now());
        LoggerService.logInfo("loginDate: " + loginDate);
        LoggerService.logInfo("timeIn: " + timeIn);
        Time timeOut = null; // Set the timeout value as needed

        try (Connection conn = DatabaseConnection.getConnection()) {
            // First check if employee already has an active session today
            String checkQuery = "SELECT COUNT(*) FROM attendance WHERE employee_id = ? AND date = ? AND logout_time IS NULL";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, employeeId);
            checkStmt.setDate(2, loginDate);
            //checkStmt.setTime(3, null);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // Employee already has an active session
                String message = "Employee ID " + employeeId + " already has an active session today.";
                LoggerService.logWarning(message);
                JOptionPane.showMessageDialog(null, message, "Time-In Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO attendance (employee_id, date, login_time, logout_time) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeId);
            stmt.setDate(2, loginDate); //  Correct way to store the date
            stmt.setTime(3, timeIn); //  Stores full timestamp for login
            stmt.setTime(4, null); //  Initially null for logout
            stmt.executeUpdate();
            String successMessage = "Time-in recorded successfully for Employee ID: " + employeeId + " at " + timeIn;
            LoggerService.logInfo(successMessage);
            JOptionPane.showMessageDialog(null, successMessage, "Time-In Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            String errorMessage = "Database error logging time-in for Employee ID: " + employeeId;
            LoggerService.logError(errorMessage, e);
            JOptionPane.showMessageDialog(null, errorMessage + "\n" + e.getMessage(), "Time-In Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void logTimeOut(int employeeId) {
        //Timestamp timeOut = Timestamp.valueOf(LocalDateTime.now());
        Time timeOut = Time.valueOf(LocalTime.now());
        Date loginDate = Date.valueOf(LocalDate.now());
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE attendance SET logout_time = ? WHERE attendance_id = (SELECT attendance_id FROM attendance WHERE employee_id"
                    + " = ? AND date = CURRENT_DATE AND logout_time IS NULL ORDER BY login_time DESC LIMIT 1)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setTime(1, timeOut);
            stmt.setInt(2, employeeId);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                String successMessage = "Time-out recorded successfully for Employee ID: " + employeeId + " at " + timeOut;
                LoggerService.logInfo(successMessage);
                JOptionPane.showMessageDialog(null, successMessage, "Time-Out Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String warningMessage = "No matching time-in found for Employee ID: " + employeeId + ". Please check your records.";
                LoggerService.logWarning(warningMessage);
                JOptionPane.showMessageDialog(null, warningMessage, "Time-Out Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            String errorMessage = "Database error logging time-out for Employee ID: " + employeeId;
            LoggerService.logError(errorMessage, e);
            JOptionPane.showMessageDialog(null, errorMessage + "\n" + e.getMessage(), "Time-Out Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // This is only used in LeaveApproval Dashboard
    public static void recordAttendance(int empID, java.sql.Date startDate, java.sql.Time defaultTimeIn, java.sql.Time defaultTimeOut) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO attendance (employee_id, date, login_time, logout_time) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, empID);
            statement.setDate(2, startDate);
            statement.setTime(3, defaultTimeIn);
            statement.setTime(4, defaultTimeOut);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Attendance recorded successfully. ");
        } catch (SQLException ex) {
            String statusMessage = "Failed to record attendance.";
            LoggerService.logError(statusMessage, ex);
            JOptionPane.showMessageDialog(null, statusMessage);
        }
    }

    public static void deleteRecord(int empID, java.sql.Date startDate) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "DELETE FROM attendance WHERE employee_id = ? AND date = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, empID);
            pstmt.setDate(2, startDate);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                String statusMessage = "Attendance Record deleted successfully: " + empID;
                LoggerService.logInfo(statusMessage);
                JOptionPane.showMessageDialog(null, statusMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String statusMessage = "No record found to delete: " + empID;
                LoggerService.logInfo(statusMessage);
                JOptionPane.showMessageDialog(null, "No record found to delete.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
            pstmt.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting record.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
