/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author brianjancarlos
 */
public class AttendanceService {

    public static void logTimeIn(int employeeId, Timestamp timeIn) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO attendance (employee_id, date, login_time, logout_time) VALUES (?, ?,CURRENT_DATE, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeId);
            stmt.setTimestamp(2, timeIn);
            stmt.setTimestamp(3, null); // For logout_time, setting to null initially
            stmt.executeUpdate();
            LoggerService.logInfo("Time-in recorded for Employee ID: " + employeeId + " at " + timeIn);
        } catch (SQLException e) {
            LoggerService.logError("Database error logging time-in for Employee ID: " + employeeId, e);
        }
    }

    public static void logTimeOut(int employeeId, Timestamp timeOut) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE Attendance SET logout_time = ? WHERE employee_id = ? AND date = CURRENT_DATE";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setTimestamp(1, timeOut);
            stmt.setInt(2, employeeId);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                LoggerService.logInfo("Time-out recorded for Employee ID: " + employeeId + " at " + timeOut);
            } else {
                LoggerService.logWarning("No matching time-in found for Employee ID: " + employeeId);
            }
        } catch (SQLException e) {
            LoggerService.logError("Database error logging time-out for Employee ID: " + employeeId, e);
        }
    }
}
