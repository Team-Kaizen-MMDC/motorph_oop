/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import domain.Payslip;

/**
 *
 * @author brianjancarlos
 */
public class PayslipService {
    // Retrieves all available pay periods from the payroll table

    public List<String> getAvailablePayPeriods() {
        List<String> payPeriods = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT DISTINCT payroll_period_start, payroll_period_end FROM payroll ORDER BY payroll_period_start DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String start = rs.getString("payroll_period_start");
                String end = rs.getString("payroll_period_end");
                payPeriods.add(start + " to " + end);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            LoggerService.logError("Error fetching available pay periods: ", e);
        }
        return payPeriods;
    }

    // Retrieves payslip details for a specific employee in a given pay period
    public Payslip getPayslip(int employeeId, String startDate, String endDate) {
        Payslip payslip = null;

        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT p.hours_worked, p.gross_pay, p.deductions, p.net_pay, "
                    + "COALESCE(c.rice_subsidy, 0) + COALESCE(c.phone_allowance, 0) + COALESCE(c.clothing_allowance, 0) AS total_allowances "
                    + "FROM payroll p "
                    + "LEFT JOIN compensation_details c ON p.employee_id = c.employee_id "
                    + "WHERE p.employee_id = ? AND p.payroll_period_start = ? AND p.payroll_period_end = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, employeeId);
            pstmt.setDate(2, java.sql.Date.valueOf(startDate));
            pstmt.setDate(3, java.sql.Date.valueOf(endDate));

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double totalHoursWorked = rs.getDouble("hours_worked");
                double grossPay = rs.getDouble("gross_pay");
                double deductions = rs.getDouble("deductions");
                double netPay = rs.getDouble("net_pay");
                double allowances = rs.getDouble("total_allowances");

                payslip = new Payslip(employeeId, startDate, endDate,
                        totalHoursWorked, grossPay, allowances, deductions,
                        netPay);
            } else {
                LoggerService.logWarning(
                        "No payroll record found for Employee ID: " + employeeId + " (" + startDate + " to " + endDate + ")");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            LoggerService.logError(
                    "Error fetching payslip for Employee ID " + employeeId + ": ",
                    e);
        }

        return payslip;
    }
}
