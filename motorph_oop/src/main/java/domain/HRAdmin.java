/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import services.PayrollCalculator;

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
    public void ApproveLeave() {
        
}
}
