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

    public FullTimeEmployee(int employeeId, String firstName, String lastName, String birthday,
            String address, String phoneNumber, String employmentStatus,
            String jobPosition, int supervisorId, Role role) {  // ✅ Correct signature
        super(employeeId, firstName, lastName, birthday, address, phoneNumber, employmentStatus, jobPosition, supervisorId, role);
    }
//    public FullTimeEmployee(int employeeId, String firstName, String lastName, String birthday,
//            String address, String phoneNumber, String employmentStatus,
//            String jobPosition, int supervisorId, Role role) {
//        super(employeeId, firstName, lastName, birthday, address, phoneNumber, employmentStatus, jobPosition, supervisorId, role);
//    }

    // Implement PayrollCalculator interface
    @Override
    public double calculateSalary() {
        return 50000.00 - calculateTax(50000.00); // Example: Fixed salary minus tax
    }

    @Override
    public double calculateTax(double salary) {
        return salary * 0.15; // Example: 15% tax deduction
    }

    // 🔹 Implement the abstract method 
    @Override
    public void displayEmployeeDetails() {

    }
}
