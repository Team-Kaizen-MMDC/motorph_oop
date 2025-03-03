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
public class FullTimeEmployee extends Employee implements PayrollCalculator {

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

    public FullTimeEmployee(int employeeId, String firstName, String lastName, String birthday,
            String address, String phoneNumber, String employmentStatus,
            String jobPosition, int supervisorId, Role role,
            String sssNumber, String philhealthNumber, String tinNumber, String pagibigNumber,
            double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance,
            double grossSemiMonthlyRate, double hourlyRate) {
        super(employeeId, firstName, lastName, birthday, address, phoneNumber, employmentStatus, jobPosition, supervisorId, role);

        this.sssNumber = sssNumber;
        this.philhealthNumber = philhealthNumber;
        this.tinNumber = tinNumber;
        this.pagibigNumber = pagibigNumber;
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

    // Implement the abstract method 
    @Override
    public void displayEmployeeDetails() {

    }

    // Getters for new fields
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
}
//    public FullTimeEmployee(int employeeId, String firstName, String lastName, String birthday,
//            String address, String phoneNumber, String employmentStatus,
//            String jobPosition, int supervisorId, Role role) {  // Correct pattern
//        super(employeeId, firstName, lastName, birthday, address, phoneNumber, employmentStatus, jobPosition, supervisorId, role);
//    }
//    // Implement PayrollCalculator interface
//    @Override
//    public double calculateSalary() {
//        return 50000.00 - calculateTax(50000.00); // Example: Fixed salary minus tax
//    }
//
//    @Override
//    public double calculateTax(double salary) {
//        return salary * 0.15; // Example: 15% tax deduction
//    }
//
//    //Implement the abstract method 
//    @Override
//    public void displayEmployeeDetails() {
//
//    }
//}
