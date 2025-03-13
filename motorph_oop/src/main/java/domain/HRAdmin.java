/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import services.DatabaseConnection;
import services.PayrollCalculator;
import services.LoggerService;

/**
 *
 * @author brianjancarlos
 */
public class HRAdmin extends Employee implements PayrollCalculator, LeaveApproval {

    private String sssNumber;
    private String philhealthNumber;
    private String tinNumber;
    private String pagibigNumber;
    private double basicSalary;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    private double grossSemiMonthlyRate;
    private double hourlyRate;

    public HRAdmin(int employeeId, String firstName, String lastName, String birthday,
            String address, String phoneNumber, String employmentStatus,
            String jobPosition, int supervisorId, Role role,
            String sssNumber, String philhealthNumber, String tinNumber, String pagibigNumber,
            double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance,
            double grossSemiMonthlyRate, double hourlyRate) {
        //super(employeeId, firstName, lastName, birthday, address, phoneNumber, employmentStatus, jobPosition, supervisorId, role);
        super(employeeId, firstName, lastName, birthday, address, phoneNumber, employmentStatus, jobPosition, supervisorId,
                role, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance,
                grossSemiMonthlyRate, hourlyRate, sssNumber, philhealthNumber, tinNumber, pagibigNumber);

        this.sssNumber = sssNumber;
        this.philhealthNumber = philhealthNumber;
        this.tinNumber = tinNumber;
        this.pagibigNumber = pagibigNumber;
        this.birthday = birthday;
        this.basicSalary = basicSalary;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.grossSemiMonthlyRate = grossSemiMonthlyRate;
        this.hourlyRate = hourlyRate;
        this.supervisorId = supervisorId;
    }

    
    public String getSssNumber() {
        return sssNumber;
    }

    public String getPhilhealthNumber() {
        return philhealthNumber;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public String getPagibigNumber() {
        return pagibigNumber;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getRiceSubsidy() {
        return riceSubsidy;
    }

    public double getPhoneAllowance() {
        return phoneAllowance;
    }

    public double getClothingAllowance() {
        return clothingAllowance;
    }

    public double getGrossSemiMonthlyRate() {
        return grossSemiMonthlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public Role getRole() {
        return role;
    }

    public boolean isIsTimedIn() {
        return isTimedIn;
    }
    

    @Override
    public double calculateSalary() {
        return basicSalary - calculateTax(basicSalary); // Example: Fixed salary minus tax
    }

    @Override
    public double calculateTax(double salary) {
        return salary * 0.15; // Example: 15% tax deduction
    }

    @Override
    public boolean isTimedIn(int employeeId) {
        return isTimedIn;
    }

    @Override
    public boolean isTimedIn() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void approveLeave(int leaveId) {
        updateLeaveStatus(leaveId, "Approved");
    }

    @Override
    public void rejectLeave(int leaveId) {
        updateLeaveStatus(leaveId, "Rejected");
    }
    
    private void updateLeaveStatus(int leaveId, String status) {
        String sql = "UPDATE leave_requests SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, leaveId);
            pstmt.executeUpdate();
            LoggerService.logInfo("Leave ID " + leaveId + " " + status);
            JOptionPane.showMessageDialog(null, "Leave request " + status + " successfully!");
        } catch (SQLException e) {
            LoggerService.logError("Error updating leave status", e);
            JOptionPane.showMessageDialog(null, "Error processing leave request", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

   
}
