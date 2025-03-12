/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domain.Employee;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author nativ
 */
class CompensationDetails {
   // Calculates total hours worked for a given employee in a date range
    public static float calculateTotalHours(Connection conn, int employeeId, Date startDate, Date endDate) throws SQLException {
        String query = "SELECT * FROM public.employee_record WHERE employee_id=? AND work_date BETWEEN ? AND ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, employeeId);
            pst.setDate(2, startDate);
            pst.setDate(3, endDate);
            try (ResultSet rs = pst.executeQuery()) {
                float totalHours = 0;
                while (rs.next()) {
                    LocalDateTime timeIn = rs.getTimestamp("time_in").toLocalDateTime();
                    LocalDateTime timeOut = rs.getTimestamp("time_out").toLocalDateTime();
                    Duration duration = Duration.between(timeIn, timeOut);
                    float hoursWorked = duration.toMillis() / (60 * 60 * 1000.0f);
                    if (hoursWorked >= 7.8333) hoursWorked = 8;
                    totalHours += hoursWorked;
                }
                return totalHours;
            }
        }
    }

    // Computes total allowances for an employee
    public static double computeTotalAllowances(Employee employee) {
        return employee.getRiceSubsidy() + employee.getPhoneAllowance() + employee.getClothingAllowance();
    }
}

