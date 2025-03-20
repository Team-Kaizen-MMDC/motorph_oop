/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brianjancarlos
 */
public class PayrollProcessor {

    private Connection conn;

    public PayrollProcessor() {
        try {
            this.conn = DatabaseConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PayrollProcessor.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    // Retrieve all employees from the database
    private List<Integer> getAllEmployees() {
        List<Integer> employeeIds = new ArrayList<>();
        try {
            String sql = "SELECT employee_id FROM employee";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                employeeIds.add(rs.getInt("employee_id"));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.
                    println("Error fetching employee list: " + e.getMessage());
        }
        return employeeIds;
    }

    // Fetch salary details for an employee
    private double getHourlyRate(int employeeId) {
        double hourlyRate = 0.0;
        try {
            String sql = "SELECT hourly_rate FROM compensation_details WHERE employee_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                hourlyRate = rs.getDouble("hourly_rate");
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println(
                    "Error fetching hourly rate for Employee ID " + employeeId + ": " + e.
                            getMessage());
        }
        return hourlyRate;
    }

    // Fetch total hours worked for an employee in a given pay period
    private double getTotalHoursWorked(int employeeId, String startDate,
            String endDate) {
        double totalHours = 0.0;

        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT SUM(total_hours) AS total_hours FROM attendance_summary "
                    + "WHERE employee_id = ? AND date BETWEEN ? AND ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, employeeId);

            // Convert String dates to java.sql.Date
            java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
            java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);

            pstmt.setDate(2, sqlStartDate);
            pstmt.setDate(3, sqlEndDate);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                totalHours = rs.getDouble("total_hours");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println(
                    "Error fetching total hours worked for Employee ID " + employeeId + ": " + e.
                            getMessage());
        }

        return totalHours;
    }

    // Fetch tax deductions based on salary bracket
    private double calculateDeductions(double grossSalary) {
        double baseTax = 0.0;
        double taxRatePercent = 0.0;
        double plusInExcess = 0.0;

        try {
            String sql = "SELECT base_tax, tax_rate_percent, plus_in_excess FROM withholding_tax "
                    + "WHERE ? >= plus_in_excess ORDER BY plus_in_excess DESC LIMIT 1";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, grossSalary);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                baseTax = rs.getDouble("base_tax");
                taxRatePercent = rs.getInt("tax_rate_percent") / 100.0;
                plusInExcess = rs.getDouble("plus_in_excess");
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println("Error fetching tax bracket: " + e.getMessage());
        }

        double excessSalary = grossSalary - plusInExcess;
        double taxOnExcess = excessSalary * taxRatePercent;
        double totalTax = baseTax + taxOnExcess;

        return totalTax;
    }

    // Process payroll for a single employee in a given pay period
    private void processPayroll(int employeeId, String startDate, String endDate) {
        double hourlyRate = getHourlyRate(employeeId);
        double totalHoursWorked = getTotalHoursWorked(employeeId, startDate,
                endDate);
        double grossSalary = totalHoursWorked * hourlyRate;
        double deductions = calculateDeductions(grossSalary);
        double netSalary = grossSalary - deductions;

        try {
            Connection conn = DatabaseConnection.getConnection();

            // **Check for existing payroll entry in the same period**
            String checkSql = "SELECT 1 FROM payroll WHERE employee_id = ? AND payroll_period_start = ? AND payroll_period_end = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, employeeId);
            checkStmt.setDate(2, java.sql.Date.valueOf(startDate));
            checkStmt.setDate(3, java.sql.Date.valueOf(endDate));
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println(
                        "Skipping payroll for Employee ID: " + employeeId + " (Already exists for " + startDate + " to " + endDate + ")");
                rs.close();
                checkStmt.close();
                return; // **Exit to prevent duplicate insertion**
            }
            rs.close();
            checkStmt.close();

            // **Insert new payroll entry**
            String sql = "INSERT INTO payroll (employee_id, payroll_period_start, payroll_period_end, hours_worked, gross_pay, deductions, net_pay) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, employeeId);
            pstmt.setDate(2, java.sql.Date.valueOf(startDate));
            pstmt.setDate(3, java.sql.Date.valueOf(endDate));
            pstmt.setDouble(4, totalHoursWorked);
            pstmt.setDouble(5, grossSalary);
            pstmt.setDouble(6, deductions);
            pstmt.setDouble(7, netSalary);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            System.out.println(
                    "Payroll processed for Employee ID: " + employeeId + " (" + startDate + " to " + endDate + ")");
        } catch (SQLException e) {
            System.err.println(
                    "Error inserting payroll record for Employee ID " + employeeId + ": " + e.
                            getMessage());
        }
    }

    // Process payroll for all employees for both pay periods of the month
    public void processMonthlyPayroll() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();

        String period1Start = year + "-" + String.format("%02d", month) + "-01";
        String period1End = year + "-" + String.format("%02d", month) + "-15";
        String period2Start = year + "-" + String.format("%02d", month) + "-16";
        String period2End = year + "-" + String.format("%02d", month) + "-" + today.
                lengthOfMonth();

        List<Integer> employeeIds = getAllEmployees();

        try {
            conn.setAutoCommit(false); // Start transaction

            for (int employeeId : employeeIds) {
                processPayroll(employeeId, period1Start, period1End);
                processPayroll(employeeId, period2Start, period2End);
            }

            conn.commit();
            System.out.
                    println("Payroll processing completed for all employees.");
        } catch (SQLException e) {
            try {
                conn.rollback(); // Rollback transaction on error
                System.err.println(
                        "Error in batch payroll processing. Rolling back transactions.");
            } catch (SQLException rollbackEx) {
                System.err.
                        println("Rollback failed: " + rollbackEx.getMessage());
            }
        } finally {
            try {
                conn.setAutoCommit(true); // Restore auto-commit
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.
                        getMessage());
            }
        }
    }
}
